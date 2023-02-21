package com.example.goodread_dwgq

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
            if (result.resultCode == Activity.RESULT_OK){
                if (result.data !=null){
                    val data = result.data
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //quitar barra titulo
        supportActionBar?.hide()
        //lista

        val listaPublicaciones = arrayListOf<Publicacion>()
        val libro = Libro(1,"Juego","Descripcion libro 1","pepe autor")
        val libro2 = Libro(2,"Juego 2","Descripcion libro 2","juan autor")
        val libro3 = Libro(3,"Juego 3","Descripcion libro 3","jhon autor")

        val libro4 = Libro(4,"camino ","Descripcion libro 1","pepe autor")
        val libro5 = Libro(5,"camino 2","Descripcion libro 2","juan autor")
        val libro6 = Libro(6,"camino 3","Descripcion libro 3","jhon autor")

        val libro7 = Libro(7,"fuego","Descripcion libro 1","pepe autor")
        val libro8 = Libro(8,"fuego 2","Descripcion libro 2","juan autor")
        val libro9 = Libro(9,"fuego 3","Descripcion libro 3","jhon autor")

        val listaLibro1 = arrayListOf(libro,libro2,libro3)
        val listaLibro2 = arrayListOf(libro4,libro5,libro6)
        val listaLibro3 = arrayListOf(libro7,libro8,libro9)

        val lector1 = Lector(1,"Daniel",listaLibro1)
        val lector2 = Lector(2,"Juan",listaLibro2)

        listaPublicaciones.add(
            Publicacion(1, lector1,libro4,"Comentario publicacion 1"))
        listaPublicaciones.add(
            Publicacion(2,lector2 ,libro,"Comentario publicacion 2")
        )
        listaPublicaciones.add(
            Publicacion(3, Lector(3,"mateo",listaLibro3),libro5,"Comentario publicacion 3")
        )

        //init recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.rv_home)
        inicializarRecyclerView(listaPublicaciones,recyclerView)

        val botonNotifi = findViewById<Button>(R.id.btn_notificaciones)
        botonNotifi.setOnClickListener {
            irActividad(Notificaciones::class.java)
        }

        val botnMislibros = findViewById<Button>(R.id.btn_my_books)
        botnMislibros.setOnClickListener { irActividad(mis_libros::class.java) }

    }

    fun inicializarRecyclerView(listaPublicaciones: ArrayList<Publicacion>, recyclerView: RecyclerView) {
        val adaptador = home_recycler_adapter(this,listaPublicaciones,recyclerView)
        recyclerView.adapter=adaptador
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this,clase)
        startActivity(intent)
    }

}