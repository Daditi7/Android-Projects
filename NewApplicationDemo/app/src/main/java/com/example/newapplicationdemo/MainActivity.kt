package com.example.newapplicationdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

getMydata();
//        val button1 = findViewById<Button>(R.id.button1)
//        var counterIncrement=0
//
//        button1.setOnClickListener {
//            counterIncrement=counterIncrement+1
//textView1.text=counterIncrement.toString()
//            // Toast.makeText(this,"Hello Aditi",Toast.LENGTH_LONG).show()
//        }
    }

    private fun getMydata() {
        val textView1=findViewById<TextView>(R.id.textView1)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://archive.org/metadata/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ress= retrofit.create(Retrofit_Interface::class.java)

        val retrofitData=ress.getData()
//        val responsebody=response.body()!!
//        val myString=StringBuilder()
//        for(myData in responsebody){
//            myString.append(myData.created)
//            myString.append("\n")
//        }
//        textView1.text=myString
      retrofitData.enqueue(object : Callback<Retrofit_Demo?> {
          override fun onResponse(call: Call<Retrofit_Demo?>, response: Response<Retrofit_Demo?>) {
              val myString=StringBuilder()
              Log.e("Aditi",response.body().toString())
         // val responsebody=response.body()!!
//        for(myData in responsebody){
//            myString.append(myData.created)
//            myString.append("\n")

              val response = response.body()
              Log.e("Aditi ", "${response?.created}")
              response?.files?.forEach { file ->
                  Log.e("Aditi file response", file.name)
                  myString.append(file.name)
                  myString.append("\n")
              }
              textView1.text=myString
          }

          override fun onFailure(call: Call<Retrofit_Demo?>, t: Throwable) {

              Log.e("Aditi","Fail")
          }
      })
    }
}