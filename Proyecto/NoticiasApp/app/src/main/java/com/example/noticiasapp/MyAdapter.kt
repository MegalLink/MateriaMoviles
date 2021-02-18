package com.example.noticiasapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.noticiasapp.modelos.Article
import com.squareup.picasso.Picasso

class MyAdapter(
    private val listaNoticias: MutableList<Article>?,//datos
    private val contexto: MainActivity,//En cual activity cargar el recycler
    private val recyclerView: androidx.recyclerview.widget.RecyclerView,
    private val desde:String?
):androidx.recyclerview.widget.RecyclerView.Adapter<MyHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        //Aqui seteo el layout en este caso la card
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MyHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val noticia= listaNoticias?.get(position)
        if(noticia!=null){
            holder.tituloTextView.text=noticia.title
            holder.sourceTextView.text=noticia.source?.name
            holder.descripcionTextView.text=noticia.description
            holder.idTextView.text=(position+1).toString()
            Picasso.get().load(noticia.urlToImage).placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24).into(holder.imgNoticia);
            var bundle=Bundle()
            bundle.putParcelable("noticia",noticia)
            bundle.putString("desde",desde)
            holder.buttonDialog.setOnClickListener {
                var fragment= BottomFragment()
                fragment.arguments=bundle
                fragment.show(((contexto as AppCompatActivity).supportFragmentManager), "Fragment")

            }
            holder.imgNoticia.setOnClickListener{
                val browserIntent=Intent(Intent.ACTION_VIEW, Uri.parse(noticia?.url))
                contexto.startActivity(browserIntent)

            }
        }

    }

    override fun getItemCount(): Int {
        return listaNoticias?.size!!
    }

}