package com.example.myapplicationdip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationdip.R
import com.example.myapplicationdip.activAdapter.ActivAdapter
import com.example.myapplicationdip.activAdapter.ItemsViewModel
import com.example.myapplicationdip.databinding.FragmentProfileBinding
import com.example.myapplicationdip.db.DBHelperPerson

// TODO: Rename parameter arguments, choose names that match

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bin = FragmentProfileBinding.inflate(inflater)

        val db = DBHelperPerson(requireContext(), null)
        var person = db.readDbData()

        bin.editTextText2.setText("${person.weight}")
        bin.editTextText1.setText("${person.height}")
        bin.editTextText5.setText("${person.age}")
        bin.editTextText3.setText(person.getActiv(requireContext()))
        bin.editTextText4.setText(person.getGender(requireContext()))

        return bin.root
    }
 }