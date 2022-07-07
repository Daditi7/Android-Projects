package com.example.sqliterdatabase

import retrofit2.Call
import retrofit2.http.GET


interface Retrofit_Interface {
    @GET("TheAdventuresOfTomSawyer_201303")
    fun getData(): Call<DBRetrofiltHandler>
}