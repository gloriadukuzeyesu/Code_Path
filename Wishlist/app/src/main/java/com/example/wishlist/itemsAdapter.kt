package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class itemsAdapter(private val items: List<itemDescription>) :
    RecyclerView.Adapter<itemsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName = itemView.findViewById<EditText>(R.id.itemName_ET)
        var url = itemView.findViewById<EditText>(R.id.urlET)
        var price = itemView.findViewById<EditText>(R.id.priceET)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // inflate custom layout
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_description, parent, false)

        // return a new holder instance
        return ViewHolder(itemView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPosition: itemDescription = items.get(position)
        // Set item views based on your views and data model
        holder.itemName.setText(itemPosition.item_name)
        holder.url.setText(itemPosition.url_info)
        holder.price.setText(itemPosition.price.toString())
    }

    override fun getItemCount(): Int {
        return items.size
    }
}