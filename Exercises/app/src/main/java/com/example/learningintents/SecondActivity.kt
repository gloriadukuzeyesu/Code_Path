package com.example.learningintents

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.learningintents.R.*

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_second)

        //get the intent info
        // key: product_name
        val productName = getIntent().getStringExtra("Product_Name")
        val productDisplay = findViewById<TextView>(id.proNameIdTV)
        productDisplay.setText(productName)

//        val entireProductObject = getIntent().getSerializableExtra("HHH")



    }
}