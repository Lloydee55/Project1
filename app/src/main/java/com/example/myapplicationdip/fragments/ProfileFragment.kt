package com.example.myapplicationdip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationdip.R
import com.example.myapplicationdip.databinding.FragmentProfileBinding
import com.example.myapplicationdip.db.DBHelperPerson

// TODO: Rename parameter arguments, choose names that match

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bin = FragmentProfileBinding.inflate(inflater)

        bin.apply {
            edWeight.isEnabled = false
            edHeight.isEnabled = false
            edAge.isEnabled = false
            edGender.isEnabled = false
            edActivity.isEnabled = false

            var state = true
            val db = DBHelperPerson(requireContext(), null)
            var person = db.readDbData()

            edWeight.setText("${person.weight}")
            edHeight.setText("${person.height}")
            edAge.setText("${person.age}")
            edActivity.setText(person.getActiv(requireContext()))
            edGender.setText(person.getGender(requireContext()))
            tvNormCalories.text = person.norm_calorie.toString()

            floatingActionButton.setOnClickListener() {
                if (state) {
                    edWeight.isEnabled = true
                    edHeight.isEnabled = true
                    edAge.isEnabled = true
                    state = false
                } else {
                    var gen: Int
                    var k = 0.0F
                    if (edGender.text.toString() == getString(R.string.girl)) {
                        k = 9.99F * edWeight.text.toString()
                            .toInt() + 6.25F * edHeight.text.toString()
                            .toInt() - 4.92F * edAge.text.toString().toInt() - 161
                        gen = 1

                    } else {
                        k = 9.99F * edWeight.text.toString().toInt() + 6.25F * edHeight.text.toString()
                            .toInt() - 4.92F * edAge.text.toString().toInt() + 5
                        gen = 0
                    }
                    var kresult : Float
                    when (edActivity.text.toString()) {
                        "Мало активен" -> {
                            kresult = k * 1.2F
                            k = 1.2F
                        }
                        "Очень активен" -> {
                            kresult = k * 1.6375F
                            k = 1.6375F
                        }
                        else -> {
                            kresult = k * 1.375F
                            k = 1.375F
                        }
                    }


                    db.updateItem(
                        edWeight.text.toString().toInt(),
                        edHeight.text.toString().toInt(),
                        edAge.text.toString().toInt(),
                        k,
                        gen,
                        kresult.toInt(),
                        person.time,
                        person.interval,
                        1
                    )
                    person = db.readDbData()

                    edWeight.setText("${person.weight}")
                    edHeight.setText("${person.height}")
                    edAge.setText("${person.age}")
                    edActivity.setText(person.getActiv(requireContext()))
                    edGender.setText(person.getGender(requireContext()))
                    tvNormCalories.text = person.norm_calorie.toString()

                    edWeight.isEnabled = false
                    edHeight.isEnabled = false
                    edAge.isEnabled = false
                    edActivity.isEnabled = false
                    state = true
                }

            }
        }
        return bin.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.profile)
    }
}