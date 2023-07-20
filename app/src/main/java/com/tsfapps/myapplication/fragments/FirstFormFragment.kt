package com.tsfapps.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.tsfapps.myapplication.databinding.FragmentFirstFormBinding
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.R

class FirstFormFragment : Fragment() {
    private var _binding: FragmentFirstFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnNextFirst.setOnClickListener {
            findNavController().navigate(R.id.frag_second_form)
        }
        binding.btnBackFirst.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}