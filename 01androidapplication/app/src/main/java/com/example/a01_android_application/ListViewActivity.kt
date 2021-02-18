package com.example.a01_android_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val listaEntrenadores= arrayListOf<Entrenador>()
        listaEntrenadores.add(Entrenador("Jeferson","Narvaez"))
        listaEntrenadores.add(Entrenador("Vicente","Pozo"))
        listaEntrenadores.add(Entrenador("Pedro","Florez"))
        listaEntrenadores.add(Entrenador("Sofia","Nuñex"))
        listaEntrenadores.add(Entrenador("Lizzet","Ordoñez"))
        listaEntrenadores.add(Entrenador("Joaquin","Montenegro"))
        listaEntrenadores.add(Entrenador("Vanesa","Pascal"))
        val adaptador=ArrayAdapter(
            this,android.R.layout.simple_list_item_1,listaEntrenadores
        )
        lv_numeros.adapter=adaptador
        lv_numeros.onItemClickListener=AdapterView.OnItemClickListener{
            parent,view,position,id ->
            Log.i("list-view","Posicion $position")
        }
        btn_agregar.setOnClickListener{
            agregarEntrenador(adaptador,listaEntrenadores)
        }


    }
    fun agregarEntrenador(adaptador:ArrayAdapter<Entrenador>,listaEntrenadores:ArrayList<Entrenador>){
        listaEntrenadores.add(Entrenador("Jeferson","Narvaez"))
        adaptador.notifyDataSetChanged()


    }
}