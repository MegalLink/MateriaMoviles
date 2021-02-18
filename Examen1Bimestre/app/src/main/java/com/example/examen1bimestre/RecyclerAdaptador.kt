package com.example.examen1bimestre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class RecyclerAdaptador(
    private val listaCanciones: List<Cancion>,
   private val contexto:RecyclerViewActivity,
    private val recyclerView: androidx.recyclerview.widget.RecyclerView
) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {
    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var nombreTextView:TextView
        var autorTextView:TextView
        var youtube_btn:Button
        init{
            nombreTextView=view.findViewById(R.id.tv_nombre_adapter)
            autorTextView=view.findViewById(R.id.tv_autor_adapter)
            youtube_btn=view.findViewById(R.id.btn_youtube)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptador.MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
