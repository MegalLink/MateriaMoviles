package com.example.a01_android_application

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity","OnCreate")
        btn_ciclo_vida.setOnClickListener({boton-> irCicloDeVida()})
        btn_list_view.setOnClickListener({btn->irListView()})
        btn_intent_respuesta.setOnClickListener{irAIntentConRespuesta()}
        btn_intent_implicito.setOnClickListener {enviarIntentConRespuesta()  }
        btn_respuesta_propia.setOnClickListener { enviarIntentConRespuestaPropia() }
        btn_http.setOnClickListener { irHttp() }
        btn_recycler.setOnClickListener {
            val intentExplicito= Intent(this, RecyclerViewActivity::class.java)
            this.startActivity(intentExplicito)
        }
        btn_maps_activity.setOnClickListener {
            val intentExplicito= Intent(this, MapsActivity::class.java)
            this.startActivity(intentExplicito)
        }
    }
    fun enviarIntentConRespuestaPropia(){
        val intentExplicito=Intent(this,IntentEnviarParametros::class.java)
        startActivityForResult(intentExplicito,305)
    }
    fun irCicloDeVida(){
        val intentExplicito= Intent(this, CicloVida::class.java)
        this.startActivity(intentExplicito)
    }
    fun irHttp(){
        val intentExplicito= Intent(this, HttpActivity::class.java)
        this.startActivity(intentExplicito)
    }
    fun irListView(){
        val intentExplicito= Intent(this, ListViewActivity::class.java)
        this.startActivity(intentExplicito)
    }
    fun irAIntentConRespuesta(){
        val intentExplicito= Intent(this, IntentEnviarParametros::class.java)
        intentExplicito.putExtra("numero",2)
        val jeff=Usuario("jeferson",22,Date(),22.2)
        val cachetes=Mascota("Peluchin",jeff)
        val arregloMascotas= arrayListOf<Mascota>(cachetes)
        intentExplicito.putExtra("mascota",cachetes)
        intentExplicito.putExtra("arreglomascotas",arregloMascotas)
        this.startActivity(intentExplicito)
    }
    fun enviarIntentConRespuesta(){
        val intentConRespuesta=Intent(
            Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        startActivityForResult(intentConRespuesta, 304)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK->{
                Log.i("resultado","Ok")
                when (requestCode){
                    304->{
                       val uri=data?.data
                        if(uri!=null){
                            val cursor=contentResolver.query(uri,null,null,null,null,null)
                            cursor?.moveToFirst()
                            val indiceTelefono=cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            val telefono=cursor?.getString((indiceTelefono!!))
                            cursor?.close()
                            Log.i("resultado","Telefono: ${telefono}")
                        }

                    }
                    305->{
                        if(data!=null){
                            val nombre=data.getStringExtra("nombre")
                            val edad=data.getIntExtra("edad",0)
                            Log.i("resultado","Nombre: ${nombre}, Edad:${edad}")
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED->{
                Log.i("resultado",":(")
            }
        }
    }
}