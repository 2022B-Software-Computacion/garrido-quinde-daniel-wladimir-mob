package com.example.dwgq_application1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type
import java.text.FieldPosition
import android.widget.TextView


class FRecyclerViewAdaptadorNombreCedula(
    private val contexto: GRecyclerView,
    private val lista: ArrayList<Bentrenador>,
    private val recyclerView: RecyclerView
    ): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>() {
    inner class MyViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionButton: Button
        var numeroLikes =0
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView=view.findViewById(R.id.tv_likes)
            accionButton=view.findViewById(R.id.btn_dar_like)
            accionButton.setOnClickListener { anadirLikes()}
        }

        private fun anadirLikes(){
            numeroLikes += 1
            likesTextView.text = numeroLikes.toString()
        }
    }


    //setear el layaout que vamos a utilizar
    //anida el layout a esta clase
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_view_vista,
            parent,
            false)
        return MyViewHolder(itemView)
    }


    //setear los datos para la iteracion
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.descripcion
        holder.accionButton.text = "Like ${entrenadorActual.nombre}"
        holder.likesTextView.text = "0"
    }

    //tamanio del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }

}