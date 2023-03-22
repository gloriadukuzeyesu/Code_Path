package com.example.learningintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.buttonBt);
        btn.setOnClickListener {
            Log.e("GD", "Button Clicked")
            // go to second Activity
            val i = Intent(this@MainActivity, SecondActivity::class.java)
            i.putExtra("Product_Name", "MacBookPro") // key and value

            startActivity(i)

            // take the intent

        }
    }
}