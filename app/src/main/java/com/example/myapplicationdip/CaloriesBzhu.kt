package com.example.myapplicationdip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplicationdip.Constants.Constants
import com.example.myapplicationdip.classProj.PersonalInfo
import com.example.myapplicationdip.databinding.ActivityCaloriesBzhuBinding
import com.example.myapplicationdip.db.DBHelperFood
import com.example.myapplicationdip.db.DBHelperPerson

@Suppress("DEPRECATION")
class CaloriesBzhu : AppCompatActivity(){
    private lateinit var bindingClass : ActivityCaloriesBzhuBinding
    private var person = PersonalInfo()
    val db = DBHelperPerson(this, null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCaloriesBzhuBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        person = db.readDbData()

        var per2 : Int
        bindingClass.apply {
            percentagesProt.text = "20"
            percentagesFast.text = "30"
            percentagesCorboh.text = "50"

            val per = person.norm_calorie

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
        val int = Intent(this, MainActivity::class.java)
        startActivity(int)
        finish()
    }

    fun onClickSave(view: View) {


        val str = "Рис, Картошка, Яйцо, молоко"
        val list: List<String> = str.split(",").toList()


        /*var dBProd = DBHelperFood(this, null)
        dBProd.addName(list[1], 56, 87, 45, 3444, 100)
        dBProd.addName(list[1], 56, 87, 45, 3444, 100)
        dBProd.addName(list[1], 56, 87, 45, 3444, 100)
        dBProd.addName(list[1], 56, 87, 45, 3444, 100)
        dBProd.addName(list[1], 56, 87, 45, 3444, 100)
        dBProd.addName(list[2], 56, 87, 45, 3444, 100)*/

        person.apply {
            db.updateItem(weight, height, age, physActRatio, gender, norm_calorie, time, interval, 1)
        }
        Toast.makeText(this, " Сохранено", Toast.LENGTH_LONG).show()
        var int = Intent(this, MainActivity::class.java)
        startActivity(int)
    }

}