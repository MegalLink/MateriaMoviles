package com.example.examen1bimestre

class Acorde(
    var id: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var notacion_latina:String,
    var notacion_inglesa:String,
    var img_url:String,
    var latitud:Double,
    var longitud:Double,
    var url_redireccion:String

) {
    override fun toString(): String {
        return "Notaciones-> Latina: ${notacion_latina.capitalize()}, Inglesa:${notacion_inglesa.capitalize()}"
    }

}