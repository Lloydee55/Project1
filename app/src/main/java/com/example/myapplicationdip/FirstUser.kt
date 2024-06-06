package com.example.myapplicationdip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplicationdip.Constants.Constants
import com.example.myapplicationdip.classProj.PersonalInfo
import com.example.myapplicationdip.databinding.ActivityFirstUserBinding
import com.example.myapplicationdip.db.DBHelperPerson

class FirstUser : AppCompatActivity() {
    private var index  = 0
    private var dB = DBHelperPerson(this, null)
    private var person = PersonalInfo()

    private lateinit var bin : ActivityFirstUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityFirstUserBinding.inflate(layoutInflater)
        setContentView(bin.root)

        person = dB.readDbData()
        if(person.age != 0){
            var int = Intent(this, Prifile::class.java)
            startActivity(int)
        }

        bin.editText.visibility = View.GONE
        bin.btFurther.visibility = View.GONE
        bin.LayBtHoise3.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        bin.textIndex.text = "$index" + getString(R.string.out_five)
    }

    private fun indexNext() {
        index++
        bin.textIndex.text = "$index" + getString(R.string.out_five)
    }

    private fun genderSelection() {
        bin.apply {
            textInfo.text = getString(R.string.enter_height)
            editText.visibility = View.VISIBLE
            btFurther.visibility = View.VISIBLE
            LayBtHoise1.visibility = View.GONE
            LayBtHoise2.visibility = View.GONE
        }
    }

    private fun activitySelection() {
        val k : Float
        person.apply {
            k = if (gender == 1) {
                9.99F * weight + 6.25F * height - 4.92F * age - 161
            } else {
                9.99F * weight + 6.25F * height - 4.92F * age + 5
            }
        }
        bin.apply {

            editText.visibility = View.GONE
            btFurther.visibility = View.GONE
            tvChOne.text = getString(R.string.calorie_calculation)
            tvChTwoo.text = getString(R.string.refil)
            LayBtHoise3.visibility = View.GONE

            textResult.text = k.toInt().toString()
            textInfo.text = getString(R.string.metabolism)
        }

    }
    fun onClickFurther(view: View) {
        val diap : Int
        if(bin.editText.text.toString() != "") {
            diap = bin.editText.text.toString().toInt()
            bin.editText.hint = ""
            if (index == 1 && diap in 150..230){
                person.height = bin.editText.text.toString().toInt()
                bin.textInfo.text = getString(R.string.enter_weight)
                bin.editText.text.clear()
                indexNext()
            }else if(index == 2 && diap in 40..180){
                person.weight = bin.editText.text.toString().toInt()
                bin.textInfo.text = getString(R.string.enter_age)
                bin.editText.text.clear()
                indexNext()
            }else if (index == 3 && diap in 10..100){
                person.age = bin.editText.text.toString().toInt()
                bin.apply{

                    editText.visibility = View.GONE
                    btFurther.visibility = View.GONE
                    LayBtHoise1.visibility = View.VISIBLE
                    tvChOne.text = getString(R.string.active)
                    LayBtHoise2.visibility = View.VISIBLE
                    tvChTwoo.text = getString(R.string.very_active)
                    LayBtHoise3.visibility = View.VISIBLE
                    tvChThree.text = getString(R.string.not_active)

                    textInfo.text = getString(R.string.enter_lifestyle)
                    editText.text.clear()

                    indexNext()
                }
            }else{
                bin.editText.text.clear()
                bin.editText.hint = getString(R.string.blank_fiend)
            }
        } else {
            bin.editText.hint = getString(R.string.blank_fiend)
        }
        bin.editText.text.clear()
    }

    fun onClickCh1(view: View) {
        if(index == 0) {
            genderSelection()
            indexNext()

        }else if(index >= 5){
            val result = bin.textResult.text.toString().toInt() * person.physActRatio
            val result2 = result.toInt()

            val db = DBHelperPerson(this, null)

            person.apply {
                db.addName(weight, height , age, physActRatio, gender)
            }
            Toast.makeText(this, " Сохранено", Toast.LENGTH_LONG).show()

            val int = Intent(this, CaloriesBzhu::class.java)
            int.putExtra(Constants.TRANC_CAL_ACT, result2)
            startActivity(int)

        }
        else {
            person.physActRatio = 1.375F
            activitySelection()
            indexNext()
        }
    }
    fun onClickCh2(view: View) {
        if (index == 0) {
            person.gender = 1
            genderSelection()
            indexNext()
        }else if(index >= 5){
            index = 0
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else {
            person.physActRatio = 1.6375F
            activitySelection()
            indexNext()
        }
    }
    fun onClickCh3(view: View) {

        person.physActRatio = 1.2F
        activitySelection()
        indexNext()
    }

}