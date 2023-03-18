package com.example.pokemoexamen
import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.ArrayList


class AdaptadorFacultad(
    private val contexto: MainActivity,
    private val listaFacultades: ArrayList<Facultad>,
):RecyclerView.Adapter<AdaptadorFacultad.MyViewHolder>(){
    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var nombre:TextView
        var codigo:TextView
         var tieneMaestrias:TextView
         var cantidadAlumnos:TextView //la fecha
         var mMenus:ImageView

        init {
            nombre=view.findViewById(R.id.tv_nombre_facu)
            codigo=view.findViewById(R.id.tv_id_facu)
            tieneMaestrias=view.findViewById(R.id.tv_tiene_maestrias)
            cantidadAlumnos=view.findViewById(R.id.tv_num_alum)
            mMenus=view.findViewById(R.id.im_facu)
            mMenus.setOnClickListener{popupMenus(it)}
        }

        fun abrirActividadAlumno(clase:Class<*>,key: String){
            val intent = Intent(contexto,clase)
            intent.putExtra("key",key)
            startActivity(contexto,intent,null)
        }

        fun abrirActividadConParametros(clase:Class<*>,esNuevo:Boolean,key:String){
            val intentExplicito = Intent(contexto,clase)
            intentExplicito.putExtra("esNuevo",esNuevo)
            intentExplicito.putExtra("key",key)
            startActivity(contexto,intentExplicito,null)
        }

        private fun popupMenus(v:View) {
            val popupMenus = PopupMenu(contexto,v)
            popupMenus.inflate(R.menu.menufacultad)
            popupMenus.setOnMenuItemClickListener { it ->
                when (it.itemId){
                    R.id.mi_ver_alumnos -> {
                        abrirActividadAlumno(verAlumnos::class.java,listaFacultades[position].codigo.toString())
                        true
                    }
                    R.id.mi_editar_facultad -> {
                        abrirActividadConParametros(
                            FacultadForm::class.java,
                            false,
                            listaFacultades[position].codigo.toString())
                        contexto.adaptadorFacultad.notifyDataSetChanged()
                        Log.i("msg","editar facultad")
                        true
                    }
                    R.id.mi_eliminar_facultad -> {//"${idItemSeleccionado}"
                        Log.i("msg","eliminar facultad")
                        AlertDialog.Builder(contexto)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_baseline_warning_amber_24)
                            .setMessage("Are you sure delete this info")
                            .setPositiveButton("Yes"){
                                dialog,_->
                                borrarRegistro(listaFacultades[position].codigo.toString())
                                contexto.adaptadorFacultad.notifyDataSetChanged()
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
            .inflate(R.layout.elemento_lista,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val facuActual = this.listaFacultades[position]
        holder.nombre.text =facuActual.nombre
        holder.codigo.text = "Id: ${facuActual.codigo.toString()}"
        holder.tieneMaestrias.text = facuActual.tieneMaestrias.toString()
        holder.cantidadAlumnos.text = facuActual.cantidadAlumnos.toString()

//        Log.i("adf",facuActual.codigo.toString())

        when(facuActual.codigo.toString()){
            "1" -> {holder.mMenus.setImageResource(R.drawable.f1) }
            "2"-> {holder.mMenus.setImageResource(R.drawable.f2) }
            "3"->{holder.mMenus.setImageResource(R.drawable.f3) }
            "4"->{holder.mMenus.setImageResource(R.drawable.f4) }
            else -> {holder.mMenus.setImageResource(R.drawable.f5) }
        }

    }

    override fun getItemCount(): Int {
        return this.listaFacultades.size
    }

    fun borrarRegistro(facultadId: String){
        val db = Firebase.firestore
        db.collection("facultades")
            .document(facultadId)
            .delete()
            .addOnCompleteListener{
                Log.i("valor","exito")
            }
            .addOnFailureListener{
                Log.i("valor","falla")
            }


        Toast.makeText(contexto, "Borrado exitosamente $facultadId",Toast.LENGTH_SHORT).show()
    }



}