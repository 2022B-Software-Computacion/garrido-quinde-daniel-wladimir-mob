package com.example.goodread_dwgq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class adaptador_mis_libros (
    val contexto: mis_libros,
    val listaLectores: ArrayList<Lector>,
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
        val galeriaActual = this.listaLectores[position] //lista de lectores
        var imagaen = valorRandom2()
        holder.nombreGal.text = galeriaActual.librosLeidos?.get(0)?.descripcion ?: String()
        holder.comentGal.text = "${valorRandom()} libros"
        when(imagaen){
                1->{
                holder.img1.setImageResource(R.drawable.l1)
                holder.img2.setImageResource(R.drawable.l9)
                holder.img3.setImageResource(R.drawable.l2)
                }
                2->{
                    holder.img1.setImageResource(R.drawable.l3)
                    holder.img2.setImageResource(R.drawable.l8)
                    holder.img3.setImageResource(R.drawable.l4)
                }
                3->{
                    holder.img1.setImageResource(R.drawable.l5)
                    holder.img2.setImageResource(R.drawable.l7)
                    holder.img3.setImageResource(R.drawable.l6)
                }
            4->{
                holder.img1.setImageResource(R.drawable.l2)
                holder.img2.setImageResource(R.drawable.l8)
                holder.img3.setImageResource(R.drawable.l3)
            }
            5->{
                holder.img1.setImageResource(R.drawable.l4)
                holder.img2.setImageResource(R.drawable.l7)
                holder.img3.setImageResource(R.drawable.l3)
            }
        }

    }

    override fun getItemCount(): Int {
        return this.listaLectores.size
    }

    fun valorRandom():Int{
       return Random.nextInt(3,15)
    }

    fun valorRandom2():Int{
        return Random.nextInt(1,3)
    }

}