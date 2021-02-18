package com.example.noticiasapp.modelos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Article")
class TablaArticle (
    @PrimaryKey val title:String,
    val source_name:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
){

}