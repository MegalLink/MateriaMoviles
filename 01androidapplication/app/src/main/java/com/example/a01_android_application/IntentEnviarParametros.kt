package com.example.a01_android_application

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_enviar_parametros.*

class IntentEnviarParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_enviar_parametros)
        //PROPIEDAD DE LA CLASE intent
        val numeroRecibido= intent.getIntExtra("numero",0)
        if(numeroRecibido!=0){
            Log.i("intents","El numero recibido es $numeroRecibido")
        }
        val textDeOtraApp:String? =intent.getStringExtra(Intent.EXTRA_TEXT)
        if(textDeOtraApp!=null){
            Log.i("intents","Texto compartido $textDeOtraApp")
        }
        val cachetes=intent.getParcelableExtra<Mascota>("mascota")
        if(cachetes!=null){
            Log.i("mascota","${cachetes.nombre}  - ${cachetes.duenio?.nombre}"  )
        }
        val arrMascotas=intent.getParcelableArrayListExtra<Mascota>("arreglomascotas")
        if(arrMascotas!=null){
            arrMascotas.forEach{
                if(it!=null) {
                    Log.i("arreglo mascotas", "${it.nombre} ${it.duenio?.nombre}")
                }
            }

        }
        btn_devolver_respuesta.setOnClickListener{
            //Metodo de la clase para terminar una actividad
            finish()
        }
        btn_respuesta_aceptar.setOnClickListener {
            val nombre="Jeferson"
            val edad=31
            val intentRespuesta=Intent()
            intentRespuesta.putExtra("nombre",nombre)
            intentRespuesta.putExtra("edad",edad)
            setResult(Activity.RESULT_OK,intentRespuesta)
            this.finish()
        }
        btn_cancelar_respuesta.setOnClickListener {
            val intentCancelado=Intent()
            setResult(Activity.RESULT_CANCELED,intentCancelado)
            finish()
        }


    }
}