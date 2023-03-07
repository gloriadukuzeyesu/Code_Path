package com.example.wishlist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
     private lateinit var items: ArrayList<itemDescription>

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvWishList = findViewById<View>(R.id.itemListRV) as RecyclerView
        val submitButton = findViewById<Button>(R.id.submitButton)
        // inflate the item_description layout to access its views
        items = ArrayList()
        Log.d("GD", "Test1")

        // create an adapter
        val adapter = itemsAdapter(items)
        Log.d("GD", "Test2")

        // Attach the adapter to the recyclerview to populate items
        rvWishList.adapter = adapter

        // Set layout manager to position the items
        rvWishList.layoutManager = LinearLayoutManager(this)

        submitButton.setOnClickListener {
            val itemNameET = findViewById<EditText>(R.id.itemName_ET)
            val urlET = findViewById<EditText>(R.id.urlET)
            val priceET = findViewById<EditText>(R.id.priceET)
            val itemName = itemNameET.text.toString()
            val url = urlET.text.toString()
            val price = priceET.text.toString()
            val newItem = itemDescription(itemName, url, price)
            items.add(newItem) // add new item to the list
            Log.d("GD", "Test5")
            // then notify the adapter for a new item
            adapter.notifyItemInserted(items.size - 1)
            Log.d("GD", "Test6")

        }


    }

}