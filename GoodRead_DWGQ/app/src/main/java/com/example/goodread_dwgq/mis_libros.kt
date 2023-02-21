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
        val libro = Libro(1,"Juego","Quiero Leer","pepe autor")
        val libro2 = Libro(2,"Juego 2","Favoritos 2022","juan autor")
        val libro3 = Libro(3,"Juego 3","Descepcion","jhon autor")

        val libro4 = Libro(4,"camino ","Terror y suspenso","pepe autor")
        val libro5 = Libro(5,"camino 2","Descepcion","juan autor")
        val libro6 = Libro(6,"camino 3","Favoritos pendientes","jhon autor")

        val libro7 = Libro(7,"fuego","Libros en ingles","pepe autor")
        val libro8 = Libro(8,"fuego 2","Terror y suspenso","juan autor")
        val libro9 = Libro(9,"fuego 3","Favoritos 2023","jhon autor")

        val listaLibro1 = arrayListOf(libro,libro2,libro3)
        val listaLibro2 = arrayListOf(libro4,libro5,libro6)
        val listaLibro3 = arrayListOf(libro7,libro8,libro9)
        val listaLibro4 = arrayListOf(libro,libro5,libro8)
        val listaLibro5 = arrayListOf(libro2,libro6,libro4)

        val lector1 = Lector(1,"Daniel",listaLibro1)
        val lector2 = Lector(2,"Juan",listaLibro2)
        val lector3 = Lector(3,"jaime",listaLibro3)
        val lector4 = Lector(4,"Ariel",listaLibro4)
        val lector5 = Lector(5,"Wilma",listaLibro5)


        val recyclerView = findViewById<RecyclerView>(R.id.rv_mis_libros)
        inicializarRecyclerView(arrayListOf(lector2,lector1,lector3,lector4,lector5),recyclerView)

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