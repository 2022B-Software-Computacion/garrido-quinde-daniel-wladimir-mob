package com.example.dwgq_application1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        //definirLista
        val listaEntrenador = arrayListOf<Bentrenador>()
        listaEntrenador
            .add(Bentrenador(1,"daniel1","ejemplo1"))
        listaEntrenador
            .add(Bentrenador(2,"daniel2","ejemplo2"))
        //init recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.rv_entrenadores)
        inicializarRecyvlerView(listaEntrenador, recyclerView)
    }

    fun inicializarRecyvlerView(lista:ArrayList<Bentrenador>,recyclerView: RecyclerView){
        val adapdtador = FRecyclerViewAdaptadorNombreCedula(
            this,lista,recyclerView
        )
        recyclerView.adapter = adapdtador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapdtador.notifyDataSetChanged()
    }

    fun aumentarTotalLikes(){
        totalLikes +=1
        val totalLikesTextView = findViewById<TextView>(R.id.tv_total_likes)
        totalLikesTextView.text = totalLikes.toString()
    }

}