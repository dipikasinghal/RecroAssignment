package com.example.myapplication

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainResponseModel(
    @SerializedName("title")
    var title: String?,
    @SerializedName("rows")
    var rows : ArrayList<ListModel>? = ArrayList()
): Serializable
