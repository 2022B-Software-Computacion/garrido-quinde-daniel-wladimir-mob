package com.example.goodread_dwgq
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class Notificaciones : AppCompatActivity() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)
        supportActionBar?.hide()
        //definir lista
        val listaPublicaciones = arrayListOf<Publicacion>()
        val libro = Libro(1,"Juego","Descripcion libro 1","pepe autor")
        val libro2 = Libro(2,"Juego 2","Descripcion libro 2","juan autor")
        val libro3 = Libro(3,"Juego 3","Descripcion libro 3","jhon autor")
        val libro4 = Libro(4,"camino ","Descripcion libro 4","dan autor")
        val libro5 = Libro(4,"camino 2","Descripcion libro 5","jhon autor")
        val libro6 = Libro(4,"camino 3","Descripcion libro 6","juan autor")
        val libro7 = Libro(4,"rey de  ","Descripcion libro 7","jaime autor")
        val libro8 = Libro(4,"rey de 2 ","Descripcion libro 8","sophie autor")
        val libro9 = Libro(4,"rey de 3","Descripcion libro 9","james autor")
        val libro10 = Libro(4,"tormenta de espadas ","Descripcion libro 10","pepe autor")

        val listaLibro1 = arrayListOf(libro,libro2,libro3,libro4,libro5,libro6,libro7,libro8,libro9,libro10)
        val lector1 = Lector(1,"Daniel",listaLibro1)
        listaPublicaciones.add(
            Publicacion(1, lector1,libro4,"Comentario publicacion 1"))

        //init
        val recyclerView = findViewById<RecyclerView>(R.id.rv_notifi)
        inicializarRecyclerView(listaLibro1,recyclerView)


    }


    fun inicializarRecyclerView(listaLibros: ArrayList<Libro>, recyclerView: RecyclerView) {
        val adaptador = adaptadorNotificaciones(this,listaLibros,recyclerView)
        recyclerView.adapter=adaptador
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adaptador.notifyDataSetChanged()
    }

}