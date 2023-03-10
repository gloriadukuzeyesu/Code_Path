package com.example.flixsterpart1

import com.google.gson.annotations.SerializedName

class MovieData(
    Mtitle: String = "",
    Mdescription: String = "",
    MposterPath: String = ""
) {
    //@JvmField
    @SerializedName("original_title")
    var title: String? = Mtitle

    @SerializedName("overview")
    var description: String? = Mdescription

    @SerializedName("poster_path")
    var movieImageUl: String? = MposterPath

}