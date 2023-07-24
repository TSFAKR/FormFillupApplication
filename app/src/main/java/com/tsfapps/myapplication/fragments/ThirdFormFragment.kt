package com.tsfapps.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentThirdFormBinding


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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdFormBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNextThird.setOnClickListener {
            findNavController().navigate(R.id.frag_fourth_form)

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


            Log.i("EditTextLogs", "strSocialCategoryOther: ${binding.etOtherInSocialCategory.text.toString()}")
            Log.i("EditTextLogs", "strReligiousCategoryOther: ${binding.etOtherReligious.text.toString()}")
            Log.i("EditTextLogs", "strUnmarriedSonBrother: ${binding.etUnmarriedSonBrother.text.toString()}")
            Log.i("EditTextLogs", "strUnmarriedDaughterSister: ${binding.etUnmarriedDaughterSister.text.toString()}")
            Log.i("EditTextLogs", "strDivorceeWidow: ${binding.etDivorceeWidow.text.toString()}")
            Log.i("EditTextLogs", "strMinorOrphan: ${binding.etMinorOrphan.text.toString()}")
            Log.i("EditTextLogs", "strAnnualIncome: ${binding.etAnnualIncome.text.toString()}")
            Log.i("EditTextLogs", "strSpecifyOtherAssistance: ${binding.etIncomeRestorationAssistance.text.toString()}")
            Log.i("EditTextLogs", "strAnyOtherAssetDescription: ${binding.etAnyOtherDescription.text.toString()}")
            Log.i("EditTextLogs", "strRelocationAffectExplanation: ${binding.etRelocationAffectExplanation.text.toString()}")
            Log.i("EditTextLogs", "strLoanExplanation: ${binding.etLoanExplanation.text.toString()}")
            Log.i("EditTextLogs", "strHouseFromLoanExplanation: ${binding.etHouseExplanation.text.toString()}")
            Log.i("EditTextLogs", "strEmploymentExplanation: ${binding.etEmploymentExplanation.text.toString()}")
            Log.i("EditTextLogs", "strTrainingExplanation: ${binding.etTrainingExplanation.text.toString()}")
            Log.i("EditTextLogs", "strOtherGovernmentSchemeExplanation: ${binding.etAnyOtherExplanation.text.toString()}")

            Log.i("RadioButtonLogs", "strRgSocialCategory: $strRgSocialCategory")
            Log.i("RadioButtonLogs", "strRgReligiousCategory: $strRgReligiousCategory")
            Log.i("RadioButtonLogs", "strRgAdditionalLand: $strRgAdditionalLand")
            Log.i("RadioButtonLogs", "strRgResettlementRelocationOption: $strRgResettlementRelocationOption")
            Log.i("RadioButtonLogs", "strRgLandCompensationOption: $strRgLandCompensationOption")
            Log.i("RadioButtonLogs", "strRgIncomeRestorationAssistance: $strRgIncomeRestorationAssistance")
            Log.i("RadioButtonLogs", "strRgFinancialDecisionMaking: $strRgFinancialDecisionMaking")
            Log.i("RadioButtonLogs", "strRgSocialDecisionMaking: $strRgSocialDecisionMaking")
            Log.i("RadioButtonLogs", "strRgHomesteadOrFarmLand: $strRgHomesteadOrFarmLand")
            Log.i("RadioButtonLogs", "strRgHouse: $strRgHouse")
            Log.i("RadioButtonLogs", "strRgFourWheeler: $strRgFourWheeler")
            Log.i("RadioButtonLogs", "strRgTwoWheeler: $strRgTwoWheeler")
            Log.i("RadioButtonLogs", "strRgCellPhone: $strRgCellPhone")
            Log.i("RadioButtonLogs", "strRgPersonalComputer: $strRgPersonalComputer")
            Log.i("RadioButtonLogs", "strRgAnyOtherAsset: $strRgAnyOtherAsset")
            Log.i("RadioButtonLogs", "strRgBankAccount: $strRgBankAccount")
            Log.i("RadioButtonLogs", "strRgSHGMember: $strRgSHGMember")
            Log.i("RadioButtonLogs", "strRgRelocationAffect: $strRgRelocationAffect")
            Log.i("RadioButtonLogs", "strRgLoan: $strRgLoan")
            Log.i("RadioButtonLogs", "strRgHouseFromLoan: $strRgHouseFromLoan")
            Log.i("RadioButtonLogs", "strRgEmployment: $strRgEmployment")
            Log.i("RadioButtonLogs", "strRgTraining: $strRgTraining")
            Log.i("RadioButtonLogs", "strRgOtherGovernmentScheme: $strRgOtherGovernmentScheme")


        }


            binding.btnBackThird.setOnClickListener {
                findNavController().navigateUp()
            }
            binding.btnFamilyDetailAdd.setOnClickListener {
                findNavController().navigate(R.id.frag_family_member_form)
            }
        }



}