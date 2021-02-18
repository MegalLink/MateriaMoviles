package com.example.examen1bimestre

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.squareup.picasso.Picasso
import java.net.URL

class MapsActivity : AppCompatActivity(), OnMapReadyCallback , GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    var search="/results?search_query="
    var tienePermisos=false
    var acordes_cancion=ArrayList<Acorde>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        BddService.getAcordes()
        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    override fun onStart() {
        super.onStart()
        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var acordes_extra= intent.getStringExtra("acordes")
        var query= intent.getStringExtra("nombre-autor")
        if(query!=null){
            this.search=this.search+query
        }
        if(acordes_extra!=null){
            val chords=acordes_extra.split(",").toTypedArray()
            for(chord in chords){
                val acorde_encontrado=BddService.buscarAcorde(chord)
                if(acorde_encontrado!=null){

                    this.acordes_cancion.add(acorde_encontrado)
                }
            }
        }

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zoom=17f
       establecerConfiguracionMapa(mMap)
        mMap.setOnInfoWindowClickListener(this)


        for (acorde in this.acordes_cancion){
          // Log.i("OnMap",acorde.img_url)
            var bit=getBitmap("https://"+acorde.img_url)
            if(bit!=null){
                agregarMarcador(
                    LatLng(acorde.latitud,acorde.longitud),
                    acorde.notacion_inglesa.capitalize(),
                    "https://www."+acorde.url_redireccion+search,
                    "https://"+acorde.img_url

                )
                moverCamaraZoom(LatLng(acorde.latitud,acorde.longitud),zoom)

            }
        }

    }

    fun agregarMarcador(latLng: LatLng,titulo:String,url_redirect:String,urlImg:String){
       // Log.i("MapsActivity",urlImg)

        mMap.addMarker(
            MarkerOptions().position(latLng).title(titulo).snippet(url_redirect).icon(getBitmap(urlImg))

        )
    }
    fun moverCamaraZoom(latLng: LatLng,zoom:Float=10f){
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng,zoom)
        )

    }
    fun getBitmap(url:String): BitmapDescriptor {
        var bmp = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
        bmp = Bitmap.createScaledBitmap(bmp, 100, 100, false)
        return BitmapDescriptorFactory.fromBitmap(bmp)
    }

    fun solicitarPermisos(){
        val permisos=ContextCompat.checkSelfPermission(this.applicationContext,android.Manifest.permission.ACCESS_FINE_LOCATION)
        val estaPermitido=permisos==PackageManager.PERMISSION_GRANTED
        if(estaPermitido){
            this.tienePermisos=true
        }else{
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)

        }
    }

    fun establecerConfiguracionMapa(mapa:GoogleMap){
            val contexto=this.applicationContext
        with(mapa){
            val permisos=ContextCompat.checkSelfPermission(contexto,android.Manifest.permission.ACCESS_FINE_LOCATION)
            val estaPermitido=permisos==PackageManager.PERMISSION_GRANTED
            if(estaPermitido){
                mapa.isMyLocationEnabled=true;
            }
            uiSettings.isZoomControlsEnabled=true
            uiSettings.isMyLocationButtonEnabled=true
        }

    }



    override fun onInfoWindowClick(p0: Marker?) {
       // Log.i("MapActivity","Clicked")
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(p0?.snippet)) //p0?.snippet
        startActivity(browserIntent)
    }

}