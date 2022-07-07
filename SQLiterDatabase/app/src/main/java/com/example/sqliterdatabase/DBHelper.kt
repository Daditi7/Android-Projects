package com.example.sqliterdatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

val DBName="NewDB"
val TableName="Aditi"
val ColEmail="Email"
val ColAge="Age"
val ColId="id"

class DBHelper(var context: Context): SQLiteOpenHelper(context, DBName,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
val CreateTable= "CREATE TABLE "+ TableName +" ( "+ ColId+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ ColEmail+
        " varchar(256), "+ ColAge+ " varchar(256) )";
        db?.execSQL(CreateTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        Log.e("Upgrade","older version upgrading")
    }

    fun onInsert(user:User){
        val db=this.writableDatabase
        var cv=ContentValues()
        cv.put(ColEmail,user.email)
        cv.put(ColAge,user.age)
        var resultrow= db.insert(TableName,null,cv)
        if(resultrow==-1.toLong())
        Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
Log.e("Inerted","${user.age}")
        }

    @SuppressLint("Range")
    fun readData(): MutableList<User> {
        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TableName"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val user = User()
                user.id = result.getString(result.getColumnIndex(ColId)).toInt()
                user.email = result.getString(result.getColumnIndex(ColEmail))
                user.age = result.getString(result.getColumnIndex(ColAge))
                list.add(user)
            }
            while (result.moveToNext())
        }
        Log.e("Inerted","${list}")
        db.close()
        result.close()
        return list
    }

    fun deleteData(){
        val db= this.writableDatabase
        db.delete(TableName, ColId+"=?", arrayOf(2.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun updateData(){
        val db = this.writableDatabase
        val query = "Select * from $TableName"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
var cv= ContentValues()
//                cv.put(ColAge,(result.getString(result.getColumnIndex(ColAge))+"Janleva che"))
                cv.put(ColAge,"Janleva che")
                db.update(TableName,cv, ColId+"=? AND "+ ColEmail+"=?", arrayOf(result.getString(result.getColumnIndex(
                    ColId)),result.getString(result.getColumnIndex(
                    ColEmail))))
                Log.e("Update","${result.getColumnIndex(ColAge)}")
            }
            while (result.moveToNext())
        }
        db.close()
        result.close()
    }

    }
