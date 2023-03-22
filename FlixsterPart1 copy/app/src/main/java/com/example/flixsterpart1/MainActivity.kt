package com.example.flixsterpart1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    // Create and set up an AsyncHttpClient() here
    val client = AsyncHttpClient()
    val params = RequestParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("GD", "Test1")

        client[
                "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                params,
                object : JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        if (response != null) {
                            Log.e("Failure", response)
                        } else {
                            Log.e("StatusCode", statusCode.toString())
                        }

                    }

                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers?,
                        json: JSON? // jason response
                    ) {
                        Log.e("Success", json.toString())

                        val JsonOjbect = json?.jsonObject
                        val resultArray = JsonOjbect?.getJSONArray("results")
                        val listOfMovies: MutableList<MovieData> = mutableListOf()
                        if (resultArray != null) {

                            for (i in 0 until resultArray.length()) {
                                val resultObject = resultArray.getJSONObject(i)
                                val posterPath = resultObject.getString("poster_path")
                                val title = resultObject.getString("title")
                                val description = resultObject.getString("overview")
                                val movie_data = MovieData(title, description, posterPath)
                                listOfMovies.add(movie_data)
//                                for (movieData in listOfMovies) {
//                                    movieData.title?.let { Log.e("title", it) }
//                                    movieData.description?.let { Log.e("description", it) }
//                                    movieData.movieImageUl?.let { Log.e("posterPath", it) }
//                                }

                            }

                        }

                        for (movieData in listOfMovies) {
                            movieData.title?.let { Log.e("title", it) }
                            movieData.description?.let { Log.e("description", it) }
                            movieData.movieImageUl?.let { Log.e("posterPath", it) }
                        }

                        val recyclerView: RecyclerView = findViewById(R.id.rvMovieList)
                        recyclerView.adapter = MovieRecyclerViewAdapter(listOfMovies)
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)


                        //TODO - Parse JSON into Models
//                                val gson = Gson()
//                                val arrayMovieType = object : TypeToken<List<MovieData>>() {}.type
//                                val models: List<MovieData> = gson.fromJson(movieTitleJSON, arrayMovieType)
                        //  recyclerView.adapter = MovieRecyclerViewAdapter(models, this@MovieFragment)
                        // Look for this in Logcat:
                    }
                }
        ]

    }


}
