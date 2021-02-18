package com.example.noticiasapp.servicios

import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.noticiasapp.modelos.Article
import com.example.noticiasapp.modelos.TopHeadlines
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
class NewsApiService {
    //&category=business
//https://newsapi.org/v2/top-headlines?country=us&page=0&apiKey=8aadbadef56e4537acccede1a7b11df9
    companion object{

    val url="https://newsapi.org/v2"
    val apiKey="&apiKey=8aadbadef56e4537acccede1a7b11df9"


    fun getNews(page:Int): MutableList<Article>? {
        val url_news= url+"/top-headlines?country=us&page=${page}"+ apiKey
        var articles= mutableListOf<Article>()
        url_news.httpGet().responseString{
            request, response, result ->when(result){
            is Result.Success->{
                val data=result.get()
                Log.i("NewsApiService","Data ${data}")
               val dt=Klaxon().parse<TopHeadlines>(data)
                if(dt!=null){

                    dt.articles?.forEach{
                        articles.add(it)
                        Log.i("NewsApiService","Source:${it.source?.name}")
                    }
                }

            }
            is Result.Failure->{
                val ex=result.getException()
                Log.i("NewsApiService","Error obteniendo articulos: $ex.message")
            }
        }
        }.join()
        return articles
    }
        fun getNewsCategoria(page:Int,categoria:String): MutableList<Article>? {
            val url_news= url+"/top-headlines?country=us&page=${page}&category=${categoria}"+ apiKey
            var articles= mutableListOf<Article>()
            url_news.httpGet().responseString{
                    request, response, result ->when(result){
                is Result.Success->{
                    val data=result.get()
                    Log.i("NewsApiService","Data ${data}")
                    val dt=Klaxon().parse<TopHeadlines>(data)
                    if(dt!=null){

                        dt.articles?.forEach{
                            articles.add(it)
                            Log.i("NewsApiService","Source:${it.source?.name}")
                        }
                    }

                }
                is Result.Failure->{
                    val ex=result.getException()
                    Log.i("NewsApiService","Error obteniendo articulos: $ex.message")
                }
            }
            }.join()
            return articles
        }


    }
}