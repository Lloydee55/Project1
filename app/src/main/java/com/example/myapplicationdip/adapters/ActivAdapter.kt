package com.example.myapplicationdip.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationdip.R
import com.example.myapplicationdip.SelectFoodFromDatabase

class ActivAdapter(private val mList: List<ItemsViewModel>, contextM: Context) : RecyclerView.Adapter<ActivAdapter.ViewHolder>() {
    val context = contextM
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal, parent, false)

        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.apply {
            textView.text = ItemsViewModel.time
            clickPlus.setOnClickListener() {

                val intent = Intent(context, SelectFoodFromDatabase::class.java)
                context.startActivity(intent)

                emptyLine.visibility = View.GONE
                rvGroceyList.visibility = View.VISIBLE
                dividerBZHY.visibility = View.VISIBLE
                linBZHY.visibility = View.VISIBLE
                tvB.visibility = View.VISIBLE
                tvBMeaning.visibility = View.VISIBLE
                tvZH.visibility = View.VISIBLE
                tvZHMeaning.visibility = View.VISIBLE
                tvY.visibility = View.VISIBLE
                tvYMeaning.visibility = View.VISIBLE
                tvTotalCalories.visibility = View.VISIBLE
                textView22.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View, contextV: Context) : RecyclerView.ViewHolder(ItemView) {
        val context = contextV

        val textView: TextView = itemView.findViewById(R.id.tvTime)
        val clickPlus: TextView = itemView.findViewById(R.id.tvAdd)

        val emptyLine: TextView = itemView.findViewById(R.id.tvEmptyLine)

        val rvGroceyList: RecyclerView = itemView.findViewById(R.id.rvGroceyList)
        val dividerBZHY: View = itemView.findViewById(R.id.dividerBZHY)
        val linBZHY: LinearLayout = itemView.findViewById(R.id.linBZHY)
        val tvB: TextView = itemView.findViewById(R.id.tvB)
        val tvBMeaning: TextView = itemView.findViewById(R.id.tvBMeaning)
        val tvZH: TextView = itemView.findViewById(R.id.tvZH)
        val tvZHMeaning: TextView = itemView.findViewById(R.id.tvZHMeaning)
        val tvY: TextView = itemView.findViewById(R.id.tvY)
        val tvYMeaning: TextView = itemView.findViewById(R.id.tvYMeaning)
        val tvTotalCalories: TextView = itemView.findViewById(R.id.tvTotalCalories)
        val textView22: TextView = itemView.findViewById(R.id.textView22)

    }
}