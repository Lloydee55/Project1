package com.example.myapplicationdip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationdip.R
import com.example.myapplicationdip.adapters.ActivAdapter
import com.example.myapplicationdip.adapters.FoodItemsViewModel
import com.example.myapplicationdip.adapters.FoodListAdapter
import com.example.myapplicationdip.adapters.ItemsViewModel
import com.example.myapplicationdip.databinding.FragmentHomeBinding
import com.example.myapplicationdip.db.DBHelperFood
import com.example.myapplicationdip.db.DBHelperPerson

// TODO: Rename parameter arguments, choose names that match
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bin = FragmentHomeBinding.inflate(inflater)

        val db = DBHelperPerson(requireContext(), null)
        var person = db.readDbData()
        bin.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        val data = ArrayList<ItemsViewModel>()

        var time = 8

        for(i in 1..person.time) {

            data.add(ItemsViewModel("$time:00"))
            val adapter = ActivAdapter(data, requireContext())
            bin.recyclerview.adapter = adapter
            time += person.interval

        }
        return bin.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.home)
    }

}