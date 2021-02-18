package com.example.examen1bimestre

class Cancion(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var nombre:String,
    var autor:String,
    var genero:String,
    var acordes:String) {

    override fun toString(): String {
        return "Nombre: ${nombre}, Autor:${autor}"
    }
}