package com.project.meals.view.meals.title_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.meals.R


class TitleMealsAdapter(val meals : ArrayList<String>) : RecyclerView.Adapter<TitleMealsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_meals, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return meals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewHolder = holder
        viewHolder.meals_textView.text = meals.get(position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var meals_textView : TextView = itemView.findViewById(R.id.meals_textView)
    }
}