package com.example.imtihon6_modul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imtihon6_modul.R
import com.example.imtihon6_modul.modul.Card

class CardAdapter(var context: Context, var items: ArrayList<Card>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_view, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[items.size - position - 1]
        if (holder is CardViewHolder){
            val tv_card_number = holder.tv_card_number
            val tv_card_name = holder.tv_card_name
            val tv_card_data = holder.tv_card_data

            tv_card_number.text = item.card_id
            tv_card_name.text = item.name
            tv_card_data.text = item.data
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_card_number: TextView
        val tv_card_name: TextView
        val tv_card_data: TextView

        init {
            tv_card_number = view.findViewById(R.id.tv_card_number)
            tv_card_name = view.findViewById(R.id.tv_card_name)
            tv_card_data = view.findViewById(R.id.tv_card_data)
        }
    }
}