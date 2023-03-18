package com.example.pokemoexamen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import com.google.firebase.firestore.Query
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val FR_aux = Crud_Facultad_implement()
    var arreglo: ArrayList<Facultad> = arrayListOf()
    val db = Firebase.firestore
    val adaptadorFacultad = AdaptadorFacultad(this,arreglo)
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    Log.i("cambio", "efectivamente")
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Variables*/

        val recyclerView = findViewById<RecyclerView>(R.id.lv_facultades)
        Log.i("test","create")
        recyclerView.adapter = adaptadorFacultad
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptadorFacultad.notifyDataSetChanged()
        /* Botones */
        val botonCrear = findViewById<Button>(R.id.btnCrearFacultad)
        botonCrear.setOnClickListener {
            abrirActividadConParametros(FacultadForm::class.java,true,"",adaptadorFacultad)
            adaptadorFacultad.notifyDataSetChanged()
        }


    }

    private fun consultarDatos(adaptadorFacultad: AdaptadorFacultad) {
            val ref = db.collection("facultades")
            limpiarArreglo()
            adaptadorFacultad.notifyDataSetChanged()
            ref
                .orderBy("codigo",com.google.firebase.firestore.Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {
                    for(facu in it){
                        anadirFacuArreglo(arreglo,facu,adaptadorFacultad)
                    }
                }
    }

    private fun anadirFacuArreglo(arreglo: ArrayList<Facultad>, facu: QueryDocumentSnapshot?, adaptadorFacultad: AdaptadorFacultad) {
        val nuevaFacu = Facultad(
            facu?.data?.get("nombre") as String,
            facu?.data?.get("codigo") as Number,
            facu?.data?.get("tieneMaestrias").toString().toBoolean(),
            facu?.data?.get("cantidadAlumnos") as Number,
            null
        )
        arreglo.add(nuevaFacu)
        adaptadorFacultad.notifyDataSetChanged()
    }



    private fun leer(){
        val db = Firebase.firestore
        val ref = db.collection("facultades").document("1")
        ref.get().addOnSuccessListener { document ->
            if (document != null){
                Log.d("TAG","documento: ${document.data}")
            }else{
                Log.d("TAG","nope")
            }
        }
            .addOnFailureListener{exc ->
                Log.d("TAG", "error: ${exc}")

            }
    }

    override fun onStart() {
        super.onStart()
        Log.i("test","start")
        limpiarArreglo()
        consultarDatos(adaptadorFacultad)
        adaptadorFacultad.notifyDataSetChanged()
    }



    private fun abrirActividadConParametros(clase:Class<*>,esNuevo:Boolean,key:String,adaptadorFacultad: AdaptadorFacultad){
        val intentExplicito = Intent(this,clase)
        intentExplicito.putExtra("esNuevo",esNuevo)
        intentExplicito.putExtra("key",key)
        contenidoIntentExplicito.launch(intentExplicito)
        adaptadorFacultad.notifyDataSetChanged()
    }

    fun limpiarArreglo() {
        arreglo.clear()
    }


}