package com.example.examen1bimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_agregar_cancion.setOnClickListener {
            this.startActivity(Intent(this, AgregarCancionActivity::class.java))
        }
        btn_listar_canciones.setOnClickListener {
            this.startActivity(Intent(this,ListaCancionesActivity::class.java))
        }
        btn_listar_acordes.setOnClickListener {
            this.startActivity(Intent(this,ListaAcordesActivity::class.java))
        }

    }

}