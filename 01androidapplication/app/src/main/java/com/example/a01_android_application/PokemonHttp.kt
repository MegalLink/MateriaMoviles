package com.example.a01_android_application

import android.util.Log
import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*

class PokemonHttp(
    var id:Int,
    var createdAt:Long,
    var updatedAt:Long,
    var nombre:String,
    var entrenador:Any?) {
    var fechaCreacion:Date
    var fechaActualizacion:Date

    init {

        fechaCreacion=Date(createdAt)
        fechaActualizacion=Date(updatedAt)
    }
    companion object {
        val myConverter = object: Converter {
            override fun canConvert(cls: Class<*>) = cls == PokemonHttp::class.java

            override fun toJson(value: Any): String {

                val pokemon = value as PokemonHttp

                var entrenador: Any?

                if(pokemon.entrenador is Int){
                    entrenador = pokemon.entrenador
                    Log.i("Log tJ int","${entrenador}")
                }else if(pokemon.entrenador is UsuarioHttp){
                    entrenador = Klaxon().toJsonString(pokemon.entrenador as UsuarioHttp)
                    Log.i("Log tJ user","${entrenador}")
                }else{
                    throw Error("toJSON ERROR")
                }

                return """
                  {
                    "id": ${pokemon.id},
                    "createdAt": ${pokemon.createdAt},
                    "updatedAt": ${pokemon.updatedAt},
                    "nombre": "${pokemon.nombre}",
                    "entrenador": ${entrenador}
                   }
                """.trimMargin()


            }



            override fun fromJson(jv: JsonValue) : PokemonHttp {



                if(jv.obj?.get("entrenador") is JsonObject){
                    val entre: UsuarioHttp? =Klaxon().parseFromJsonObject<UsuarioHttp>(jv.obj?.get("entrenador") as JsonObject)

                    return PokemonHttp(
                        jv.objInt("id"),
                        jv.obj?.get("createdAt") as Long,
                        jv.obj?.get("updatedAt") as Long,
                        jv.objString("nombre"),
                        entre?.id

                    )
                }else if(jv.obj?.get("entrenador") is Int){
                    return PokemonHttp(
                        jv.objInt("id"),
                        jv.obj?.get("createdAt") as Long,
                        jv.obj?.get("updatedAt") as Long,
                        jv.objString("nombre"),
                        jv.objInt("entrenador")
                    )
                }else{
                    throw Error("fromJson ERROR")
                }


            }




        }
    }
}