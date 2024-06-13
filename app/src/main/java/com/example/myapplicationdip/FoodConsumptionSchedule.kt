package com.example.myapplicationdip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplicationdip.Constants.Constants
import com.example.myapplicationdip.classProj.PersonalInfo
import com.example.myapplicationdip.databinding.ActivityFoodConsumptionScheduleBinding
import com.example.myapplicationdip.db.DBHelperPerson

class FoodConsumptionSchedule : AppCompatActivity() {
    private lateinit var bin: ActivityFoodConsumptionScheduleBinding
    private var person = PersonalInfo()
    val db = DBHelperPerson(this, null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin =ActivityFoodConsumptionScheduleBinding.inflate(layoutInflater)
        setContentView(bin.root)

        person = db.readDbData()

        bin.Further.setOnClickListener(){
            bin.apply {

                val interval : Int
                val numMeal : Int

                if(edMealInterval.text.toString() != "" && edNumberMeals.text.toString() != "") {
                    interval = edMealInterval.text.toString().toInt()
                    numMeal = edNumberMeals.text.toString().toInt()


                    if(interval !in 5 downTo 1 && numMeal !in 4 downTo 1) {
                        edMealInterval.text.clear()
                        edMealInterval.hint = getString(R.string.lala)
                        edNumberMeals.text.clear()
                        edNumberMeals.hint = getString(R.string.dada)
                    }else if(interval !in 6 downTo 4){

                        edMealInterval.text.clear()
                        edMealInterval.hint = getString(R.string.lala)

                    }else if(numMeal !in 4 downTo 1){

                        edNumberMeals.text.clear()
                        edNumberMeals.hint = getString(R.string.dada)

                    }else{

                        val int = Intent(this@FoodConsumptionSchedule, CaloriesBzhu::class.java)

                        val time = 3
                        val inter = 5

                        person.apply {
                            db.updateItem(weight, height, age, physActRatio, gender, norm_calorie, time, inter,1)
                        }
                        startActivity(int)
                    }
                }else{
                    edMealInterval.text.clear()
                    edMealInterval.hint = getString(R.string.blank_fiend)
                    edNumberMeals.text.clear()
                    edNumberMeals.hint = getString(R.string.blank_fiend)
                }
            }
        }
    }
}