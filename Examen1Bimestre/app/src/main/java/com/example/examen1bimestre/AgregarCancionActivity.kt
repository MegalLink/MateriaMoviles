package com.example.examen1bimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_cancion.*

class AgregarCancionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cancion)

        val posicion= intent.getIntExtra("index",-1)

        if(posicion>-1){
            //Me enviaron para modificar
            tv_agregar_modificar_c.text="Modificar Cancion"
            btn_agregar_modificar.setText("MODIFICAR")
            var cancion: Cancion? = BddService.obtenerCancion(posicion)
            if (cancion != null) {
                et_nombre.setText(cancion.nombre)
                et_autor_genero.setText(cancion.autor)
                et_genero.setText(cancion.genero)
                et_acordes.setText(cancion.acordes)
            }

            btn_agregar_modificar.setOnClickListener {
                BddService.modificarCancion(posicion,
                    et_nombre.text.toString(),
                    et_autor_genero.text.toString(),
                    et_genero.text.toString(),
                    et_acordes.text.toString()
                )
                Toast.makeText(applicationContext,"Cancion Modificada con Exito",Toast.LENGTH_SHORT).show()
                irACancion(posicion)
            }

        }else{
            tv_agregar_modificar_c.text="Agregar Cancion"
            btn_agregar_modificar.setText("AGREGAR")
            //Me enviaron para agregar
            btn_agregar_modificar.setOnClickListener {
                BddService.agregarCancion(
                    et_nombre.text.toString(),
                    et_autor_genero.text.toString(),
                    et_genero.text.toString(),
                    et_acordes.text.toString()
                )
                Toast.makeText(applicationContext,"Cancion Agregada con Exito",Toast.LENGTH_SHORT).show()
                irAListaCanciones()
            }
        }
        btn_agregar_a_lista.setOnClickListener {
            this.startActivity(Intent(this,ListaCancionesActivity::class.java))
        }
        btn_agregar_a_main.setOnClickListener {
            this.startActivity(Intent(this,MainActivity::class.java))
        }
    }
    fun irACancion(posicion:Int){
        val intentExplicito= Intent(this, CancionActivity::class.java)
        intentExplicito.putExtra("index",posicion)
        this.startActivity(intentExplicito)
    }
    fun irAListaCanciones(){
        val intentExplicito= Intent(this, ListaCancionesActivity::class.java)
        this.startActivity(intentExplicito)
    }

}