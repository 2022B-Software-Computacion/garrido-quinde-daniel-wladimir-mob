package com.example.dwgq_application1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.imput_id)
                val nombre = findViewById<EditText>(R.id.imput_nombre)
                val descripcion = findViewById<EditText>(R.id.imput_descripcion)
                val entrenador = EBaseDeDatos.TablaEntrenador!!
                    .consultarEntrenador(id.text.toString().toInt())
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre)
                descripcion.setText(entrenador.descripcion)
            }

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.imput_nombre)
            val descripcion = findViewById<EditText>(R.id.imput_descripcion)
            EBaseDeDatos.TablaEntrenador!!.crearEntrenador(
                nombre.text.toString()
                ,descripcion.text.toString()
            )
        }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar_bdd)
        botonActualizarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.imput_id)
            val nombre = findViewById<EditText>(R.id.imput_nombre)
            val descripcion = findViewById<EditText>(R.id.imput_descripcion)
            EBaseDeDatos.TablaEntrenador!!.actualizarEntrenadorFormulario(
                nombre.text.toString(),
                descripcion.text.toString(),
                id.text.toString().toInt()
            )
        }


        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener {
            val id = findViewById<EditText>(R.id.imput_id)
            EBaseDeDatos.TablaEntrenador!!.eliminarEntrenadorFormulario(id.text.toString().toInt())
        }
    }
}