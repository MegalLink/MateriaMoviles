package com.example.a01_android_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVida : AppCompatActivity() {
    var numeroActual = 0;
    fun sumarUnValor() {
       numeroActual++;
       tv_numero.text = numeroActual.toString()
        ServicioBDDMemoria.añadirNumero()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("Activity", "OnCreate")
        numeroActual=ServicioBDDMemoria.numero
        if (numeroActual!=0){
            tv_numero.text=numeroActual.toString()
        }

        btn_agregar.setOnClickListener { this.sumarUnValor() }
    }

    override fun onStart() {
        super.onStart()

        Log.i("Activity", "OnStart")
    }

    override fun onResume() {
        super.onResume()

        Log.i("Activity", "OnResume")
    }

    override fun onPause() {
        super.onPause()

        Log.i("Activity", "OnPause")
    }

    override fun onStop() {
        super.onStop()

        Log.i("Activity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("Activity", "OnDestroy")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i("Activity", "OnRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("Activity", "OnSaveInstanceState")
        outState.run {
            putInt("numeroActualGuardado", numeroActual)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val recuperado = savedInstanceState?.getInt("numeroActualGuardado")
        if (recuperado != null) {
                this.numeroActual=recuperado
            tv_numero.text=this.numeroActual.toString()
        }
    }
}