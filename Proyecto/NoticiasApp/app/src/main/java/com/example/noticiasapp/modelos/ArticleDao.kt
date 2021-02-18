package com.example.noticiasapp.modelos

import androidx.room.*

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article:TablaArticle)
    @Delete()
    fun deleteArticle(article: TablaArticle)


    @Query("SELECT * FROM Article")
    fun getArticles():MutableList<TablaArticle>
}