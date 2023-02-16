package com.example.goodread_dwgq

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class home_recycler_adapter(
    val contexto: MainActivity,
    val listaPublicaciones: ArrayList<Publicacion>,
    val recyclerView: RecyclerView
        ): RecyclerView.Adapter<home_recycler_adapter.MyviewHolder>() {
    @SuppressLint("ResourceAsColor")
    inner class MyviewHolder(view:View):RecyclerView.ViewHolder(view){
        val nombreLectorTextView: TextView
        val nombreLibroTextView: TextView
        val nombreAutorTextView: TextView
        val btnWantRead: Button
        val btnComent: Button
        val btnLike: Button
        val img: ImageView

        init {
            nombreLectorTextView = view.findViewById(R.id.tv_Lector)
            nombreLibroTextView = view.findViewById(R.id.tv_libro_titulo)
            nombreAutorTextView = view.findViewById(R.id.tv_autor)
            btnWantRead = view.findViewById(R.id.btn_want_read)
            btnComent = view.findViewById(R.id.btn_comentario)
            btnLike = view.findViewById(R.id.btn_like)
            btnLike.setOnClickListener { cambiarColor() }
            img = view.findViewById(R.id.imageView)
            nombreLectorTextView.setTextColor(R.color.text_user_home)
            btnWantRead.setTextColor(Color.WHITE)
        }

        private fun cambiarColor(){
            btnLike.setBackgroundColor(R.drawable.ex)
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
        holder.img.setImageResource(R.drawable.ejemplo)
    }

    override fun getItemCount(): Int {
        return this.listaPublicaciones.size
    }
}