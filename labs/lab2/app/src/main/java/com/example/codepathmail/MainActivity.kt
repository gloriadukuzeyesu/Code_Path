package com.example.codepathmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var emails: List<Email>
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val emails_Rv = findViewById<RecyclerView>(R.id.emailsRv)
        // get the list of emails
        emails = EmailFetcher.getEmails()
        val adapter_ = EmailAdapter(emails)
        // Attach the adapter to the RecyclerView to populate items
        emails_Rv.adapter = adapter_
        // set layout
        emails_Rv.layoutManager = LinearLayoutManager(this)
    }
}