package com.tsfapps.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentSecondFormBinding
import com.tsfapps.myapplication.db.Converters
import com.tsfapps.myapplication.utils.Constant.FIRST_FRAGMENT_DATA
import com.tsfapps.myapplication.utils.Constant.SECOND_FRAGMENT_DATA
import com.tsfapps.myapplication.utils.Constant.TAG
import com.tsfapps.myapplication.utils.MergeJsonObject.mergeJsonObjects
import org.json.JSONArray
import org.json.JSONObject


class SecondFormFragment : Fragment() {

    private var isNavigate: Boolean = false

    private var _binding: FragmentSecondFormBinding? = null
    private val binding get() = _binding!!


    private var strRgStructureInTheAffectedLand: String = ""
    private var strRgScaleOfImpact: String = ""
    private var strRgTypeOfConstruction: String = ""

    private var strMarketValue: String = ""

    private var residentialCategory = arrayOf<CheckBox>()
    private var residentialCategoryChecked = mutableListOf<String>()
    private var commercialCategory = arrayOf<CheckBox>()
    private var commercialCategoryChecked = mutableListOf<String>()
    private var mixedCategory = arrayOf<CheckBox>()
    private var mixedCategoryChecked = mutableListOf<String>()
    private var communityType = arrayOf<CheckBox>()
    private var communityTypeChecked = mutableListOf<String>()
    private var religiousStructure = arrayOf<CheckBox>()
    private var religiousStructureChecked = mutableListOf<String>()
    private var governmentStructure  = arrayOf<CheckBox>()
    private var governmentStructureChecked = mutableListOf<String>()
    private var otherStructure  = arrayOf<CheckBox>()
    private var otherStructureChecked = mutableListOf<String>()

    private var strTypeOfBusiness: String=""

    private var strRgStructureStatus: String = ""
    private var strRgTenantInStructure: String = ""
    private var strTenantName1: String=""
    private var strTenantName2: String=""
    private var strTenantName3: String=""
    private var strTenantName4: String=""

    private var strRgCommercialStructure: String = ""
    private var strCommercialEmployeeName1: String=""
    private var strCommercialEmployeeName2: String=""
    private var strCommercialEmployeeName3: String=""
    private var strCommercialEmployeeName4: String=""

    private var strRgResidentialStructure: String = ""
    private var strResidentialEmployeeName1: String=""
    private var strResidentialEmployeeName2: String=""

    private var strFruitBearing: String=""
    private var strNonFruitBearing: String=""
    private var jsonString: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondFormBinding.inflate(inflater, container, false)
        return binding.root
    }

//    private lateinit var generalDao: GeneralDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jsonString = arguments?.getString(FIRST_FRAGMENT_DATA).toString()
        binding.btnNextSecond.setOnClickListener {

            val rgStructure: Int = binding.structureRadioGroup.checkedRadioButtonId
            val rbStructure = rgStructure.let { view.findViewById<RadioButton>(it) }

            val rgScaleOfImpact: Int? = binding.scaleOfImpactRadioGroup.selectedRadioButtonId
            val rbScaleOfImpact = rgScaleOfImpact?.let { view.findViewById<RadioButton>(it) }

            val rgTypeOfConstruction: Int = binding.typeOfConstructionRadioGroup.checkedRadioButtonId
            val rbTypeOfConstruction = rgTypeOfConstruction.let { view.findViewById<RadioButton>(it) }

            val rgStatusOfStructure: Int? = binding.structureStatusRadioGroup.selectedRadioButtonId
            val rbStatusOfStructure = rgStatusOfStructure?.let { view.findViewById<RadioButton>(it) }

            val rgTenant: Int = binding.tenantRadioGroup.checkedRadioButtonId
            val rbTenant = rgTenant.let { view.findViewById<RadioButton>(it) }

            val rgCommercialEmployee: Int = binding.commercialEmployeeRadioGroup.checkedRadioButtonId
            val rbCommercialEmployee = rgCommercialEmployee.let { view.findViewById<RadioButton>(it) }

            val rgResidentialEmployee: Int = binding.residentialEmployeeRadioGroup.checkedRadioButtonId
            val rbResidentialEmployee = rgResidentialEmployee.let { view.findViewById<RadioButton>(it) }

            strRgStructureInTheAffectedLand = rbStructure?.text.toString()
            strRgScaleOfImpact = rbScaleOfImpact?.text.toString()
            strRgTypeOfConstruction = rbTypeOfConstruction?.text.toString()
            strRgStructureStatus = rbStatusOfStructure?.text.toString()
            strRgTenantInStructure = rbTenant?.text.toString()
            if (strRgTenantInStructure == "Yes"){
                if (binding.etTenantName1.text.isEmpty()){
                    binding.etTenantName1.error = "Please enter the Name"
                    isNavigate = false
                }else{
                    isNavigate = true
                }
            }
            else{
                isNavigate = true
            }
            strRgCommercialStructure = rbCommercialEmployee?.text.toString()
            if (strRgCommercialStructure == "Yes"){
                if (binding.etCommercialEmployeeName1.text.isEmpty()){
                    binding.etCommercialEmployeeName1.error = "Please enter the Name"
                    isNavigate = false
                }else{
                    isNavigate = true
                }
            }
            else{
                isNavigate = true
            }
            strRgResidentialStructure = rbResidentialEmployee?.text.toString()
            if (strRgResidentialStructure == "Yes"){
                if (binding.etResidentialEmployeeName1.text.isEmpty()){
                    binding.etResidentialEmployeeName1.error = "Please enter the Name"
                    isNavigate = false
                }else{
                    isNavigate = true
                }
            }
            else{
                isNavigate = true
            }


            residentialCategory = arrayOf(binding.checkboxHouse, binding.checkboxHut)
            for (i in residentialCategory.indices) {
                if (residentialCategory[i].isChecked) {
                    residentialCategoryChecked.add(residentialCategory[i].text.toString())
                }
            }
            val arrResidentialCategory = Converters.fromCheckBoxList(residentialCategoryChecked)

            commercialCategory =
                arrayOf(binding.checkShops, binding.checkHotel, binding.checkSmallEatery,
                    binding.checkKiosk, binding.checkFarmHouse, binding.checkPetrolPump, binding.checkClinic,
                    binding.checkSTDBooth, binding.checkWorkshop, binding.checkVendors, binding.checkComComplex,
                    binding.checkIndustry, binding.checkPvtOffice)
            for (i in commercialCategory.indices) {
                if (commercialCategory[i].isChecked) {
                    commercialCategoryChecked.add(commercialCategory[i].text.toString())
                }
            }
            val arrCommercialCategory = Converters.fromCheckBoxList(commercialCategoryChecked)

            mixedCategory = arrayOf(binding.checkResidentialCommercial)
            for (i in mixedCategory.indices) {
                if (mixedCategory[i].isChecked) {
                    mixedCategoryChecked.add(mixedCategory[i].text.toString())
                }
            }
            val arrMixedCategory = Converters.fromCheckBoxList(mixedCategoryChecked)

            communityType = arrayOf(binding.checkCommunityCenter, binding.checkClub, binding.checkTrust,
                binding.checkMemorials)
            for (i in communityType.indices) {
                if (communityType[i].isChecked) {
                    communityTypeChecked.add(communityType[i].text.toString())
                }
            }
            val arrCommunityType = Converters.fromCheckBoxList(communityTypeChecked)

            religiousStructure = arrayOf(binding.checkTemple, binding.checkChurch, binding.checkMosque,
                binding.checkGurudwara, binding.checkShrines, binding.checkSacredGrove)
            for (i in religiousStructure.indices) {
                if (religiousStructure[i].isChecked) {
                    religiousStructureChecked.add(religiousStructure[i].text.toString())
                }
            }
            val arrReligiousStructure = Converters.fromCheckBoxList(religiousStructureChecked)


            governmentStructure = arrayOf(binding.checkGovernmentOffice, binding.checkHospital,
                binding.checkSchool, binding.checkCollege, binding.checkBusStop)
            for (i in governmentStructure.indices) {
                if (governmentStructure[i].isChecked) {
                    governmentStructureChecked.add(governmentStructure[i].text.toString())
                }
            }
            val arrGovernmentStructure = Converters.fromCheckBoxList(governmentStructureChecked)

            otherStructure = arrayOf(binding.checkBoundaryWall, binding.checkFoundation, binding.checkCattleShed)
            for (i in otherStructure.indices) {
                if (otherStructure[i].isChecked) {
                    otherStructureChecked.add(otherStructure[i].text.toString())
                }
            }
            val arrOtherStructure = Converters.fromCheckBoxList(otherStructureChecked)

            strMarketValue = binding.etMarketValue.text.toString()

            strTypeOfBusiness = binding.etTypeOfBusiness.text.toString()

            strTenantName1 = binding.etTenantName1.text.toString()
            strTenantName2 = binding.etTenantName2.text.toString()
            strTenantName3 = binding.etTenantName3.text.toString()
            strTenantName4 = binding.etTenantName4.text.toString()

            strCommercialEmployeeName1 = binding.etCommercialEmployeeName1.text.toString()
            strCommercialEmployeeName2 = binding.etCommercialEmployeeName2.text.toString()
            strCommercialEmployeeName3 = binding.etCommercialEmployeeName3.text.toString()
            strCommercialEmployeeName4 = binding.etCommercialEmployeeName4.text.toString()

            strResidentialEmployeeName1 = binding.etResidentialEmployeeName1.text.toString()
            strResidentialEmployeeName2 = binding.etResidentialEmployeeName2.text.toString()

            strFruitBearing = binding.etFruitBearing.text.toString()
            strNonFruitBearing = binding.etNonFruitBearing.text.toString()

            val logMessage1 = "RadioButton: $strRgStructureInTheAffectedLand,$strRgScaleOfImpact," +
                    "$strRgTypeOfConstruction, $strRgStructureStatus, $strRgTenantInStructure, $strRgCommercialStructure," +
                    "$strRgResidentialStructure,"
            val logMessage2 = "Use of Structure: $arrResidentialCategory,$arrCommercialCategory," +
                    "$arrMixedCategory,$arrCommunityType,$arrReligiousStructure,$arrGovernmentStructure,$arrOtherStructure,"
            val rootObject= JSONObject()
            rootObject.put("Market Value", strMarketValue)
            rootObject.put("Structure In The Affected Land", strRgStructureInTheAffectedLand)
            rootObject.put("Scale Of Impact", strRgScaleOfImpact)
            rootObject.put("Type Of Construction", strRgTypeOfConstruction)
            rootObject.put("Residential Category", JSONArray(residentialCategoryChecked))
            rootObject.put("Commercial Category", JSONArray(commercialCategoryChecked))
            rootObject.put("Mixed Category", JSONArray(mixedCategoryChecked))
            rootObject.put("Community Type", JSONArray(communityTypeChecked))
            rootObject.put("Religious Structure", JSONArray(religiousStructureChecked))
            rootObject.put("Government Structure", JSONArray(governmentStructureChecked))
            rootObject.put("Other Structure", JSONArray(otherStructureChecked))
            rootObject.put("Type Of Business", strTypeOfBusiness)
            rootObject.put("Structure Status", strRgStructureStatus)
            rootObject.put("Tenant In Structure", strRgTenantInStructure)
            rootObject.put("Tenant Name 1", strTenantName1)
            rootObject.put("Tenant Name 2", strTenantName2)
            rootObject.put("Tenant Name 3", strTenantName3)
            rootObject.put("Tenant Name 4", strTenantName4)
            rootObject.put("Commercial Structure", strRgCommercialStructure)
            rootObject.put("Commercial Employee Name 1", strCommercialEmployeeName1)
            rootObject.put("Commercial Employee Name 2", strCommercialEmployeeName2)
            rootObject.put("Commercial Employee Name 3", strCommercialEmployeeName3)
            rootObject.put("Commercial Employee Name 4", strCommercialEmployeeName4)
            rootObject.put("Residential Structure", strRgResidentialStructure)
            rootObject.put("Residential Employee Name 1", strResidentialEmployeeName1)
            rootObject.put("Residential Employee Name 2", strResidentialEmployeeName2)
            rootObject.put("Fruit Bearing", strFruitBearing)
            rootObject.put("Non-Fruit Bearing", strNonFruitBearing)
            Log.d(TAG, "Data: $rootObject")
            if (isNavigate) {
                Log.d(TAG, "Data of first at 2nd: $jsonString")
                val rootObject2 = JSONObject(jsonString)

                val merged = mergeJsonObjects(rootObject, rootObject2)
                Log.d(TAG, "Merged at 2nd: $merged")

                val bundle = Bundle().apply {
                    putString(SECOND_FRAGMENT_DATA, merged.toString())
                    Log.i(TAG, "mergedObj for 3rd: $merged")
                }

                findNavController().navigate(R.id.frag_third_form, bundle)
            }
        }


        binding.btnBackSecond.setOnClickListener {
            findNavController().navigateUp()
        }



    }


}