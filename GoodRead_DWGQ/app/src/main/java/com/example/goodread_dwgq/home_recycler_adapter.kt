package com.example.goodread_dwgq

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class home_recycler_adapter (
     val contexto: MainActivity,
     val listaPublicaciones: ArrayList<Publicacion>,
     val recyclerView: RecyclerView
        ): RecyclerView.Adapter<home_recycler_adapter.MyviewHolder>() {
    inner class MyviewHolder(view:View):RecyclerView.ViewHolder(view){
        val nombreLectorTextView: TextView
        val nombreLibroTextView: TextView
        val nombreAutorTextView: TextView
        val btnWantRead: Button
        val btnComent: Button
        val btnLike: Button

        init {
            nombreLectorTextView = view.findViewById(R.id.tv_Lector)
            nombreLibroTextView = view.findViewById(R.id.tv_libro_titulo)
            nombreAutorTextView = view.findViewById(R.id.tv_autor)
            btnWantRead = view.findViewById(R.id.btn_want_read)
            btnComent = view.findViewById(R.id.btn_comentario)
            btnLike = view.findViewById(R.id.btn_like)
            btnLike.setOnClickListener { cambiarColor() }
        }

        private fun cambiarColor(){
            btnLike.setBackgroundColor(Color.WHITE)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_view_home,
        parent,false)
        return MyviewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
       val publicacionActual = this.listaPublicaciones[position]
        holder.nombreLectorTextView.text = publicacionActual.lector?.nombre ?: String()
        holder.nombreAutorTextView.text = publicacionActual.libro?.autor ?: String()
        holder.nombreLibroTextView.text = publicacionActual.libro?.nombre ?: String()
        //holder.btnWantRead.text = "Want $(publicacionActual.lector?.nombre ?: String())"
        //holder.btnComent.text = "Coemntario hola"
        //holder.btnLike.text = "Like $(publicacionActual.lector?.nombre ?: String())"
    }

    override fun getItemCount(): Int {
        return this.listaPublicaciones.size
    }
}