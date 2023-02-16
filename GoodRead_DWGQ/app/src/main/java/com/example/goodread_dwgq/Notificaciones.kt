package com.example.goodread_dwgq
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class Notificaciones : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)

        //definir lista
        val listaPublicaciones = arrayListOf<Publicacion>()
        val libro = Libro(1,"Juego","nuevo","pepe autor")
        listaPublicaciones.add(
            Publicacion(1, Lector(1,"Daniel",null),libro,"comentario"))
        listaPublicaciones.add(
            Publicacion(2, Lector(2,"Juan",null),libro,"comentario2")
        )
        //init
        val recyclerView = findViewById<RecyclerView>(R.id.rv_notifi)
        inicializarRecyclerView(listaPublicaciones,recyclerView)
    }


    fun inicializarRecyclerView(listaPublicaciones: ArrayList<Publicacion>, recyclerView: RecyclerView) {
        val adaptador = adaptadorNotificaciones(this,listaPublicaciones,recyclerView)
        recyclerView.adapter=adaptador
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

}