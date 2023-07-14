package com.tsfapps.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.FragmentFifthFormBinding


class FifthFormFragment : Fragment() {

    private var _binding: FragmentFifthFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFifthFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNextFifth.setOnClickListener {
            findNavController().navigate(R.id.frag_dashboard)
        }

    }

}