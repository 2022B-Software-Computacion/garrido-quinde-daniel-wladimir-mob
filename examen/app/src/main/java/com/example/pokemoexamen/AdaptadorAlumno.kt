package com.example.pokemoexamen

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class AdaptadorAlumno(
    private val contexto:verAlumnos,
    private val listaAlumno:ArrayList<Alumno>
):RecyclerView.Adapter<AdaptadorAlumno.MyViewHolder>() {
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
    var nombre:TextView
    var ira:TextView
    var esRepetido:TextView
    var sexo:TextView
    var mMenu:ImageView

    init {
        nombre=view.findViewById(R.id.tv_nombre_alumno)
        ira=view.findViewById(R.id.tv_sexo)
        esRepetido=view.findViewById(R.id.tv_es_repetido)
        sexo=view.findViewById(R.id.tv_ira)
        mMenu=view.findViewById(R.id.im_alumno)
        mMenu.setOnClickListener{popupMenus(it)}
    }
        fun abrirActividadConParametros(
            clase:Class<*>,esNuevo:Boolean,id_alumno:String,id_facultad:String){
            val intentExplicito = Intent(contexto,clase)
            intentExplicito.putExtra("esNuevo",esNuevo)
            intentExplicito.putExtra("id_alumno",id_alumno)
            intentExplicito.putExtra("id_facultad",id_facultad)
            ContextCompat.startActivity(contexto, intentExplicito, null)
        }

        private fun popupMenus(v:View) {
            val popupMenus = PopupMenu(contexto,v)
            popupMenus.inflate(R.menu.menualumno)
            popupMenus.setOnMenuItemClickListener { it ->
                when (it.itemId){
                    R.id.mi_editar_alumno -> {
                        abrirActividadConParametros(
                            alumno_form::class.java,
                            false,
                            listaAlumno[position].id.toString(),
                            contexto.keyFacu)
                        contexto.adaptadorAlumno.notifyDataSetChanged()
                        Log.i("test","adaptador alumno key: "+contexto.keyFacu)
                        true
                    }
                    R.id.mi_eliminar_alumno -> {//"${idItemSeleccionado}"
                        Log.i("msg","eliminar facultad")
                        AlertDialog.Builder(contexto)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_baseline_warning_amber_24)
                            .setMessage("Are you sure delete this info")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                borrarRegistro(listaAlumno[position].id.toString())
                                contexto.adaptadorAlumno.notifyDataSetChanged()
                                dialog.dismiss()
                            }
                            .setNegativeButton("NO"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .show()

                        true}
                    else -> {true}}
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu, true)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.elemento_lista_alumno,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val alumnoActual = this.listaAlumno[position]
        holder.nombre.text = alumnoActual.nombre
        holder.ira.text = alumnoActual.ira.toString()
        holder.esRepetido.text = alumnoActual.esRepetido.toString()
        holder.sexo.text = alumnoActual.sexo
        when(Random.nextInt(4)){
            0 -> {holder.mMenu.setImageResource(R.drawable.a1) }
            1-> {holder.mMenu.setImageResource(R.drawable.a2) }
            2->{holder.mMenu.setImageResource(R.drawable.a3) }
            3->{holder.mMenu.setImageResource(R.drawable.a4) }
            4 -> {holder.mMenu.setImageResource(R.drawable.a5)}
            else -> {holder.mMenu.setImageResource(R.drawable.a6) }
        }

    }

    override fun getItemCount(): Int {
        return this.listaAlumno.size
    }

    fun borrarRegistro(alumnoId: String){
        val db = Firebase.firestore
        Log.i("test",contexto.keyFacu)
        db.collection("facultades")
            .document(contexto.keyFacu)
            .collection("Alumnos")
            .document(alumnoId)
            .delete()
            .addOnCompleteListener{
                Log.i("valor","exito")
            }
            .addOnFailureListener{
                Log.i("valor","falla")
            }


        Toast.makeText(contexto, "Borrado exitosamente ", Toast.LENGTH_SHORT).show()
    }

}