package com.example.goodread_dwgq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class mis_libros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_libros)

        //definir lista
        val listaPublicaciones = arrayListOf<Publicacion>()
        val libro = Libro(1,"Juego","nuevo","pepe autor")
        listaPublicaciones.add(
            Publicacion(1, Lector(1,"Daniel",null),libro,"comentario"))
        listaPublicaciones.add(
            Publicacion(2, Lector(2,"Juan",null),libro,"comentario2")
        )
        val lectores = arrayListOf( Lector(1,"Daniel",null),Lector(2,"Juan",null)
        ,Lector(3,"Juan2",null))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_mis_libros)
        inicializarRecyclerView(lectores,recyclerView)

    }

    fun inicializarRecyclerView(lectores: ArrayList<Lector>, recyclerView: RecyclerView) {
        val adaptador = adaptador_mis_libros(this,lectores,recyclerView)
        recyclerView.adapter=adaptador
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }
}