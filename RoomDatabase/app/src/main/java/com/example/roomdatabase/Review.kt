package com.example.roomdatabase

data class Review(
    val createdate: String,
    val reviewbody: String,
    val reviewdate: String,
    val reviewer: String,
    val reviewer_itemname: String,
    val reviewtitle: String,
    val stars: String
)