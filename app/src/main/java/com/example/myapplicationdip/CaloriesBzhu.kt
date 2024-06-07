package com.example.myapplicationdip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplicationdip.Constants.Constants
import com.example.myapplicationdip.databinding.ActivityCaloriesBzhuBinding

@Suppress("DEPRECATION")
class CaloriesBzhu : AppCompatActivity(){
    private lateinit var bindingClass : ActivityCaloriesBzhuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCaloriesBzhuBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        var per = intent.getIntExtra(Constants.TRANC_CAL_ACT, 0)
        var per2 : Int
        bindingClass.apply {
            percentagesProt.text = "20"
            percentagesFast.text = "30"
            percentagesCorboh.text = "50"

            textMainCalculations.text = per.toString()
            per2 = per * 30 / 100 / 4
            gramsProt.text = per2.toString()
            per2 = per * 20 / 100 / 9
            gramsFats.text = per2.toString()
            per2 = per * 50 / 100 / 4
            gramsCarboh.text = per2.toString()
        }
    }

    fun onClickBack(view: View) {
        finish()
    }

    fun onClickSave(view: View) {
        var int = Intent(this, MainActivity::class.java)
        startActivity(int)
    }

}