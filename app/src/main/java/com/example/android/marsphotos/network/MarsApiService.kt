package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

// creating the Moshi object, similar to the Retrofit object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Retrofit builder to build and create a Retrofit object.
private val retrofit = Retrofit.Builder()
    // Retrofit has a ScalarsConverter that supports strings and other primitive types,
    // so call addConverterFactory() on the builder with an instance of ScalarsConverterFactory.
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL) //add the base URI for the web service using baseUrl()-method
    .build() //create the Retrofit object

//MarsApiService-interface defines how Retrofit talks to the web server using HTTP requests
interface MarsApiService {
    // GET-annotation tells Retrofit that this is GET request,
    // and specify an endpoint ("photos"), for that web service method
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto> //function to get the response string from the web service
    //When the getPhotos() method is invoked, Retrofit appends the endpoint photos to the base URL
    // used to start the request.
    // A return type of the function --> MarsPhoto objects instead of returning String
    // getPhotos()-function is a suspend function so that it can be called from within a coroutine
}

//public object MarsApi to initialize the Retrofit service.
object MarsApi {
    val retrofitService : MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
