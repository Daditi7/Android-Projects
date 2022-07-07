package com.example.roomdatabase
//
//import android.app.Application
//import android.util.Log
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class RetrofitCall() {
//
//        val retrofit = Retrofit.Builder()
//        .baseUrl("https://archive.org/metadata/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    val ress = retrofit.create(RetrofitInterface::class.java)
//    val retrofitData = ress.getData()
//
//    retrofitData.enqueue(
//    object : Callback<DBRetrofitHandler?> {
//        override fun onResponse(
//            call: Call<DBRetrofitHandler?>,
//            response: Response<DBRetrofitHandler?>
//        ) {
//            val response = response.body()
//            Log.e("Aditi ", "${response?.created}")
//            response?.files?.forEach { file ->
//                var note = Note(file.name)
//            }
//        }
//
//        override fun onFailure(call: Call<DBRetrofitHandler?>, t: Throwable) {
//
//            Log.e("Aditi", "Fail")
//        }
//    })
//
//}