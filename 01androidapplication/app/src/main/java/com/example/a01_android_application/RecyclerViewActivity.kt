package com.example.a01_android_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list_view.*
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    var numeroLikes=0;
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        val listaUsuarios= arrayListOf<UsuarioHttp>()
        listaUsuarios.add(
            UsuarioHttp(
                1,
                123123123,
                123123123,
                "401730254",
                "Jeferson",
                "jeferson@jeferson.com",
                "Casado",
                "test1234",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                2,
                123123123,
                123123123,
                "4017302543",
                "Jeferson2",
                "jeferson2@jeferson.com",
                "Casado",
                "test1234",
                arrayListOf<PokemonHttp>()
            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                3,
                123123123,
                123123123,
                "4017302542",
                "Jeferson3",
                "jeferson3@jeferson.com",
                "Casado",
                "test1234",
                arrayListOf<PokemonHttp>()
            )
        )
        iniciarRecyclerView(listaUsuarios,this,rv_usuario)
    }
    fun agregarLikesEnActividad(numero:Int){
        this.numeroLikes=this.numeroLikes+numero
        tv_titulo_rv.text=this.numeroLikes.toString()


    }
    fun iniciarRecyclerView(lista:List<UsuarioHttp>,
                            actividad:RecyclerViewActivity,
                            recycler_view:androidx.recyclerview.widget.RecyclerView){
        val adaptadorUsuario=RecyclerAdaptador(lista,actividad, recycler_view)
        rv_usuario.adapter=adaptadorUsuario
        rv_usuario.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuario.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorUsuario.notifyDataSetChanged()
    }

}