package com.example.sqliterdatabase

data class Metadata(
    val addeddate: String,
    val backup_location: String,
    val collection: List<String>,
    val creator: String,
    val curation: String,
    val description: String,
//    val identifier: String,
//    val identifier-access: String,
//    val identifier-ark: String,
    val language: String,
    val mediatype: String,
    val ocr: String,
    val openlibrary_edition: String,
    val openlibrary_work: String,
    val ppi: String,
    val publicdate: String,
    val repub_state: String,
    val scanner: String,
    val subject: List<String>,
    val title: String,
    val uploader: String
)