package com.example.wishlist

import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView


class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTV: EditText = itemView.findViewById(R.id.itemName_ET)
        val priceTV: EditText = itemView.findViewById(R.id.priceET)
        val itemImageIV: EditText = itemView.findViewById(R.id.urlET)
    }


