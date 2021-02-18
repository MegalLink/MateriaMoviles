package com.example.noticiasapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noticiasapp.modelos.Article
import com.example.noticiasapp.modelos.ArticleDao
import com.example.noticiasapp.modelos.Source
import com.example.noticiasapp.servicios.DbService
import com.example.noticiasapp.servicios.NewsApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class MainActivity : AppCompatActivity() {
    lateinit var articleDao: ArticleDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var page:Int=0
        var mostrarCategorias:Boolean=false
        val categorias= listOf<String>("business","entertainment", "general" ,"health" ,"science", "sports", "technology")
        var noticias=NewsApiService.getNews(page)

        hsv_categorias.visibility= View.GONE
        tv_encabezado_app.text="Para Tí"
        iniciarRecyclerView(noticias,this,rv_cards,"main")


        btn_para_ti.setOnClickListener {
            tv_encabezado_app.text="PARA TÍ"
            hsv_categorias.visibility= View.GONE

            page=0
               noticias=NewsApiService.getNews(page)
            iniciarRecyclerView(noticias,this,rv_cards,"main")

        }

        btn_encabezados.setOnClickListener {
            hsv_categorias.visibility= View.VISIBLE

            tv_encabezado_app.text="ENCABEZADOS"
            page=0
            var categoria="business"
            getCategoria(categoria,page)

        }
        btn_favoritos.setOnClickListener {
            tv_encabezado_app.text="FAVORITOS"
            hsv_categorias.visibility= View.GONE

            //CARGAR DE LA BASE DE DATOS
            articleDao=DbService.getInstance(this).articleDao()
            var articles=articleDao.getArticles()

            var news=mutableListOf<Article>()
            articles.forEach{
                news.add(Article(Source(it.source_name,""),"",it.title,it.description,it.url,it.urlToImage,"",""))
            }

            iniciarRecyclerView(news,this,rv_cards,"favoritos")
        }







            btn_business.setOnClickListener {
                Log.i("Buttons","Clicked")
                setFocus(btn_business)
                var categoria = "business"
                getCategoria(categoria, page)
            }
            btn_general.setOnClickListener {
                Log.i("Buttons","Clicked")
                setFocus(btn_general)
                var categoria = "general"
                getCategoria(categoria, page)
            }
            btn_entertainment.setOnClickListener {
                setFocus(btn_entertainment)
                var categoria = "entertainment"
                getCategoria(categoria, page)
            }
            btn_health.setOnClickListener {
                setFocus(btn_health)
                var categoria = "health"
                getCategoria(categoria, page)
            }
            btn_science.setOnClickListener {
                setFocus(btn_science)
                var categoria = "science"
                getCategoria(categoria, page)
            }
            btn_sports.setOnClickListener {
                setFocus(btn_sports)
                var categoria = "sports"
                getCategoria(categoria, page)
            }
            btn_technology.setOnClickListener {

                setFocus(btn_technology)
                var categoria = "technology"
                getCategoria(categoria, page)
            }



    }
    fun setFocus(btn:Button){
        var listaBotones= arrayListOf<Button>(btn_business,btn_technology,btn_sports,btn_science,btn_health,btn_entertainment,btn_general)
        for(boton in listaBotones){
            if(boton.id==btn.id){
                boton.setBackgroundColor(Color.rgb(6,53,73))
                boton.setTextColor(Color.rgb(40,104,199))
            }else{
                boton.setBackgroundColor(Color.rgb(34,36,40))
                boton.setTextColor(Color.rgb(255,255,255))
            }

        }
    }
    fun getCategoria(categoria:String,page:Int){
        var noticias=NewsApiService.getNewsCategoria(page,categoria)
        iniciarRecyclerView(noticias,this,rv_cards,"main")
    }
    fun iniciarRecyclerView(lista:MutableList<Article>?,
                            activity:MainActivity,
                            recycler_view:androidx.recyclerview.widget.RecyclerView,
                            desde:String
                            ){
        val mi_adaptador=MyAdapter(lista,activity,recycler_view,desde)
        rv_cards.adapter=mi_adaptador
        rv_cards.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        rv_cards.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(activity)
        mi_adaptador.notifyDataSetChanged()

    }




}