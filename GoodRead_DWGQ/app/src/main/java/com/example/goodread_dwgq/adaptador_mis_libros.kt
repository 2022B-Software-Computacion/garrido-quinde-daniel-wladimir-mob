package com.example.goodread_dwgq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adaptador_mis_libros (
    val contexto: mis_libros,
    val listaLibros: ArrayList<Lector>,
    val recyclerView: RecyclerView
        ): RecyclerView.Adapter<adaptador_mis_libros.MyviewHolder>(){
    inner class MyviewHolder(view: View):RecyclerView.ViewHolder(view){
        val img1:ImageView
        val img2:ImageView
        val img3:ImageView
        val nombreGal: TextView
        val comentGal: TextView
        init {
            img1 = view.findViewById(R.id.iv_ml_1)
            img2 = view.findViewById(R.id.iv_ml_2)
            img3 = view.findViewById(R.id.iv_ml_3)
            nombreGal = view.findViewById(R.id.tv_galeria_titulo)
            comentGal = view.findViewById(R.id.tv_galeria_descripcion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_view_mis_libros,parent,false)
        return MyviewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val galeriaActual = this.listaLibros[position] //lista de lectores
        holder.nombreGal.text = "Libros por leer"
        holder.comentGal.text = "${this.listaLibros.size} libros"
        holder.img1.setImageResource(R.drawable.ejemplo)
        holder.img2.setImageResource(R.drawable.ejemplo)
        holder.img3.setImageResource(R.drawable.ejemplo)
    }

    override fun getItemCount(): Int {
        return this.listaLibros.size
    }


}