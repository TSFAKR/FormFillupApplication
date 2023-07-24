package com.tsfapps.myapplication.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentFirstFormBinding
import com.tsfapps.myapplication.db.Converters.fromCheckBoxList
import com.tsfapps.myapplication.db.dao.GeneralDao
import com.tsfapps.myapplication.db.database.AppDatabase
import com.tsfapps.myapplication.db.entity.GeneralEntity
import com.tsfapps.myapplication.utils.ImageConverter.bitMapToString
import com.tsfapps.myapplication.utils.ImageConverter.convertImageViewToBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.util.*


class FirstFormFragment : Fragment() {

    private var isNavigate: Boolean = false
    val REQUEST_PERMISSION = 1001

    private var _binding: FragmentFirstFormBinding? = null
    private val binding get() = _binding!!

    private  var strSelectedPhoto : String = ""
    private  var strOwnerPhoto : String = ""
    private  var strIdentityProofPhoto : String = ""


    private var typeOfLand = arrayOf<CheckBox>()
    private var typeOfLandChecked = mutableListOf<String>()
    private var useOfLand = arrayOf<CheckBox>()
    private var useOfLandChecked = mutableListOf<String>()

    private var strQuestionnaireNo: String = ""
    private var strVillageName: String = ""
    private var strBlockName: String = ""
    private var strDistrictName: String = ""
    private var strThanaNo: String = ""
    private var strPlotNo: String = ""
    private var strRbOwnerShipLand: String = ""
    private var strRbDocumentType: String = ""
    private var strRbOwnershipStatus: String = ""
    private var strRbPrivateOwnership: String = ""
    private var strRbAgriculturalLaborer: String = ""
    private var strRbTenantLessee: String = ""
    private var strRbShareCopper: String = ""
    private var strAffectedLand: String = ""
    private var strTotalLand: String = ""
    private var strIrrigated: String = ""
    private var strNonIrrigated: String = ""
    private var strOtherLand: String = ""
    private var strTotal: String = ""
    private var strOwnershipSpecify: String = ""
    private var strOwnerName: String = ""
    private var strProofOfIdentity: String = ""
    private var strNameOfBank: String = ""
    private var strAccountNo: String = ""
    private var strIfscCode: String = ""
    private var strFatherName: String = ""
    private var strMarketRate: String = ""
    private var strRevenueRate: String = ""
    private var strAgriculturalLaborerName1: String = ""
    private var strAgriculturalLaborerName2: String = ""
    private var strTenantLesseeName1: String = ""
    private var strTenantLesseeName2: String = ""
    private var strSharecropperName1: String = ""
    private var strSharecropperName2: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var generalDao: GeneralDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnUploadPhoto.setOnClickListener {
            captureImage(pic_id)
        }
        binding.btnUploadOwnerPhoto.setOnClickListener {
            captureImage(ownerPhotoId)
        }
        binding.btnUploadIdentityProof.setOnClickListener {
            captureImage(identityProofId)
        }
        val strSelectedPhotoBitmap = convertImageViewToBitmap(binding.imageViewSelectedPhoto)
        val strOwnerPhotoBitmap = convertImageViewToBitmap(binding.imageViewOwnerPhoto)
        val strIdentityProofBitmap = convertImageViewToBitmap(binding.imageViewIdentityProof)


        binding.btnNextFirst.setOnClickListener {
            strSelectedPhoto = bitMapToString(strSelectedPhotoBitmap)
            strOwnerPhoto = bitMapToString(strOwnerPhotoBitmap)
            strIdentityProofPhoto = bitMapToString(strIdentityProofBitmap)

            val rgOwnershipLand: Int? = binding.rgOwnerShip.selectedRadioButtonId
            val rbOwnershipLand = rgOwnershipLand?.let { view.findViewById<RadioButton>(it) }

            val radioGroupDocumentType: Int? = binding.radioGroupDocumentType.selectedRadioButtonId
            val rbDocumentType = radioGroupDocumentType?.let { view.findViewById<RadioButton>(it) }

            val rgOwnershipStatus: Int? = binding.ownershipStatusRadioGroup.selectedRadioButtonId
            val rbOwnershipStatus = rgOwnershipStatus?.let { view.findViewById<RadioButton>(it) }

            val rgPrivateOwnership: Int? = binding.privateOwnershipRadioGroup.checkedRadioButtonId
            val rbPrivateOwnership = rgPrivateOwnership?.let { view.findViewById<RadioButton>(it) }

            val rgAgriculturalLaborer: Int = binding.agriculturalLaborerRadioGroup.checkedRadioButtonId
            val rbAgriculturalLaborer = rgAgriculturalLaborer.let { view.findViewById<RadioButton>(it) }

            val rgTenantLessee: Int = binding.tenantLesseeRadioGroup.checkedRadioButtonId
            val rbTenantLessee = rgTenantLessee.let { view.findViewById<RadioButton>(it) }

            val rgSharecropper: Int = binding.sharecropperRadioGroup.checkedRadioButtonId
            val rbSharecropper = rgSharecropper.let { view.findViewById<RadioButton>(it) }


            typeOfLand =
                arrayOf(binding.checkIrrigated, binding.checkNonIrrigated, binding.checkBarren,
                    binding.checkForest, binding.checkResidential, binding.checkCommercial,
                    binding.checkPond)


            for (i in typeOfLand.indices) {
                if (typeOfLand[i].isChecked) {
                    typeOfLandChecked.add(typeOfLand[i].text.toString())
                }
            }
            val arrTypeOfLand = fromCheckBoxList(typeOfLandChecked)

            useOfLand =
                arrayOf(binding.checkCultivation, binding.checkOrchard, binding.checkResidential1,
                    binding.checkCommercial1, binding.checkForestation, binding.checkNoUseBarren)


            for (i in useOfLand.indices) {
                if (useOfLand[i].isChecked) {
                    useOfLandChecked.add(useOfLand[i].text.toString())
                }
            }
            val arrUseOfLand = fromCheckBoxList(useOfLandChecked)

            strQuestionnaireNo = binding.edtQuestionnaireNo.text.toString()
            strVillageName = binding.edtVillageName.text.toString()
            strBlockName = binding.edtBlockName.text.toString()
            strDistrictName = binding.edtDistrictName.text.toString()
            strThanaNo = binding.edtThanaNo.text.toString()
            strPlotNo = binding.edtPlotNo.text.toString()
            strRbOwnerShipLand = rbOwnershipLand?.text.toString()
            if (strRbOwnerShipLand == "Other"){
                if (binding.etOtherSpecifyAffectedLand.text.isEmpty()){
                    binding.etOtherSpecifyAffectedLand.error = "Please specify the Ownership"
                    isNavigate = false
                }else{
                    strRbOwnerShipLand = binding.etOtherSpecifyAffectedLand.text.toString()
                    isNavigate = true

                }
            }
            else{
                isNavigate = true
            }
            strRbDocumentType = rbDocumentType?.text.toString()
            if (strRbDocumentType == "Other"){
                if (binding.edtProofOfIdentity.text.isEmpty()){
                    binding.edtProofOfIdentity.error = "Please specify the Proof of identity"
                    isNavigate = false
                }else{
                    strRbDocumentType = binding.edtProofOfIdentity.text.toString()
                    isNavigate = true

                }
            }
            else{
                isNavigate = true
            }
            strRbOwnershipStatus = rbOwnershipStatus?.text.toString()
            if (strRbOwnershipStatus == "Other"){
                if (binding.edtOwnershipSpecify.text.isEmpty()){
                    binding.edtOwnershipSpecify.error = "Please specify the Ownership Status"
                    isNavigate = false
                }else{
                    strRbOwnershipStatus = binding.edtOwnershipSpecify.text.toString()
                    isNavigate = true

                }
            }
            else{
                isNavigate = true
            }
            strRbPrivateOwnership = rbPrivateOwnership?.text.toString()

            //12 a
            strRbAgriculturalLaborer = rbAgriculturalLaborer?.text.toString()
            if (strRbAgriculturalLaborer == "Yes"){
                if (binding.edtAgriculturalLaborerName1.text.isEmpty()){
                    binding.edtAgriculturalLaborerName1.error = "Please enter the Name"
                    isNavigate = false
                }else{
                    isNavigate = true
                }
            }
            else{
                isNavigate = true
            }

            //12 b
            strRbTenantLessee = rbTenantLessee?.text.toString()
            if (strRbTenantLessee == "Yes"){
                if (binding.edtTenantLesseeName1.text.isEmpty()){
                    binding.edtTenantLesseeName1.error = "Please enter the Name"
                    isNavigate = false
                }else{
                    isNavigate = true
                }
            }
            else{
                isNavigate = true
            }
            //12 c
            strRbShareCopper = rbSharecropper?.text.toString()
            if (strRbShareCopper == "Yes"){
                if (binding.edtSharecropperName1.text.isEmpty()){
                    binding.edtSharecropperName1.error = "Please enter the Name"
                    isNavigate = false
                }else{
                    isNavigate = true
                }
            }
            else{
                isNavigate = true
            }

            strAffectedLand = binding.edtAffectedLand.text.toString()
            strTotalLand = binding.edtTotalLand.text.toString()
            strIrrigated = binding.edtNonIrrigated.text.toString()
            strNonIrrigated = binding.edtNonIrrigated.text.toString()
            strOtherLand = binding.edtOtherLand.text.toString()
            strTotal = binding.edtTotal.text.toString()
            strOwnershipSpecify = binding.edtOwnershipSpecify.text.toString()
            strOwnerName = binding.edtOwnerName.text.toString()
            strProofOfIdentity = binding.edtProofOfIdentity.text.toString()
            strNameOfBank = binding.edtNameOfBank.text.toString()
            strAccountNo = binding.edtAccountNo.text.toString()
            strIfscCode = binding.edtIfscCode.text.toString()
            strFatherName = binding.edtFatherName.text.toString()
            strMarketRate = binding.edtMarketRate.text.toString()
            strRevenueRate = binding.edtRevenueRate.text.toString()
            strAgriculturalLaborerName1 = binding.edtAgriculturalLaborerName1.text.toString()
            strAgriculturalLaborerName2 = binding.edtAgriculturalLaborerName2.text.toString()
            strTenantLesseeName1 = binding.edtTenantLesseeName1.text.toString()
            strTenantLesseeName2 = binding.edtTenantLesseeName2.text.toString()
            strSharecropperName1 = binding.edtSharecropperName1.text.toString()
            strSharecropperName2 = binding.edtSharecropperName2.text.toString()

            val rootObject = JSONObject()

            rootObject.put("Type Of Land Checked", JSONArray(typeOfLandChecked))
            rootObject.put("Use Of Land Checked", JSONArray(useOfLandChecked))
            rootObject.put("Questionnaire No", strQuestionnaireNo)
            rootObject.put("Village Name", strVillageName)
            rootObject.put("Block Name", strBlockName)
            rootObject.put("District Name", strDistrictName)
            rootObject.put("Thana No", strThanaNo)
            rootObject.put("Plot No", strPlotNo)
            rootObject.put("Ownership Land", strRbOwnerShipLand)
            rootObject.put("Document Type", strRbDocumentType)
            rootObject.put("Ownership Status", strRbOwnershipStatus)
            rootObject.put("Private Ownership", strRbPrivateOwnership)
            rootObject.put("Agricultural Laborer", strRbAgriculturalLaborer)
            rootObject.put("Tenant Lessee", strRbTenantLessee)
            rootObject.put("Share Copper", strRbShareCopper)
            rootObject.put("Affected Land", strAffectedLand)
            rootObject.put("Total Land", strTotalLand)
            rootObject.put("Irrigated", strIrrigated)
            rootObject.put("Non-Irrigated", strNonIrrigated)
            rootObject.put("Other Land", strOtherLand)
            rootObject.put("Total", strTotal)
            rootObject.put("Ownership Specify", strOwnershipSpecify)
            rootObject.put("Owner Name", strOwnerName)
            rootObject.put("Proof Of Identity", strProofOfIdentity)
            rootObject.put("Name Of Bank", strNameOfBank)
            rootObject.put("Account No", strAccountNo)
            rootObject.put("IFSC Code", strIfscCode)
            rootObject.put("Father Name", strFatherName)
            rootObject.put("Market Rate", strMarketRate)
            rootObject.put("Revenue Rate", strRevenueRate)
            rootObject.put("Agricultural Laborer Name 1", strAgriculturalLaborerName1)
            rootObject.put("Agricultural Laborer Name 2", strAgriculturalLaborerName2)
            rootObject.put("Tenant Lessee Name 1", strTenantLesseeName1)
            rootObject.put("Tenant Lessee Name 2", strTenantLesseeName2)
            rootObject.put("Sharecropper Name 1", strSharecropperName1)
            rootObject.put("Sharecropper Name 2", strSharecropperName2)
            rootObject.put("Selected Photo", strSelectedPhoto)
            rootObject.put("Owner Photo", strOwnerPhoto)
            rootObject.put("Identity Proof Photo", strIdentityProofPhoto)



            generalDB(strQuestionnaireNo, strVillageName,
                strBlockName, strDistrictName, strThanaNo, strPlotNo, strRbOwnerShipLand, strAffectedLand,
                arrTypeOfLand, arrUseOfLand, strTotalLand, strIrrigated, strNonIrrigated, strOtherLand, strTotal,
                strOwnershipSpecify, strOwnerName, strProofOfIdentity, strNameOfBank, strAccountNo,
                strIfscCode, strFatherName, strMarketRate, strRevenueRate, strAgriculturalLaborerName1,
                strAgriculturalLaborerName2, strTenantLesseeName1, strTenantLesseeName2,
                strSharecropperName1, strSharecropperName2)
            if (isNavigate){
                findNavController().navigate(R.id.frag_second_form)
            }

        }
        binding.btnBackFirst.setOnClickListener {
            findNavController().navigateUp()
        }

        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "form_database"
        ).build()
        generalDao = db.generalDao()

    }
    private fun generalDB(strQuestionnaireNo:String,
                          strVillageName:String,
                          strBlockName:String,
                          strDistrictName:String,
                          strThanaNo:String,
                          strPlotNo:String,
                          strRbOwnerShipLand:String,
                          strAffectedLand:String,
                          arrTypeOfLand:String,
                          arrUseOfLand:String,
                          strTotalLand:String,
                          strIrrigated:String,
                          strNonIrrigated:String,
                          strOtherLand:String,
                          strTotal:String,
                          strOwnershipSpecify:String,
                          strOwnerName:String,
                          strProofOfIdentity:String,
                          strNameOfBank:String,
                          strAccountNo:String,
                          strIfscCode:String,
                          strFatherName:String,
                          strMarketRate:String,
                          strRevenueRate:String,
                          strAgriculturalLaborerName1:String,
                          strAgriculturalLaborerName2:String,
                          strTenantLesseeName1:String,
                          strTenantLesseeName2:String,
                          strSharecropperName1:String,
                          strSharecropperName2:String
    ){
        lifecycleScope.launch(Dispatchers.IO) {
            generalDao.insertGeneral(GeneralEntity(0, strQuestionnaireNo, strVillageName,
                strBlockName, strDistrictName, strThanaNo, strPlotNo, strRbOwnerShipLand, arrTypeOfLand, arrUseOfLand, strAffectedLand, strTotalLand, strIrrigated, strNonIrrigated,
                strOtherLand, strTotal, strOwnershipSpecify, strOwnerName, strProofOfIdentity, strNameOfBank, strAccountNo, strIfscCode, strFatherName, strMarketRate,
                strRevenueRate, strAgriculturalLaborerName1, strAgriculturalLaborerName2, strTenantLesseeName1, strTenantLesseeName2, strSharecropperName1, strSharecropperName2))

            val generalAll = generalDao.getAll()
            for (general in generalAll) {
                Log.i("TSF_APPS","id: ${general.generalId} Questionnaire No: ${general.questionnaireNo}" +
                        " Village Name: ${general.villageName} Ownership Land: ${general.strRbOwnerShipLand} Types of Land: ${general.arrTypeOfLand}")

            }
        }
    }
    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun captureImage(imageId: Int) {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(cameraIntent, imageId)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            pic_id -> { val photo = data!!.extras!!["data"] as Bitmap?
                binding.imageViewSelectedPhoto.setImageBitmap(photo)}
            ownerPhotoId -> { val photo = data!!.extras!!["data"] as Bitmap?
                binding.imageViewOwnerPhoto.setImageBitmap(photo)}
            identityProofId -> { val photo = data!!.extras!!["data"] as Bitmap?
                binding.imageViewIdentityProof.setImageBitmap(photo)}

        }


        if (requestCode == pic_id) {

            val photo = data!!.extras!!["data"] as Bitmap?
            binding.imageViewSelectedPhoto.setImageBitmap(photo)
        }
    }

    companion object {
        private const val pic_id = 123
        private const val ownerPhotoId = 125
        private const val identityProofId = 127
    }    override fun onResume() {
        super.onResume()
        checkCameraPermission()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}