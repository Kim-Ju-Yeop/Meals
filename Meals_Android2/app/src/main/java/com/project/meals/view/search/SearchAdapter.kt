package com.project.meals.view.search

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.meals.R
import kotlinx.android.synthetic.main.activity_search.view.*
import kotlinx.android.synthetic.main.school_layout.view.*

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var items : ArrayList<SearchInfo> = ArrayList<SearchInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.school_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item : SearchInfo = items.get(position)
        holder.setItem(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(item : SearchInfo){
        items.add(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setItem(item : SearchInfo){
            itemView.schoolName.text = item.schoolName
            itemView.schoolLocate.text = item.schoolLocate
        }
    }
}