package com.example.pokemoexamen

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

class alumno_form : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumno_form)
        val id_alumno = intent.getStringExtra("id_alumno")
        val esNuevo = intent.getBooleanExtra("esNuevo",false)
        val id_facultad = intent.getStringExtra("id_facultad")

        //variables
        val nombre = findViewById<EditText>(R.id.editText_Nombre_alumno)
        val switchSexo = findViewById<Switch>(R.id.switch_alumno_generos)
        val ira=findViewById<EditText>(R.id.edit_alumno_ira)
        val switchMateria = findViewById<Switch>(R.id.switch_alumno_repetido)
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_alumno_alumno)
        val codigo_alumno = findViewById<EditText>(R.id.edit_codigo_alumno)

        //se setea animacion de los switch
        switchMateria.setOnCheckedChangeListener{
            buttonView,isChecked ->
                if(isChecked){
                    switchMateria.setText("No")
                }else{
                    switchMateria.setText("Si")
                }
        }

        switchSexo.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                switchSexo.setText("Masculino")
            }else
                switchSexo.setText("Femenino")
        }

        fun generarBol(valor:Boolean):String{
            if (valor){
                return "M"
            }else{
                return "F"
            }
        }

        //se setea el update
        if(!esNuevo){
            if (id_alumno != null){
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
                    .document(id_facultad.toString())
                    .collection("Alumnos")
                    .document(id_alumno)
                    .get()
                    .addOnSuccessListener {
                        Log.i("test",it.data.toString())
                        nombre.setText(it.data?.get("nombre").toString())
                        ira.setText(it.data?.get("ira").toString())
                        codigo_alumno.setText(it.data?.get("codigo").toString())
                        switchMateria.setChecked(it.data?.get("esRepetido").toString().toBoolean())
                        if (it.data?.get("sexo")?.equals("M") == true){
                            switchSexo.setChecked(true)
                        }else{
                            switchSexo.setChecked(false)
                        }
                    }
            }
        }

        //se setea el boton
        botonGuardar.setOnClickListener {
            val noExistenCampos = nombre.text.toString() == "" || ira.text.toString()==""
                    || codigo_alumno.text.toString()==""
            if(!noExistenCampos){
                Log.i("prueba", nombre.text.toString())
                Log.i("prueba", ira.text.toString().toDouble().toString())
//                Log.i("prueba", switchMateria.isChecked.toString())
//                Log.i("prueba", generarBol(switchSexo.isChecked).toString())
//                Log.i("prueba", codigo_alumno.text.toString())

                    val alumno = Alumno(
                        nombre.text.toString(),
                        ira.text.toString().toDouble(),
                        !switchMateria.isChecked,
                        generarBol(switchSexo.isChecked),
                        codigo_alumno.text.toString().toInt())
                Log.i("update",alumno.toString()+"   "+id_facultad.toString())
                val db = Firebase.firestore
                if (id_facultad != null) {
                    db.collection("facultades").document(id_facultad)
                        .collection("Alumnos")
                        .document(codigo_alumno.text.toString())
                        .set(alumno)
                }


                Toast.makeText(this,"Ingresado exitosamente", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Complete los datos no sea bobo",Toast.LENGTH_SHORT).show()

            }

        }


    }
}