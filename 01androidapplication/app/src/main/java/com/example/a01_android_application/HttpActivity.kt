package com.example. a01_android_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    val urlPrincipal="http://192.168.100.56:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_get.setOnClickListener {
            obtenerUsuario()
            obtenerPokemon()
        }
        btn_post.setOnClickListener {
            crearUsuario()
        }
    }
fun crearUsuario(){
    val url= "$urlPrincipal/usuario"
    val parametrosUsuario=listOf(
        "cedula" to "1234567898",
        "nombre" to "Ramiro",
        "correo" to "ramiro@ramiro.com",
        "estadoVicil" to "Casado",
        "password" to "A123456789b"

    )
    url.httpPost(parametrosUsuario).responseString{
        req,res,result->
        when(result){
            is Result.Failure->{
                val error= result.getException()
                Log.i("http-klaxon-post-usuario","Error:${error}")
            }
            is Result.Success->{
                val usuarioString=result.get()
                Log.i("Http-klaxon","$usuarioString")
            }
        }
    }

}

 fun obtenerPokemon(){
     val url= "$urlPrincipal/pokemon"
     url.httpGet().responseString{

             request, response, result ->
         when(result){
             is Result.Success->{
                 val data=result.get()
                 Log.i("Http_data_pokemon","Data pokemon: $data")
                 val pokemons = Klaxon().converter(PokemonHttp.myConverter).parseArray<PokemonHttp>(data)
                 if(pokemons!=null){
                     pokemons.forEach {
                         Log.i("Http-Klaxon-pokemon","Nombre ${it.nombre} , Entrenador: ${it.entrenador}")

                     }
                 }
             }
             is Result.Failure->{
                 val ex=result.getException()
                 Log.i("Http Exception","Error obteniendo Pokemons: $ex.message")
             }
         }
     }
 }
    fun obtenerUsuario(){

    val url= "$urlPrincipal/usuario"
        url.httpGet().responseString{
            request, response, result ->
            when(result){
                is Result.Success->{
                    val data=result.get()
                    Log.i("Http data usuario","Data usuario: $data")
                    val usuarios=Klaxon().parseArray<UsuarioHttp>(data)
                    if(usuarios!=null){
                        usuarios.forEach {
                            Log.i("Http-Klaxon-usuario","Nombre ${it.nombre} , Estado civil: ${it.estadoCivil}")
                           if(it.pokemons!=null){
                               it.pokemons?.forEach {
                                   Log.i("Http-Klaxon-usuario","Nombre Pokemon ${it.nombre} , Entrenador:${it.entrenador} ")
                               }
                           }
                        }
                    }
                }
                is Result.Failure->{
                    val ex=result.getException()
                    Log.i("Http Exception","Error obteniendo usuarios: $ex.message")
                }
            }
        }
    }
}