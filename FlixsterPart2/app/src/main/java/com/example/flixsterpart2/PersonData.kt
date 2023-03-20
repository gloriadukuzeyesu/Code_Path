package com.example.flixsterpart2

import com.google.gson.annotations.SerializedName

// implemented Parcelable interface so that I can pass PersonData object using putExtra
data class PersonData(
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("profile_path")
    val profilePath: String? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null

) : java.io.Serializable {

}












