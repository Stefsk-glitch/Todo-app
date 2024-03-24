package com.example.myapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val items: MutableList<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    private fun toggle(tvItems: TextView, check: Boolean) {
        if(check) { tvItems.paintFlags = tvItems.paintFlags or STRIKE_THRU_TEXT_FLAG }
        else { tvItems.paintFlags = tvItems.paintFlags and STRIKE_THRU_TEXT_FLAG.inv() }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var currentItem = items[position]

        holder.itemView.apply {

            val tvItems = findViewById<TextView>(R.id.tvItems)
            val cb = findViewById<CheckBox>(R.id.cb)

            tvItems.text = currentItem.text
            cb.isChecked = currentItem.check
            toggle(tvItems, currentItem.check)

            cb.setOnCheckedChangeListener { _, check ->
                toggle(tvItems, check)
                currentItem.check = !currentItem.check
            }
        }
    }
}