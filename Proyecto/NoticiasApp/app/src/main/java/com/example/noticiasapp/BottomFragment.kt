package com.example.noticiasapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.noticiasapp.modelos.Article
import com.example.noticiasapp.modelos.ArticleDao
import com.example.noticiasapp.modelos.Source
import com.example.noticiasapp.modelos.TablaArticle
import com.example.noticiasapp.servicios.DbService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*

class BottomFragment():BottomSheetDialogFragment() {
    lateinit var articleDao: ArticleDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=inflater.inflate(R.layout.fragment_bottom_sheet,container,false)

        var noticia=arguments?.getParcelable<Article>("noticia")
        var desde=arguments?.getString("desde")
        if(desde=="main"){
            view.btn_bs_add_favoritos.visibility=View.VISIBLE
            view.btn_bs_eliminar.visibility=View.GONE
        }else{
            view.btn_bs_add_favoritos.visibility=View.GONE
            view.btn_bs_eliminar.visibility=View.VISIBLE
        }
        if(noticia!=null){
            view.btn_bs_compartir.setOnClickListener {
                val intent=Intent()
                intent.action=Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,noticia.url)
                intent.putExtra(Intent.EXTRA_TITLE,noticia.title)
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent,"NoticiasApp"))

            }
            view.btn_bs_add_favoritos.setOnClickListener {

                articleDao=DbService.getInstance(this.requireContext()).articleDao()
                var dataArticle=TablaArticle(noticia.title,noticia.source?.name,noticia.description,noticia.url,noticia.urlToImage)
                articleDao.insertArticle(dataArticle)
                Toast.makeText(this.requireContext(),"Agregado a Favoritos",Toast.LENGTH_SHORT).show()
                dismiss()
            }
            view.btn_bs_eliminar.setOnClickListener {
                articleDao=DbService.getInstance(this.requireContext()).articleDao()
                var dataArticle=TablaArticle(noticia.title,noticia.source?.name,noticia.description,noticia.url,noticia.urlToImage)
                articleDao.deleteArticle(dataArticle)

                Toast.makeText(this.requireContext(),"Eliminado de favoritos",Toast.LENGTH_SHORT).show()

                dismiss()
            }
        }
        view.btn_bs_cancelar.setOnClickListener {
            dismiss()
        }

        return view
        }



}