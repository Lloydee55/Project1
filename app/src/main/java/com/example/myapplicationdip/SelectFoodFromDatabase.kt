package com.example.myapplicationdip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationdip.adapters.FoodItemsViewModel
import com.example.myapplicationdip.adapters.FoodListAdapter
import com.example.myapplicationdip.databinding.ActivitySelectFoodFromDatabaseBinding
import com.example.myapplicationdip.db.DBHelperFood

class SelectFoodFromDatabase : AppCompatActivity() {
    private lateinit var bin: ActivitySelectFoodFromDatabaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivitySelectFoodFromDatabaseBinding.inflate(layoutInflater)
        setContentView(bin.root)

        bin.recyclerView.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<FoodItemsViewModel>()
        val db = DBHelperFood(this, null)
        val dataList = db.readDbData(this)

        for ((i, item) in dataList.withIndex()) {
            data.add(
                FoodItemsViewModel(
                    dataList[i].product,
                    dataList[i].calories,
                    dataList[i].proteins,
                    dataList[i].fats,
                    dataList[i].carbohydrates,
                    dataList[i].gram
                )
            )
            val adapter = FoodListAdapter(data, this)
            bin.recyclerView.adapter = adapter
        }
    }
}