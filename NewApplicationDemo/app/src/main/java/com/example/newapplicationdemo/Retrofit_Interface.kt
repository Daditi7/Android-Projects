package com.example.newapplicationdemo

import retrofit2.Call
import retrofit2.http.GET

interface Retrofit_Interface {
    @GET("TheAdventuresOfTomSawyer_201303")
    fun getData(): Call<Retrofit_Demo>
}