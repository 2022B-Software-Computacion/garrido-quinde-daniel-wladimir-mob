package com.example.pokemoexamen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FacultadForm : AppCompatActivity() {
    val FR_aux = Crud_Facultad_implement()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facultad_form)
        //intent values
        val key = intent.getStringExtra("key")
        val esNuevo = intent.getBooleanExtra("esNuevo",false)

        //variables
        val nombre = findViewById<EditText>(R.id.editText_Nombre)
        val codigo = findViewById<EditText>(R.id.editNumbreCodigo)
        val numAlumno = findViewById<EditText>(R.id.editNumber_alumnos)
        val switchMaestrias = findViewById<Switch>(R.id.switch_maestrias)
        val botnGuardar = findViewById<Button>(R.id.btn_guardar_alumnos)

        if(!esNuevo){
            if (key != null) {
                //warning

                AlertDialog.Builder(this)
                    .setTitle("Advertencia")
                    .setIcon(R.drawable.ic_baseline_warning_amber_24)
                    .setMessage(
                        "Si cambia el codigo se generara una nueva facultad" +
                                "recuerde borrar la que esta mal xd"
                    )
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                //se seta los datos por default
                val db = Firebase.firestore
                db.collection("facultades")
                    .document(key)
                    .get()
                    .addOnSuccessListener {
                        Log.i("test",it.data.toString())
                        nombre.setText(it.data?.get("nombre").toString())
                        codigo.setText(it.data?.get("codigo").toString())
                        numAlumno.setText(it.data?.get("cantidadAlumnos").toString())
                        switchMaestrias.setChecked((it.data?.get("tieneMaestrias").toString()).toBoolean())
                    }
            }
        }

        botnGuardar.setOnClickListener {
            val noExistenCampos = nombre.text.toString() == "" ||
                    codigo.text.toString() =="" || numAlumno.text.toString()==""
            if(!noExistenCampos){
                val facultad = Facultad(
                    nombre.text.toString(),codigo.text.toString().toInt(),generarBool(switchMaestrias)
                ,numAlumno.text.toString().toInt(),null
                )
                FR_aux.create(facultad)
                Toast.makeText(this,"Ingresado exitosamente",Toast.LENGTH_SHORT).show()
                devolverRespuesta()
            }else{
                Toast.makeText(this,"Complete los datos no sea bobo",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado","wladimir")
        intentDevolverParametros.putExtra("edadModificada",18)
        setResult(
            RESULT_OK,intentDevolverParametros
        )
        finish()
    }


    private fun generarBool(switchMaestrias: Switch?): Boolean {
        return switchMaestrias!!.isChecked
    }
}




