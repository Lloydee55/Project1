package com.example.myapplicationdip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationdip.activAdapter.ActivAdapter
import com.example.myapplicationdip.activAdapter.ItemsViewModel
import com.example.myapplicationdip.classProj.PersonalInfo
import com.example.myapplicationdip.databinding.ActivityFragmentHomeBinding
import com.example.myapplicationdip.db.DBHelperPerson

class fragment_home : AppCompatActivity() {
    private var dB = DBHelperPerson(this, null)
    private var person = PersonalInfo()

    private lateinit var bin: ActivityFragmentHomeBinding
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_home)

        person = dB.readDbData()
        if(person.age == 0){
            var int = Intent(this, FirstUser::class.java)
            startActivity(int)
        }
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        bin.btPlusList.setOnClickListener{
            data.add(ItemsViewModel("34"))
            val adapter = ActivAdapter(data)
            recyclerview.adapter = adapter
        }
    }
}
