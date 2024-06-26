package com.example.myapplicationdip.classProj

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplicationdip.Constants.Constants
import com.example.myapplicationdip.FoodConsumptionSchedule
import com.example.myapplicationdip.MainActivity
import com.example.myapplicationdip.R

class PersonalInfo {
    var weight = 0
    var height = 0
    var age = 0
    var gender = 0
    var physActRatio : Float = 0F
    var norm_calorie = 0

    var time = 0
    var interval = 0

    fun getGender(context : Context) : String{
        if(gender == 0){
            return context.getString(R.string.man)
        }else{
            return context.getString(R.string.girl)
        }
    }

    fun getActiv(context : Context) : String {
        when (physActRatio) {
            1.2F -> return context.getString(R.string.nots_active)
            1.6375F -> return context.getString(R.string.very_active)
            else -> return context.getString(R.string.actives)
        }
    }
}