package com.example.myapplicationdip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationdip.databinding.ActivityPrifileBinding
import com.example.myapplicationdip.db.DBHelperPerson

class Prifile : AppCompatActivity() {

    private lateinit var bind : ActivityPrifileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityPrifileBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val db = DBHelperPerson(this, null)
        var person = db.readDbData()

        bind.editTextText2.setText("${person.weight}")
        bind.editTextText1.setText("${person.height}")
        bind.editTextText5.setText("${person.age}")
        bind.editTextText3.setText(person.getActiv(this))
        bind.editTextText4.setText(person.getGender(this))

    }
}