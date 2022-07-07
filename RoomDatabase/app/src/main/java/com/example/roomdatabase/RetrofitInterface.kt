package com.example.roomdatabase

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("TheAdventuresOfTomSawyer_201303")
    fun getData(): Call<DBRetrofitHandler>
}