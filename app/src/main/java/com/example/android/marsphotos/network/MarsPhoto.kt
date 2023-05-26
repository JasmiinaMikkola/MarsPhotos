package com.example.android.marsphotos.network

import com.squareup.moshi.Json

//Moshi needs to have a Kotlin data class to store the parsed results
data class MarsPhoto (
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "img_src") val imgSrcUrl: String
        )
