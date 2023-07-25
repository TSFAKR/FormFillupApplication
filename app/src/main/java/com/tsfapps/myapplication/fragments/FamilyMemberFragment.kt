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
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentFamilyMemberBinding
import org.json.JSONObject


class FamilyMemberFragment : Fragment() {

    private var isNavigate: Boolean = false


    private var _binding: FragmentFamilyMemberBinding? = null
    private val binding get() = _binding!!

    private var strEdFamilyName: String = ""
    private var strEdAge: String = ""
    private var strRgSex: String = ""
    private var strEdRelationship: String = ""
    private var strSpinnerMaritalStatus: String = ""
    private var strSpinnerEducation: String = ""
    private var strSpinnerOccupation: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFamilyMemberBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val maritalStatus = arrayOf("Select Marital Status", "Single", "Married", "Divorced", "Widowed", "Separated")
        val spinnerMaritalStatus = binding.spinnerMaritalStatus
        if (spinnerMaritalStatus != null) {
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, maritalStatus)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMaritalStatus.adapter = adapter

            spinnerMaritalStatus.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedMaritalStatus = maritalStatus[position]
                        strSpinnerMaritalStatus = selectedMaritalStatus
                        Log.i("SpinnerLog", "Selected Marital Status: $strSpinnerMaritalStatus")
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Handle nothing selected, if needed
                    }
                }
            }

        val education = arrayOf("Select Education", "Illiterate", "Literate", "Up to middle", "Below metric",
            "Metric","Graduate","Above Graduate")
        val spinnerEducation = binding.spinnerEducation
        if (spinnerEducation != null) {
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, education)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerEducation.adapter = adapter

            spinnerEducation.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedEducation = education[position]
                        strSpinnerEducation = selectedEducation
                        Log.i("SpinnerLog", "Selected Marital Status: $selectedEducation")
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Handle nothing selected, if needed
                    }
                }
        }

        val occupation = arrayOf(
            "Select Occupation",
            "Service",
            "Business",
            "Agriculture",
            "Study",
            "Retired",
            "Labour",
            "Unemployed",
            "Below 6 years",
            "Old/Inactive",
        )

        val spinnerOccupation = binding.spinnerOccupation
        if (spinnerOccupation != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, occupation)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerOccupation.adapter = adapter

            // Set a listener to handle the selected item in the spinner
            spinnerOccupation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // Handle the selected item here
                    val selectedOccupation = occupation[position]
                    if (selectedOccupation != "Select Occupation") {
                        strSpinnerOccupation = selectedOccupation
                        Log.i("SpinnerLog", "Selected Occupation: $strSpinnerOccupation")
                    } else {
                        // Handle the case when the hint item is selected
                        Log.i("SpinnerLog", "No occupation selected.")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected, if needed
                }
            }
        }


        binding.btnAddFamily.setOnClickListener {
            findNavController().navigate(R.id.frag_third_form)

            strEdFamilyName = binding.edFamilyName.text.toString()
            strEdAge = binding.edAge.text.toString()

            val rgSex: Int = binding.rgSex.checkedRadioButtonId
            val rbSex = rgSex.let { view.findViewById<RadioButton>(it) }
            strRgSex = rbSex?.text.toString()

            strEdRelationship = binding.edRelationship.text.toString()


            val rootObject = JSONObject()
            rootObject.put("Family Name", strEdFamilyName)
            rootObject.put("Age", strEdAge)
            rootObject.put("Sex", strRgSex)
            rootObject.put("Relationship", strEdRelationship)
            rootObject.put("Marital Status", strSpinnerMaritalStatus)
            rootObject.put("Education", strSpinnerEducation)
            rootObject.put("Occupation", strSpinnerOccupation)

            val jsonString = rootObject.toString()
            Log.i("JSONLog", jsonString)

            Toast.makeText(requireContext(), "1 Family member added.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
