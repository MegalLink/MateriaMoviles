package com.example.examen1bimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_acordes.*
import kotlinx.android.synthetic.main.activity_lista_canciones.*
import kotlinx.android.synthetic.main.activity_lista_canciones.lv_acordes_main

class ListaAcordesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_acordes)
        val listaAcordes= BddService.listaAcordes
        Log.i("Lista-Http","${listaAcordes}")
        val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,listaAcordes)
        lv_acordes_main.adapter=adaptador
        lv_acordes_main.onItemClickListener= AdapterView.OnItemClickListener{
                parent,view,position,id ->
            Log.i("list-view","Posicion ${listaAcordes[position]}")

            irAcorde(listaAcordes[position].id);
        }
        btn_lista_a_main2.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }

    }
    init {
        BddService.getAcordes()
    }
    fun irAcorde(posicion:Int){
       //val intentExplicito= Intent(this, CancionActivity::class.java)
        //intentExplicito.putExtra("index",posicion)
        //this.startActivity(intentExplicito)
    }
}