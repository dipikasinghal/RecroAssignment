package com.example.myapplication

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListModel(
    @SerializedName("imageHref")
    var image:String,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String
): Serializable