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
        val listaNotificaciones: ArrayList<Publicacion>,
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
        val notifiActual = this.listaNotificaciones[position]
        holder.nombre.text = notifiActual.lector?.nombre ?: String()
        holder.descripcion.text = notifiActual.libro?.descripcion ?: String()
        holder.imagen.setImageResource(R.drawable.user)
    }

    override fun getItemCount(): Int {
        return this.listaNotificaciones.size
    }
}