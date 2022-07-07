package com.example.roomdatabase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private var mNoteViewModel: NoteViewModel? = null
    var noterepository:NoteViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noterepository = ViewModelProvider(this).get(NoteViewModel::class.java)
        getdata()
        mNoteViewModel?.getmAllNotes()?.observe(
            this
        ) { notes: List<Note?>? ->
            println("Total count -> ${notes?.size}")
            adapter.update(
                notes as List<Note>?
            )
        }

    }

    private fun getdata() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://archive.org/metadata/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ress = retrofit.create(RetrofitInterface::class.java)
        val retrofitData = ress.getData()
        retrofitData.enqueue(object : Callback<DBRetrofitHandler?> {
            override fun onResponse(
                call: Call<DBRetrofitHandler?>,
                response: Response<DBRetrofitHandler?>
            ) {
                val response = response.body()
                Log.e("Aditi ", "${response?.created}")
                response?.files?.forEach { file ->
                    var user = Note(file.name)
                    Log.e("Inserted", "${file.name}")
                        //mNoteViewModel!!.insert(Note(file.name))
                         noterepository?.insert(user)

                }

            }

            override fun onFailure(call: Call<DBRetrofitHandler?>, t: Throwable) {

                Log.e("Aditi", "Fail")
            }
        })
//      return r

    }

    override fun onItemClicked(note: Note?) {
        mNoteViewModel!!.delete(note)
        Toast.makeText(this, note!!.text + " is deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View?) {
        val input = findViewById<TextView>(R.id.input_text)
        val noteText = input.text.toString()
        if (!noteText.isEmpty()) {
            mNoteViewModel!!.insert(Note(noteText))
            Toast.makeText(this, "$noteText is inserted", Toast.LENGTH_SHORT).show()
        }
    }
}