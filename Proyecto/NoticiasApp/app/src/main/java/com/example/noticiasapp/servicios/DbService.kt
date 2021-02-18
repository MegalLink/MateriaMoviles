package com.example.noticiasapp.servicios

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noticiasapp.modelos.ArticleDao
import com.example.noticiasapp.modelos.TablaArticle

@Database(entities = [TablaArticle::class],version = 1)
abstract class DbService():RoomDatabase() {
        abstract fun articleDao():ArticleDao
    companion object{
        private var INSTANCE:DbService?=null

        fun getInstance(context: Context):DbService{
            //if(INSTANCE==null){  EL IF ES PARA SINGLETON PERO CREO QUE DA PROBLEMAS SI USO DE DIFERENTES SITIOS
               INSTANCE= Room.databaseBuilder(context.applicationContext,DbService::class.java,"Article").allowMainThreadQueries().fallbackToDestructiveMigration().build()

          //  }
            return INSTANCE!!
        }
    }


}