package com.example.myapplicationdip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationdip.activAdapter.ActivAdapter
import com.example.myapplicationdip.activAdapter.ItemsViewModel
import com.example.myapplicationdip.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bin = FragmentHomeBinding.inflate(inflater)

        bin.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val data = ArrayList<ItemsViewModel>()
        bin.btPlusList.setOnClickListener{
            data.add(ItemsViewModel("34"))
            val adapter = ActivAdapter(data)
            bin.recyclerview.adapter = adapter
        }
        // Inflate the layout for this fragment
        return bin.root
    }
}