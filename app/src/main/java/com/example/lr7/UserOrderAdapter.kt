package com.example.lr7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserOrderAdapter(private var dataList: List<String>) : RecyclerView.Adapter<UserOrderAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newData: List<String>) {
        dataList = newData
        notifyDataSetChanged() // Повідомляє адаптер, що дані змінилися
    }
}

