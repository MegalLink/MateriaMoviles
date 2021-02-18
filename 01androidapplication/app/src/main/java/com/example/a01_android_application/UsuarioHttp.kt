package com.example.a01_android_application

import java.util.*

class UsuarioHttp(
    var id:Int,
    var createdAt:Long,
    var updatedAt:Long,
    var cedula:String,
    var nombre:String,
    var correo:String,
    var estadoCivil:String,
    var password:String,
    var pokemons: ArrayList<PokemonHttp>? = null

) {
    var fechaCreacion: Date
    var fechaActualizacion: Date
    init {
        fechaCreacion= Date(createdAt)
        fechaActualizacion= Date(updatedAt)
    }

}