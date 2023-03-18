package com.example.pokemoexamen

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Objects

class verAlumnos : AppCompatActivity() {
    var arregloAlumnos:ArrayList<Alumno> = arrayListOf()
    val adaptadorAlumno = AdaptadorAlumno(this,arregloAlumnos)
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    Log.i("cambio", "efectivamente")
                }
            }
        }
    var keyFacu = ""
    //val key = intent.getStringExtra("key")

    override fun onCreate(savedInstanceState: Bundle?) {
        val key = intent.getStringExtra("key")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_alumnos)
        if (key != null) {
            keyFacu=key
        }
        val botonCrear = findViewById<Button>(R.id.btnCrearAlumno)
        botonCrear.setOnClickListener {
            abrirActividadConParametros(alumno_form::class.java,true,"",keyFacu)
        }
        Log.i("test","oncreate")


        //recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.lv_alumnos)
        recyclerView.adapter = adaptadorAlumno
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
//        limpiarArreglo()
//        consultar(adaptadorAlumno,key)
        adaptadorAlumno.notifyDataSetChanged()
    }


    override fun onStart() {
        super.onStart()
        val key = intent.getStringExtra("key")
        Log.i("test","start")
        limpiarArreglo()
        consultar(adaptadorAlumno, key)
        adaptadorAlumno.notifyDataSetChanged()
    }

    fun abrirActividadConParametros(
        clase:Class<*>,esNuevo:Boolean,id_alumno:String,id_facultad:String){
        val intentExplicito = Intent(this,clase)
        intentExplicito.putExtra("esNuevo",esNuevo)
        intentExplicito.putExtra("id_alumno",id_alumno)
        intentExplicito.putExtra("id_facultad",id_facultad)
        ContextCompat.startActivity(this, intentExplicito, null)
    }

    private fun limpiarArreglo() {
        arregloAlumnos.clear()
    }




    private fun consultar(adaptadorAlumno: AdaptadorAlumno, key: String?) {
        val db = Firebase.firestore
        val ref = key?.let { db.collection("facultades").document(it) }
        ref?.collection("Alumnos")?.orderBy("codigo",com.google.firebase.firestore.Query.Direction.ASCENDING)
            ?.get()?.addOnSuccessListener {
            for (alumno in it){
                anadirAlumnos(arregloAlumnos,alumno,adaptadorAlumno)
            }
        }

    }

    private fun anadirAlumnos(arregloAlumnos: ArrayList<Alumno>, alumno: QueryDocumentSnapshot?, adaptadorAlumno: AdaptadorAlumno) {
        val nuevoAlumno = Alumno(
            alumno?.data?.get("nombre") as String,
            alumno?.data?.get("ira") as Number,
            alumno?.data?.get("esRepetido") as Boolean,
            alumno?.data?.get("sexo") as String,
            alumno?.data?.get("codigo") as Number
        )
        arregloAlumnos.add(nuevoAlumno)
        Log.i("test",nuevoAlumno.toString())
        adaptadorAlumno.notifyDataSetChanged()
    }

}


