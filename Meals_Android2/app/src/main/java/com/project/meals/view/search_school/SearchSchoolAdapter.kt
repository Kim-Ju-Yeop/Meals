package com.project.meals.view.search_school

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.SharedPreferences
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.meals.R
import com.project.meals.model.SearchSchool
import com.project.meals.view.MainActivity
import com.project.meals.view.meals.MealsActivity
import kotlinx.android.synthetic.main.school_layout.view.*

class SearchSchoolAdapter(val mContext : Context, val items : ArrayList<SearchSchool>) : RecyclerView.Adapter<SearchSchoolAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        var itemView : View = inflater.inflate(R.layout.school_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        items.get(position).school_name

        var item : SearchSchool = items.get(position)
        val listener = View.OnClickListener {

            // Intent
            val intent = Intent(mContext, MealsActivity::class.java)
            intent.putExtra("school_id", item.school_code)
            intent.putExtra("office_id", item.office_code)
            mContext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))

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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var view : View = itemView

        fun bind(listener: View.OnClickListener, item: SearchSchool){
            itemView.schoolName.text = item.school_name
            itemView.schoolLocate.text = item.school_locate
            view.setOnClickListener(listener)

            itemView.schoolName.setSingleLine(true)
            itemView.schoolName.ellipsize = TextUtils.TruncateAt.MARQUEE
            itemView.schoolName.isSelected = true
        }
    }
}