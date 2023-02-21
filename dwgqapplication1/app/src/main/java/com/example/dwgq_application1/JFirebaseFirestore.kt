package com.example.dwgq_application1

import android.app.DownloadManager.Query
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.QueryDocumentSnapshot

class JFirebaseFirestore : AppCompatActivity() {
    val query: Query?= null
    val arregol:ArrayList<JCitiesDto> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jfirebase_firestore)
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador= ArrayAdapter(
        this,//contexto
        android.R.layout.simple_list_item_1, //como se va a ver
        arregol
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }

    }

    fun crearDatosPrueba(){}
    fun limpiarArreglo(){arregol.clear()}
    fun anadirArregloCiudad(
        arregloNuevo:ArrayList<JCitiesDto>,
        ciudad:QueryDocumentSnapshot,
        adaptador:ArrayAdapter<JCitiesDto>
    ){
        val nuevaCiudad = JCitiesDto(
            ciudad.data.get("name") as String?,
            ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?,
            ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?,
            ciudad.data.get("regions") as ArrayList<String>
        )
        arregloNuevo.add(
            nuevaCiudad
        )
        adaptador.notifyDataSetChanged()
    }

}