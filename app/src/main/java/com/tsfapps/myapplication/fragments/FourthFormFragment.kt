package com.tsfapps.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentFourthFormBinding
import org.json.JSONObject


class FourthFormFragment : Fragment() {

    private var isNavigate: Boolean = false


    private var _binding: FragmentFourthFormBinding? = null
    private val binding get() = _binding!!

    private var strEdCensusQuestionnaire: String = ""
    private var strEdOwnerName: String = ""
    private var strEdOccupierName: String = ""
    private var strOccupierFatherName: String = ""
    private var strSpinnerStatusOfOccupier: String = ""
    private var strRgSocialCategory: String = ""
    private var strSocialCategoryOther: String = ""

    private var strRgReligiousCategory: String = ""
    private var strReligiousCategoryOther: String = ""

    private var strEdMale: String = ""
    private var strEdFemale: String = ""
    private var strEdTotal: String = ""
    private var strRgVulnerabilityStatus: String = ""
    private var strEdOtherVulnerabilityStatus: String = ""
    private var strEdAnnualIncome: String = ""
    private var strRgIncomeRestoration: String = ""
    private var strEdOtherInIncomeRestoration: String = ""





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthFormBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val statusOfOccupier = arrayOf(
            "Select Status of Occupier",
            "Agricultural Laborer",
            "Agricultural Tenant/Lessee",
            "Sharecropper",
            "Tenant in structure",
            "Employee/ wage earner in Residential/Commercial Structure",
        )

        val spinnerStatusOfOccupier = binding.spinnerStatusOfOccupier
        if (spinnerStatusOfOccupier != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, statusOfOccupier)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerStatusOfOccupier.adapter = adapter

            // Set a listener to handle the selected item in the spinner
            spinnerStatusOfOccupier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // Handle the selected item here
                    val selectedStatusOfOccupier = statusOfOccupier[position]
                    strSpinnerStatusOfOccupier = selectedStatusOfOccupier
                    Log.i("SpinnerLog", "Selected Status of Occupier: $strSpinnerStatusOfOccupier")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected, if needed
                }
            }
        }



        binding.btnNextFourth.setOnClickListener {

            findNavController().navigate(R.id.frag_dashboard)

            strEdCensusQuestionnaire = binding.edCensusQuestionnaire.text.toString()
            strEdOwnerName = binding.edOwnerName.text.toString()
            strEdOccupierName = binding.edOccupierName.text.toString()
            strOccupierFatherName = binding.edOccupierFatherName.text.toString()
            strSpinnerStatusOfOccupier //spinner validation remaining

            strSocialCategoryOther = binding.etOtherInSocialCategory.text.toString()
            strReligiousCategoryOther = binding.etOtherReligious.text.toString()
            strEdMale = binding.edMale.text.toString()
            strEdFemale = binding.edFemale.text.toString()
            strEdTotal = binding.edTotal.text.toString()
            strEdOtherVulnerabilityStatus = binding.edOtherVulnerabilityStatus.text.toString()
            strEdAnnualIncome = binding.edAnnualIncome.text.toString()
            strEdOtherInIncomeRestoration = binding.edOtherInIncomeRestoration.text.toString()

            val rgSocialCategory: Int? = binding.rgSocialCategory.selectedRadioButtonId
            val rbSocialCategory = rgSocialCategory?.let { view.findViewById<RadioButton>(it) }

            val rgReligiousCategory: Int? = binding.rgReligiousCategory.selectedRadioButtonId
            val rbReligiousCategory = rgReligiousCategory?.let { view.findViewById<RadioButton>(it) }

            val rgVulnerabilityStatus: Int = binding.rgVulnerabilityStatus.checkedRadioButtonId
            val rbVulnerabilityStatus = rgVulnerabilityStatus.let { view.findViewById<RadioButton>(it) }

            val rgIncomeRestoration: Int = binding.rgIncomeRestoration.checkedRadioButtonId
            val rbIncomeRestoration = rgIncomeRestoration.let { view.findViewById<RadioButton>(it) }


            strRgSocialCategory = rbSocialCategory?.text.toString()
            strRgReligiousCategory = rbReligiousCategory?.text.toString()
            strRgVulnerabilityStatus = rbVulnerabilityStatus?.text.toString()
            strRgIncomeRestoration = rbIncomeRestoration?.text.toString()


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

            if (strRgVulnerabilityStatus == "Other") {
                if (binding.edOtherVulnerabilityStatus.text.isEmpty()) {
                    binding.edOtherVulnerabilityStatus.error =
                        "Please Specify Other Vulnerability Status"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }
            if (strRgIncomeRestoration == "Other") {
                if (binding.edOtherInIncomeRestoration.text.isEmpty()) {
                    binding.edOtherInIncomeRestoration.error = "Please Specify Other Assistance"
                    isNavigate = false
                } else {
                    isNavigate = true
                }
            } else {
                isNavigate = true
            }

            val rootObject = JSONObject()
            rootObject.put("Census Questionnaire", strEdCensusQuestionnaire)
            rootObject.put("Owner Name", strEdOwnerName)
            rootObject.put("Occupier Name", strEdOccupierName)
            rootObject.put("Occupier Father Name", strOccupierFatherName)
            rootObject.put("Status of Occupier", strSpinnerStatusOfOccupier)
            rootObject.put("Social Category", strRgSocialCategory)
            rootObject.put("Social Category Other", strSocialCategoryOther)
            rootObject.put("Religious Category", strRgReligiousCategory)
            rootObject.put("Religious Category Other", strReligiousCategoryOther)
            rootObject.put("Male", strEdMale)
            rootObject.put("Female", strEdFemale)
            rootObject.put("Total", strEdTotal)
            rootObject.put("Vulnerability Status", strRgVulnerabilityStatus)
            rootObject.put("Other Vulnerability Status", strEdOtherVulnerabilityStatus)
            rootObject.put("Annual Income", strEdAnnualIncome)
            rootObject.put("Income Restoration", strRgIncomeRestoration)
            rootObject.put("Other in Income Restoration", strEdOtherInIncomeRestoration)

            val jsonString = rootObject.toString()
            Log.i("JSONLog", jsonString)




        }


        binding.btnBackFourth.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnFamilyDetailAdd.setOnClickListener {
            findNavController().navigate(R.id.frag_family_member_form)
        }

    }

}