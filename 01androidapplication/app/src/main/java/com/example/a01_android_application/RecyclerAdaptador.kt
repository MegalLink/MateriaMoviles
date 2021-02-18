package com.example.a01_android_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class RecyclerAdaptador(
    private val listaUsuarios:List<UsuarioHttp>,
    private val contexto:RecyclerViewActivity ,
    private val reyclerView:androidx.recyclerview.widget.RecyclerView):
    androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {
    inner class MyViewHolder(view:View):androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
        val nombreTextView:TextView
        val cedulaTextView:TextView
        val accionButton:Button
        val likesTextView:TextView
        var numlikes=0;
        init{
            nombreTextView=view.findViewById(R.id.tv_nombre)
            cedulaTextView=view.findViewById(R.id.tv_cedula)
            accionButton=view.findViewById(R.id.btn_accion)
            likesTextView=view.findViewById(R.id.tv_likes)
            accionButton.setOnClickListener {
                añadirLike()
            }
        }
        fun añadirLike(){
            this.numlikes=this.numlikes+1
            this.likesTextView.text=this.numlikes.toString()
            contexto.agregarLikesEnActividad(1)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptador.MyViewHolder {
       //Interfaz a usar
        val itemView=LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.adaptador_persona,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }
    //Items de la lista
    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
    //Funcion que se ejecuta con cada uno de los items
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val usuario=listaUsuarios[position]
        holder.nombreTextView.text=usuario.nombre
        holder.cedulaTextView.text=usuario.cedula
        holder.accionButton.text="Like ${usuario.nombre}"
        holder.likesTextView.text="0"
    }
}