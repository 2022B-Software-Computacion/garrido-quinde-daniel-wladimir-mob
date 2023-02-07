package com.example.dwgq_application1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class HGoogleMapsActivity : AppCompatActivity() {
    private lateinit var mapa:GoogleMap
    var permisos = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hgoogle_maps2)
        solicitarPermisos()
        iniciarLogicaMapa()

        val btonCarolina = findViewById<Button>(R.id.btn_ir_carolina)
        btonCarolina.setOnClickListener {
            irCarolina()
        }
    }

    fun irCarolina(){
        val posicion = LatLng(0.2878447270285614, -78.53531263674577)
        val zoom = 17f
        moverCamaraConZoom(posicion,zoom)
    }

    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync{
            googleMap ->
            if (googleMap != null){
                mapa = googleMap
                establecerConfiguracionMapa()

                val zoom = 17f
                val quicentro = LatLng(-0.2856042979482473, -78.54333213215729)
                val titulo = "quicentro"
                val markQuicentro = anadirMarcador(quicentro,titulo)
                markQuicentro.tag = titulo

                val poliLineaUno = googleMap
                    .addPolyline(
                        PolylineOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.2878447270285614, -78.53531263674577),
                                LatLng(-0.28511963678742236, -78.5339822610302),
                                LatLng(-0.28450810069960153, -78.53052757570435)
                            )
                    )
                poliLineaUno.tag = "linea 1"// <- ID

                //POLIGONO
                val poligonoUno = googleMap
                    .addPolygon(
                        PolygonOptions()
                            .clickable(true)
                            .add(
                                LatLng(-0.2892501867019501, -78.53477619492496),
                                LatLng(-0.2869649735570251, -78.53470109307007),
                                LatLng(-0.2870937179717232, -78.53248022393201),
                                LatLng(-0.2890677988152085, -78.53295229273431)
                            )
                    )
                poligonoUno.fillColor = -0xc771c4
                poligonoUno.tag = "poligono-2"
                escucharListeners()
            }
        }
    }

    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat
                .checkSelfPermission(
                    contexto,android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun escucharListeners(){
        mapa.setOnPolygonClickListener {
            Log.i("mapa", "setOnPolygonClickListener ${it}")
            it.tag // ID
        }
        mapa.setOnPolylineClickListener {
            Log.i("mapa", "setOnPolylineClickListener ${it}")
            it.tag // ID
        }
        mapa.setOnMarkerClickListener {
            Log.i("mapa", "setOnMarkerClickListener ${it}")
            it.tag // ID
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            Log.i("mapa", "setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            Log.i("mapa", "setOnCameraMoveStartedListener ${it}")
        }
        mapa.setOnCameraIdleListener {
            Log.i("mapa", "setOnCameraIdleListener")
        }
    }


    fun anadirMarcador(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng, zoom)
        )
    }

    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val permisosFineLocation = ContextCompat
            .checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION//perimso que se va a checkea
            )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_DENIED
        if(tienePermisos){
            permisos==true
        }else{
            ActivityCompat.requestPermissions(
                this,//contexto
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION) //ARREGLO PERMISOS
            ,1 //CODIGO PETICION DE LOS PERMISOS
            )
        }
    }
}