package com.example.goodread_dwgq

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class home_recycler_adapter (
    private val contexto:MainActivity,
    private val lista: ArrayList<Publicacion>,
    private val recyclerView: RecyclerView
        ):RecyclerView.Adapter<home_recycler_adapter.MyViewHolder>(){
            inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}