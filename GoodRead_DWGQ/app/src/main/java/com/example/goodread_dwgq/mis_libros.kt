package com.example.goodread_dwgq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Timer
import java.util.TimerTask

class mis_libros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_libros)
        supportActionBar?.hide()
        //definir lista
        val libro = Libro(1,"Juego","Descripcion libro 1","pepe autor")
        val libro2 = Libro(2,"Juego 2","Descripcion libro 2","juan autor")
        val libro3 = Libro(3,"Juego 3","Descripcion libro 3","jhon autor")

        val libro4 = Libro(4,"camino ","Descripcion libro 1","pepe autor")
        val libro5 = Libro(5,"camino 2","Descripcion libro 2","juan autor")
        val libro6 = Libro(6,"camino 3","Descripcion libro 3","jhon autor")


        val listaLibro1 = arrayListOf(libro,libro2,libro3)
        val listaLibro2 = arrayListOf(libro4,libro5,libro6)


        val lector1 = Lector(1,"Daniel",listaLibro1)
        val lector2 = Lector(2,"Juan",listaLibro2)


        val recyclerView = findViewById<RecyclerView>(R.id.rv_mis_libros)
        inicializarRecyclerView(arrayListOf(lector2,lector1),recyclerView)

        val tfUpdate = findViewById<TextView>(R.id.tv_update_progress)
        tfUpdate.setOnClickListener{
            aumentarProgreso()
        }


    }

    fun inicializarRecyclerView(lectores: ArrayList<Lector>, recyclerView: RecyclerView) {
        val adaptador = adaptador_mis_libros(this,lectores,recyclerView)
        recyclerView.adapter=adaptador
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarProgreso(){
        val progreso = findViewById<ProgressBar>(R.id.pb_mis_libros)
        val tf_progreso = findViewById<TextView>(R.id.tv_progreso)
        var progresoNumero = progreso.progress
        if (progresoNumero>=100){
            progreso.setProgress(35,true)
            tf_progreso.text = "Progreso: 35%"
        }else{
            progreso.setProgress(progresoNumero+8,true)
            tf_progreso.text = "Progreso: ${progreso.progress}%"
        }
    }

}