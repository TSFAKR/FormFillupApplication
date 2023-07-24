package com.tsfapps.myapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentFamilyMemberBinding


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

        binding.btnAddFamily.setOnClickListener {
            findNavController().navigate(R.id.frag_third_form)

            strEdFamilyName = binding.edFamilyName.text.toString()
            strEdAge = binding.edAge.text.toString()

            val rgSex: Int = binding.rgSex.checkedRadioButtonId
            val rbSex = rgSex.let { view.findViewById<RadioButton>(it) }
            strRgSex = rbSex?.text.toString()

            strEdRelationship = binding.edRelationship.text.toString()

            Log.i("EditTextLog", "strEdFamilyName: $strEdFamilyName")
            Log.i("EditTextLog", "strEdAge: $strEdAge")
            Log.i("RadioLog", "strRgSex: $strRgSex")
            Log.i("EditTextLog", "strEdRelationship: $strEdRelationship")

            Toast.makeText(requireContext(), "1 Family member added.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
