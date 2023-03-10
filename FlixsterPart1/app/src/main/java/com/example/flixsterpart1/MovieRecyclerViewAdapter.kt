package com.example.flixsterpart1

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//    private val mListener: onListFragmentInterationListener?
// when the movie is clicked
//        holder.mView.setOnClickListener {
//            holder.mItem?.let { movie -> mListener?.onItemClick(movie) }
//        }


class MovieRecyclerViewAdapter(
    private val movies: List<MovieData>,
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        // var mItem: MovieData? = null
        var mMovieTitle: TextView = mView.findViewById(R.id.tvMovieTitle)
        var mMovieDescription: TextView = mView.findViewById(R.id.tvMovieDescription)
        var mMoviePoster: ImageView = mView.findViewById(R.id.movie_poster)
    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val movieView = inflater.inflate(R.layout.movie_layout_activity, parent, false)
        return MovieViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        //  holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description
        // load the image using Glide
        val restOfUrlForImage = "https://image.tmdb.org/t/p/w500/"
        Glide.with(holder.mView)
            .load(restOfUrlForImage.plus(movie.movieImageUl))
            .centerInside()
            .into(holder.mMoviePoster)
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }

}