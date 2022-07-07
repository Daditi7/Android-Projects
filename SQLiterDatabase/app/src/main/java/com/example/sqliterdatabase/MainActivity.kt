package com.example.sqliterdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var l:List<File>? = getMydata()

//        val myString=StringBuilder()
//        l?.forEach { file ->
//            Log.e("functionesd", file.name)
//            myString.append(file.name)
//            myString.append("\n")
//        }
        val context=this
        var btn=findViewById<Button>(R.id.button)
        var read=findViewById<Button>(R.id.read)
        var delete=findViewById<Button>(R.id.delete)
        var update=findViewById<Button>(R.id.update)
//        var email= findViewById<EditText>(R.id.email)
//        var age= findViewById<EditText>(R.id.age)
        var tvResult=findViewById<TextView>(R.id.tv)

//        var db= l?.let { DBHelper(context, it) }
        var db: DBHelper = DBHelper(context)
        btn.setOnClickListener {
            getMydata(db)

        }

        read.setOnClickListener {
            val data = db?.readData()
            tvResult.text = ""
            if (data != null) {
                for (i in 0 until data.size) {
        //                Log.e("Inerted","${data[0]}")
                    tvResult.append(
                        data[i].id.toString() + " " + data[i].email + " " + data[i].age + "\n"
                    )
                }
            }
        }

        delete.setOnClickListener {
            db?.deleteData()
            read.performClick()
        }

        update.setOnClickListener {
            db?.updateData()
            read.performClick()
        }

    }



    private fun getMydata(db: DBHelper){
//        var email= findViewById<EditText>(R.id.email)
//        var age= findViewById<EditText>(R.id.age)
//        var user = User(email.text.toString(), age.text.toString().toInt())
//        db?.onInsert(user)
//        var r: List<File>? = null
        val textView1 = findViewById<TextView>(R.id.tv)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://archive.org/metadata/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ress = retrofit.create(Retrofit_Interface::class.java)

        val retrofitData = ress.getData()
//        val responsebody=response.body()!!
//        val myString=StringBuilder()
//        for(myData in responsebody){
//            myString.append(myData.created)
//            myString.append("\n")
//        }
//        textView1.text=myString
        retrofitData.enqueue(object : Callback<DBRetrofiltHandler?> {
            override fun onResponse(
                call: Call<DBRetrofiltHandler?>,
                response: Response<DBRetrofiltHandler?>
            ) {

//                val myString=StringBuilder()
//                 Log.e("Aditi",response.body().toString())
//                 val responsebodyy=response.body()!!
//        for(myData in responsebodyy){
//            myString.append(myData.created)
//            myString.append("\n")
//r= response.body()?.files
                val response = response.body()
               Log.e("Aditi ", "${response?.created}")
                response?.files?.forEach { file ->
                    var user = User(file.name, file.size)
                    db?.onInsert(user)
                }
//
//                response?.files?.forEach { file ->
//                  Log.e("Aditi file response", file.name)
//                    myString.append(file.name)
//                    myString.append("\n")
//                }
//                textView1.text=myString

            }

            override fun onFailure(call: Call<DBRetrofiltHandler?>, t: Throwable) {

                Log.e("Aditi", "Fail")
            }
        })
//      return r
    }
}