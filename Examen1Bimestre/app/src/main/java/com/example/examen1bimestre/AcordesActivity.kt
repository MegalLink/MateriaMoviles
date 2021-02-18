package com.example.examen1bimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_acordes.*

class AcordesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acordes)
        BddService.getAcordes()
        val posicion= intent.getIntExtra("index",-1)

        if(posicion>-1){
            val cancion:Cancion?= BddService.obtenerCancion(posicion)
            tv_cancion_y_autor.setText("${cancion?.nombre} - ${cancion?.autor}")
            val chords=cancion!!.acordes.split(",").toTypedArray()
            val adaptador= ArrayAdapter(this,android.R.layout.simple_list_item_1,chords)

            lv_acordes.adapter=adaptador

            lv_acordes.onItemClickListener= AdapterView.OnItemClickListener{
                    parent,view,position,id ->
                Log.i("list-view","Posicion acorde ${chords[position]}")
                val acorde_encontrado=BddService.buscarAcorde(chords[position])
                if(acorde_encontrado!=null){
                    Log.i("acorde-econtrado","${acorde_encontrado.img_url}")
                    //Cambia la imagen
                    Picasso.get().load("https://"+acorde_encontrado.img_url).placeholder(R.drawable.guitarra2)
                        .error(R.drawable.guitarra_3).into(iv_chord);
                 // iv_chord.setImageResource(acorde_encontrado.imagen)

                }else{
                    Toast.makeText(applicationContext,"No se encuentra ese acorde", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            this.startActivity(Intent(this,MainActivity::class.java))
        }


        btn_acordes_a_main.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }
        btn_acordes_a_lista.setOnClickListener {
            this.startActivity(Intent(this,ListaCancionesActivity::class.java))
        }


    }



}