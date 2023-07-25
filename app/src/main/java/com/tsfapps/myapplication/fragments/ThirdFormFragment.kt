package com.tsfapps.myapplication.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.MainActivity
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentThirdFormBinding
import com.tsfapps.myapplication.db.preference.MySharedPreference
import com.tsfapps.myapplication.network.NetworkService
import com.tsfapps.myapplication.network.SendData
import com.tsfapps.myapplication.utils.Constant
import com.tsfapps.myapplication.utils.Constant.TAG
import com.tsfapps.myapplication.utils.GetTimeStamps.getCurrentDateTime
import com.tsfapps.myapplication.utils.MergeJsonObject.mergeJsonObjects
import com.tsfapps.myapplication.utils.MergeJsonObject.mergeJsonObjects3
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date


class ThirdFormFragment : Fragment() {
    private var isNavigate: Boolean = false

    private var _binding: FragmentThirdFormBinding? = null
    private val binding get() = _binding!!

    private var strRgSocialCategory: String = ""
    private var strSocialCategoryOther: String = ""

    private var strRgReligiousCategory: String = ""
    private var strReligiousCategoryOther: String = ""

    private var strUnmarriedSonBrother: String = ""
    private var strUnmarriedDaughterSister: String = ""
    private var strDivorceeWidow: String = ""
    private var strMinorOrphan: String = ""
    private var strAnnualIncome: String = ""

    private var strRgAdditionalLand: String = ""
    private var strRgResettlementRelocationOption: String = ""
    private var strRgLandCompensationOption: String = ""
    private var strRgIncomeRestorationAssistance: String = ""
    private var strSpecifyOtherAssistance: String = ""

    private var strRgFinancialDecisionMaking: String = ""
    private var strRgSocialDecisionMaking: String = ""
    private var strRgHomesteadOrFarmLand: String = ""
    private var strRgHouse: String = ""
    private var strRgFourWheeler: String = ""
    private var strRgTwoWheeler: String = ""
    private var strRgCellPhone: String = ""
    private var strRgPersonalComputer: String = ""
    private var strRgAnyOtherAsset: String = ""
    private var strAnyOtherAssetDescription: String = ""

    private var strRgBankAccount: String = ""
    private var strRgSHGMember: String = ""
    private var strRgRelocationAffect: String = ""
    private var strRelocationAffectExplanation: String = ""

    private var strRgLoan: String = ""
    private var strLoanExplanation: String = ""
    private var strRgHouseFromLoan: String = ""
    private var strHouseFromLoanExplanation: String = ""
    private var strRgEmployment: String = ""
    private var strEmploymentExplanation: String = ""
    private var strRgTraining: String = ""
    private var strTrainingExplanation: String = ""
    private var strRgOtherGovernmentScheme: String = ""
    private var strOtherGovernmentSchemeExplanation: String = ""
    private lateinit var mySharedPreference: MySharedPreference
    private var jsonString: String = ""
    private var jsonFamily: String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdFormBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedPreference = MySharedPreference(requireContext())
        jsonString = arguments?.getString(Constant.SECOND_FRAGMENT_DATA).toString()

        if (mySharedPreference.getFamilyMemberAdded()) {
            jsonFamily = arguments?.getString(Constant.FAMILY_FRAGMENT_DATA).toString()
            Log.d(TAG, "Family Details: $jsonFamily")
            mySharedPreference.setFamilyMemberAdded(false)
        }

        binding.btnNextThird.setOnClickListener {
            strSocialCategoryOther = binding.etOtherInSocialCategory.text.toString()
            strReligiousCategoryOther = binding.etOtherReligious.text.toString()
            strUnmarriedSonBrother = binding.etUnmarriedSonBrother.text.toString()
            strUnmarriedDaughterSister = binding.etUnmarriedDaughterSister.text.toString()
            strDivorceeWidow = binding.etDivorceeWidow.text.toString()
            strMinorOrphan = binding.etMinorOrphan.text.toString()
            strAnnualIncome = binding.etAnnualIncome.text.toString()
            strSpecifyOtherAssistance = binding.etIncomeRestorationAssistance.text.toString()
            strAnyOtherAssetDescription = binding.etAnyOtherDescription.text.toString()
            strRelocationAffectExplanation = binding.etRelocationAffectExplanation.text.toString()
            strLoanExplanation = binding.etLoanExplanation.text.toString()
            strHouseFromLoanExplanation = binding.etHouseExplanation.text.toString()
            strEmploymentExplanation = binding.etEmploymentExplanation.text.toString()
            strTrainingExplanation = binding.etTrainingExplanation.text.toString()
            strOtherGovernmentSchemeExplanation = binding.etAnyOtherExplanation.text.toString()

            val rgSocialCategory: Int? = binding.rgSocialCategory.selectedRadioButtonId
            val rbSocialCategory = rgSocialCategory?.let { view.findViewById<RadioButton>(it) }

            val rgReligiousCategory: Int? = binding.rgReligiousCategory.selectedRadioButtonId
            val rbReligiousCategory =
                rgReligiousCategory?.let { view.findViewById<RadioButton>(it) }

            val rgAdditionalLand: Int = binding.rgAdditionalLand.checkedRadioButtonId
            val rbAdditionalLand = rgAdditionalLand.let { view.findViewById<RadioButton>(it) }


            val rgRelocationOption: Int = binding.rgRelocationOption.checkedRadioButtonId
            val rbRelocationOption = rgRelocationOption.let { view.findViewById<RadioButton>(it) }

            val rgLandCompensationOption: Int = binding.rgRelocationOption.checkedRadioButtonId
            val rbLandCompensationOption = rgLandCompensationOption.let { view.findViewById<RadioButton>(it) }

            val rgIncomeRestorationAssistance: Int = binding.rgIncomeRestorationAssistance.checkedRadioButtonId
            val rbIncomeRestorationAssistance = rgIncomeRestorationAssistance.let { view.findViewById<RadioButton>(it) }

            val rgFinancialDecisionMaking: Int = binding.rgFinancialDecisionMaking.checkedRadioButtonId
            val rbFinancialDecisionMaking = rgFinancialDecisionMaking.let { view.findViewById<RadioButton>(it) }

            val rgSocialDecisionMaking: Int = binding.rgSocialDecisionMaking.checkedRadioButtonId
            val rbSocialDecisionMaking = rgSocialDecisionMaking.let { view.findViewById<RadioButton>(it) }

            val rgHomesteadOrFarmLand: Int = binding.rgHomesteadOrFarmLand.checkedRadioButtonId
            val rbHomesteadOrFarmLand = rgHomesteadOrFarmLand.let { view.findViewById<RadioButton>(it) }

            val rgHouse: Int = binding.rgHouse.checkedRadioButtonId
            val rbHouse = rgHouse.let { view.findViewById<RadioButton>(it) }

            val rgFourWheeler: Int = binding.rgFourWheeler.checkedRadioButtonId
            val rbFourWheeler = rgFourWheeler.let { view.findViewById<RadioButton>(it) }

            val rgTwoWheeler: Int = binding.rgTwoWheeler.checkedRadioButtonId
            val rbTwoWheeler = rgTwoWheeler.let { view.findViewById<RadioButton>(it) }

            val rgCellPhone: Int = binding.rgCellPhone.checkedRadioButtonId
            val rbCellPhone = rgCellPhone.let { view.findViewById<RadioButton>(it) }

            val rgPersonalComputer: Int = binding.rgPersonalComputer.checkedRadioButtonId
            val rbPersonalComputer = rgPersonalComputer.let { view.findViewById<RadioButton>(it) }

            val rgAnyOtherAsset: Int = binding.rgAnyOtherAsset.checkedRadioButtonId
            val rbAnyOtherAsset = rgAnyOtherAsset.let { view.findViewById<RadioButton>(it) }

            val rgBankAccount: Int = binding.rgBankAccount.checkedRadioButtonId
            val rbBankAccount = rgBankAccount.let { view.findViewById<RadioButton>(it) }

            val rgSHGMember: Int = binding.rgSHGMember.checkedRadioButtonId
            val rbSHGMember = rgSHGMember.let { view.findViewById<RadioButton>(it) }

            val rgRelocationAffect: Int = binding.rgRelocationAffect.checkedRadioButtonId
            val rbRelocationAffect = rgRelocationAffect.let { view.findViewById<RadioButton>(it) }

            val rgLoan: Int = binding.rgLoan.checkedRadioButtonId
            val rbLoan = rgLoan.let { view.findViewById<RadioButton>(it) }

            val rgHouseFromLoan: Int = binding.rgHouseFromLoan.checkedRadioButtonId
            val rbHouseFromLoan = rgHouseFromLoan.let { view.findViewById<RadioButton>(it) }

            val rgEmployment: Int = binding.rgEmployment.checkedRadioButtonId
            val rbEmployment = rgEmployment.let { view.findViewById<RadioButton>(it) }

            val rgTraining: Int = binding.rgTraining.checkedRadioButtonId
            val rbTraining = rgTraining.let { view.findViewById<RadioButton>(it) }

            val rgAnyOtherForScheme: Int = binding.rgAnyOtherForScheme.checkedRadioButtonId
            val rbAnyOtherForScheme = rgAnyOtherForScheme.let { view.findViewById<RadioButton>(it) }


            strRgSocialCategory = rbSocialCategory?.text.toString()
            if (strRgSocialCategory == "Other") {
                if (binding.etOtherInSocialCategory.text.isEmpty()) {
                    binding.etOtherInSocialCategory.error =
                        "Please Specify Other in Social Category"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgReligiousCategory = rbReligiousCategory?.text.toString()
            if (strRgReligiousCategory == "Other") {
                if (binding.etOtherInSocialCategory.text.isEmpty()) {
                    binding.etOtherInSocialCategory.error =
                        "Please Specify Other in Religious Category"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgAdditionalLand = rbAdditionalLand?.text.toString()
            strRgResettlementRelocationOption = rbRelocationOption?.text.toString()
            strRgLandCompensationOption = rbLandCompensationOption?.text.toString()

            strRgIncomeRestorationAssistance = rbIncomeRestorationAssistance?.text.toString()
            if (strRgIncomeRestorationAssistance == "Other") {
                if (binding.etIncomeRestorationAssistance.text.isEmpty()) {
                    binding.etIncomeRestorationAssistance.error =
                        "Please Specify Other in Restoration Assistance"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgFinancialDecisionMaking = rbFinancialDecisionMaking?.text.toString()
            strRgSocialDecisionMaking = rbSocialDecisionMaking?.text.toString()
            strRgHomesteadOrFarmLand = rbHomesteadOrFarmLand?.text.toString()
            strRgHouse = rbHouse?.text.toString()
            strRgFourWheeler = rbFourWheeler?.text.toString()
            strRgTwoWheeler = rbTwoWheeler?.text.toString()
            strRgCellPhone = rbCellPhone?.text.toString()
            strRgPersonalComputer = rbPersonalComputer?.text.toString()

            strRgAnyOtherAsset = rbAnyOtherAsset?.text.toString()
            if (strRgAnyOtherAsset == "Any Other") {
                if (binding.etAnyOtherDescription.text.isEmpty()) {
                    binding.etAnyOtherDescription.error =
                        "Please Specify any Other Asset"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgBankAccount = rbBankAccount?.text.toString()
            strRgSHGMember = rbSHGMember?.text.toString()

            strRgRelocationAffect = rbRelocationAffect?.text.toString()
            if (strRgAnyOtherAsset == "Yes") {
                if (binding.etRelocationAffectExplanation.text.isEmpty()) {
                    binding.etRelocationAffectExplanation.error =
                        "Please Explain"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgLoan = rbLoan?.text.toString()
            if (strRgLoan == "Yes") {
                if (binding.etLoanExplanation.text.isEmpty()) {
                    binding.etLoanExplanation.error = "Name of Scheme"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgHouseFromLoan = rbHouseFromLoan?.text.toString()
            if (strRgHouseFromLoan == "Yes") {
                if (binding.etHouseExplanation.text.isEmpty()) {
                    binding.etHouseExplanation.error = "Name of Scheme"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgEmployment = rbEmployment?.text.toString()
            if (strRgEmployment == "Yes") {
                if (binding.etEmploymentExplanation.text.isEmpty()) {
                    binding.etEmploymentExplanation.error = "Name of Scheme"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }
            strRgTraining = rbTraining?.text.toString()
            if (strRgTraining == "Yes") {
                if (binding.etTrainingExplanation.text.isEmpty()) {
                    binding.etTrainingExplanation.error = "Name of Scheme"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            strRgOtherGovernmentScheme = rbAnyOtherForScheme?.text.toString()
            if (strRgTraining == "Yes") {
                if (binding.etAnyOtherExplanation.text.isEmpty()) {
                    binding.etAnyOtherExplanation.error = "Name of Scheme"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }


            val rootObject = JSONObject()
            rootObject.put("Social Category", strRgSocialCategory)
            rootObject.put("Social Category Other", strSocialCategoryOther)
            rootObject.put("Religious Category", strRgReligiousCategory)
            rootObject.put("Religious Category Other", strReligiousCategoryOther)
            rootObject.put("Unmarried Son/Brother", strUnmarriedSonBrother)
            rootObject.put("Unmarried Daughter/Sister", strUnmarriedDaughterSister)
            rootObject.put("Divorcee/Widow", strDivorceeWidow)
            rootObject.put("Minor/Orphan", strMinorOrphan)
            rootObject.put("Annual Income", strAnnualIncome)
            rootObject.put("Additional Land", strRgAdditionalLand)
            rootObject.put("Resettlement/Relocation Option", strRgResettlementRelocationOption)
            rootObject.put("Land Compensation Option", strRgLandCompensationOption)
            rootObject.put("Income Restoration Assistance", strRgIncomeRestorationAssistance)
            rootObject.put("Specify Other Assistance", strSpecifyOtherAssistance)
            rootObject.put("Financial Decision Making", strRgFinancialDecisionMaking)
            rootObject.put("Social Decision Making", strRgSocialDecisionMaking)
            rootObject.put("Homestead/Farm Land", strRgHomesteadOrFarmLand)
            rootObject.put("House", strRgHouse)
            rootObject.put("Four Wheeler", strRgFourWheeler)
            rootObject.put("Two Wheeler", strRgTwoWheeler)
            rootObject.put("Cell Phone", strRgCellPhone)
            rootObject.put("Personal Computer", strRgPersonalComputer)
            rootObject.put("Any Other Asset", strRgAnyOtherAsset)
            rootObject.put("Any Other Asset Description", strAnyOtherAssetDescription)
            rootObject.put("Bank Account", strRgBankAccount)
            rootObject.put("SHG Member", strRgSHGMember)
            rootObject.put("Relocation Affect", strRgRelocationAffect)
            rootObject.put("Relocation Affect Explanation", strRelocationAffectExplanation)
            rootObject.put("Loan", strRgLoan)
            rootObject.put("Loan Explanation", strLoanExplanation)
            rootObject.put("House From Loan", strRgHouseFromLoan)
            rootObject.put("House From Loan Explanation", strHouseFromLoanExplanation)
            rootObject.put("Employment", strRgEmployment)
            rootObject.put("Employment Explanation", strEmploymentExplanation)
            rootObject.put("Training", strRgTraining)
            rootObject.put("Training Explanation", strTrainingExplanation)
            rootObject.put("Other Government Scheme", strRgOtherGovernmentScheme)
            rootObject.put("Other Government Scheme Explanation", strOtherGovernmentSchemeExplanation)

            val jsonObject = JSONObject(jsonString)
            val jsonFamilyObject = JSONObject(jsonFamily)
            val merged = mergeJsonObjects3(rootObject, jsonObject, jsonFamilyObject)

            if (isNavigate) {
                sendData(merged)
                Log.i(TAG, "Final mergedObj: $merged")

            }
        }


            binding.btnBackThird.setOnClickListener {
                findNavController().navigateUp()
            }
            binding.btnFamilyDetailAdd.setOnClickListener {
                findNavController().navigate(R.id.frag_family_member_form)
                mySharedPreference.setFamilyMemberAdded(true)
            }
        }


    private fun sendData(rootObject: JSONObject){
        val recordId = mySharedPreference.getRecordId().toString()
        val sessionKey = mySharedPreference.getSessionKey().toString()
        val userId = mySharedPreference.getUserId().toString()
        val date = getCurrentDateTime()
        val dateTimeStamp = date.toString()
        val requestData = SendData(recordId, dateTimeStamp, sessionKey,userId,rootObject)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = NetworkService.api.sendData(requestData)
                if (response.success) {

                    findNavController().navigate(R.id.frag_dashboard)
                    mySharedPreference.setSyncId(response.syncRecordID)

                    val alertDialogBuilder = AlertDialog.Builder(requireContext())
                    alertDialogBuilder.setTitle("Data Successfully Inserted")
                    alertDialogBuilder.setMessage("Sync Record ID: ${response.syncRecordID}")
                    alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    val alertDialog = alertDialogBuilder.create()
                    alertDialog.show()

                    Log.d("TSF_APPS", "Success: ${response.syncRecordID}")

                } else {
                    Log.d("TSF_APPS", "Success Fail: ${response.errorMessage}")

                }
            } catch (e: Exception) {
                Log.i("TSF_APPS", "Error ${e.message}")
            }
        }

    }



}