package com.tsfapps.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentFirstFormBinding
import com.tsfapps.myapplication.db.dao.GeneralDao
import com.tsfapps.myapplication.db.database.AppDatabase
import com.tsfapps.myapplication.db.entity.GeneralEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstFormFragment : Fragment() {
    private var _binding: FragmentFirstFormBinding? = null
    private val binding get() = _binding!!

    private var strQuestionnaireNo: String = ""
    private var strVillageName: String = ""
    private var strBlockName: String = ""
    private var strDistrictName: String = ""
    private var strThanaNo: String = ""
    private var strPlotNo: String = ""
    private var strOwnerShipLand: String = ""
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
        val selectedId: Int = binding.rgOwnerShip.getCheckedCheckableImageButtonId()
        val radioButton =  view.findViewById<RadioButton>(selectedId)

        binding.btnNextFirst.setOnClickListener {

            strQuestionnaireNo = binding.edtQuestionnaireNo.text.toString()
            strVillageName = binding.edtVillageName.text.toString()
            strBlockName = binding.edtBlockName.text.toString()
            strDistrictName = binding.edtDistrictName.text.toString()
            strThanaNo = binding.edtThanaNo.text.toString()
            strPlotNo = binding.edtPlotNo.text.toString()
            strOwnerShipLand = radioButton.text.toString()
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

            generalDB(strQuestionnaireNo, strVillageName,
                strBlockName, strDistrictName, strThanaNo, strPlotNo, strOwnerShipLand, strAffectedLand, strTotalLand,
                strIrrigated, strNonIrrigated, strOtherLand, strTotal, strOwnershipSpecify,
                strOwnerName, strProofOfIdentity, strNameOfBank, strAccountNo, strIfscCode,
                strFatherName, strMarketRate, strRevenueRate, strAgriculturalLaborerName1,
                strAgriculturalLaborerName2, strTenantLesseeName1, strTenantLesseeName2,
                strSharecropperName1, strSharecropperName2)
            findNavController().navigate(R.id.frag_second_form)
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
                          strOwnerShipLand:String,
                          strAffectedLand:String,
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
                strBlockName, strDistrictName, strThanaNo, strPlotNo, strOwnerShipLand, strAffectedLand,strTotalLand, strIrrigated, strNonIrrigated,
            strOtherLand, strTotal, strOwnershipSpecify, strOwnerName, strProofOfIdentity, strNameOfBank, strAccountNo, strIfscCode, strFatherName, strMarketRate,
            strRevenueRate, strAgriculturalLaborerName1, strAgriculturalLaborerName2, strTenantLesseeName1, strTenantLesseeName2, strSharecropperName1, strSharecropperName2))

            val generalAll = generalDao.getAll()
            for (general in generalAll) {
                Log.i("TSF_APPS","id: ${general.generalId} Questionnaire No: ${general.questionnaireNo}" +
                        " Village Name: ${general.villageName} Ownership Land: ${general.strOwnerShipLand}")

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}