package com.example.flixsterpart2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    // Create and set up an AsyncHttpClient() here
    val client = AsyncHttpClient()
    val params = RequestParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        client[
                "https://api.themoviedb.org/3/person/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                params,
                object : JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        if (response != null) {
                            Log.e("GD", response)
                        } else {
                            Log.e("GD", "response is null")
                        }
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                        Log.e("Success", json.toString())
                        val resultsJSON: JSONArray? = json?.jsonObject?.getJSONArray("results")

                        // create an empty list to hold the parsed PersonData objects
                        val models = mutableListOf<PersonData>()

                        // loop through each object in the results array
                        resultsJSON?.let { jsonArray ->
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)

                                // extract the required fields from the object
                                val name = jsonObject.optString("name")
                                val profilePath = jsonObject.optString("profile_path")

                                // extract the "known_for" array and get the first object
                                val knownForArray = jsonObject.optJSONArray("known_for")
                                val knownForObject = knownForArray?.optJSONObject(0)

                                // extract the required fields from the knownForObject object
                                val posterPath = knownForObject?.optString("poster_path")
                                val overview = knownForObject?.optString("overview")
                                val originalTitle = knownForObject?.optString("original_title")

                                // create a new PersonData object with the extracted fields and add it to the list
                                val personData = PersonData(
                                    name,
                                    profilePath,
                                    overview,
                                    posterPath,
                                    originalTitle
                                )
                                models.add(personData)
                            }
                        }

                        // do the adapter thing
                        val recyclerView: RecyclerView = findViewById(R.id.recyclerviewId)
                        val myTwoColumnLayoutManager = GridLayoutManager(this@MainActivity, 2)
                        recyclerView.adapter = PeopleRecyclerViewAdapter(models)
                        recyclerView.layoutManager = myTwoColumnLayoutManager

                        /* val horizontalLayoutManagaer = LinearLayoutManager(
                             this@MainActivity,
                             LinearLayoutManager.HORIZONTAL,
                             false
                         )
                         recyclerView.layoutManager = horizontalLayoutManagaer*/
//
                    }

                }

        ]


    }
}