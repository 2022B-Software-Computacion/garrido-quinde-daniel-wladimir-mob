package com.example.goodread_dwgq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adaptadorNotificaciones(
        val contexto: Notificaciones,
        val listaLibros: ArrayList<Libro>,
        val recyclerView: RecyclerView
    ):RecyclerView.Adapter<adaptadorNotificaciones.MyviewHolder>() {
    inner class MyviewHolder(view: View):RecyclerView.ViewHolder(view){
        val imagen:ImageView
        val nombre:TextView
        val descripcion:TextView
        init {
            imagen = view.findViewById(R.id.iv_user_notifi)
            nombre = view.findViewById(R.id.tv_nombre)
            descripcion = view.findViewById(R.id.tv_coment)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_view_notificaciones,
                parent,false)
        return MyviewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val notifiActual = this.listaLibros[position]
            holder.nombre.text = notifiActual.autor
        holder.descripcion.text = notifiActual.descripcion
        when (position){
            1 ->holder.imagen.setImageResource(R.drawable.l1)
            2 -> holder.imagen.setImageResource(R.drawable.l2)
            3->holder.imagen.setImageResource(R.drawable.l3)
            4->holder.imagen.setImageResource(R.drawable.l4)
            else -> holder.imagen.setImageResource(R.drawable.l5)
        }
    }

    override fun getItemCount(): Int {
        return this.listaLibros.size
    }
}