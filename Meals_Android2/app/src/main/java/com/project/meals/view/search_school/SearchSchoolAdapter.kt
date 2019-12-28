package com.project.meals.view.search_school

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.meals.R
import com.project.meals.model.SearchSchool
import kotlinx.android.synthetic.main.school_layout.view.*

class SearchSchoolAdapter(val items : ArrayList<SearchSchool>) : RecyclerView.Adapter<SearchSchoolAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.school_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        items.get(position).school_name

        var item : SearchSchool = items.get(position)
        val listener = View.OnClickListener {
            Toast.makeText(it.context, "Clicked : ${item.school_name}", Toast.LENGTH_SHORT).show()
        }

        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

//    fun addItems(item : SearchSchoolInfo){
//        items.add(item)
//    }
//
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var view : View = itemView

        fun bind(listener: View.OnClickListener, item: SearchSchool){
            itemView.schoolName.text = item.school_name
            itemView.schoolLocate.text = item.school_locate
            view.setOnClickListener(listener)
        }
    }
}