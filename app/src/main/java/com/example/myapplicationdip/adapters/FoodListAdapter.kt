package com.example.myapplicationdip.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationdip.R

class FoodListAdapter(private val mList: ArrayList<FoodItemsViewModel>, contextM: Context) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {
    val context = contextM
    class ViewHolder(ItemView: View, contextV: Context) : RecyclerView.ViewHolder(ItemView) {
        val context = contextV

        val tvProduct: TextView = itemView.findViewById(R.id.tvProduct)
        val tvGram: TextView = itemView.findViewById(R.id.tvGram)
        val tvProteins: TextView = itemView.findViewById(R.id.tvProteins)
        val tvFats: TextView = itemView.findViewById(R.id.tvFats)
        val tvCarbohydrates: TextView = itemView.findViewById(R.id.tvCarbohydrates)
        val tvCalories: TextView = itemView.findViewById(R.id.tvCalories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nutritional_value, parent, false)

        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val FoodItemsViewModel = mList[position]
        holder.apply {
            tvProduct.text = FoodItemsViewModel.product
            tvProteins.text = FoodItemsViewModel.proteins.toString()
            tvFats.text = FoodItemsViewModel.fats.toString()
            tvCarbohydrates.text = FoodItemsViewModel.carbohydrates.toString()
            tvCalories.text = FoodItemsViewModel.calories.toString()
            tvGram.text = FoodItemsViewModel.gram.toString()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}