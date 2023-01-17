package com.example.dwgq_application1

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this,//contexto
            android.R.layout.simple_list_item_1,//como se va a ver
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
            botonAnadirListView
                .setOnClickListener {
                    anadirEntrenador(adaptador)
                }

        registerForContextMenu(listView)
        }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llenamos las aplicaciones del menu:
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        //obtener el id del arraylist seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar -> {
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar -> {
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton("ACEPTAR",DialogInterface.OnClickListener{
            dialog,which -> }
        )
        builder.setNegativeButton("Cancelar", null)
        val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)
        val seleccionPrevia = booleanArrayOf(true,false,false)//aqui elijo de antemano, la seleccion default, el
        //primer elmento se selecciona solito por el true `
        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
             { dialog,
                which,
                isChecked ->
                "Dio click en el item ${which}"
             }
        )
        val dialogo = builder.create()
        dialogo.show()
    }


        fun anadirEntrenador(
            adaptador: ArrayAdapter<Bentrenador>
        ){
            arreglo.add(
                Bentrenador(
                    "Adrian",
                    "Descripcion"
                )
            )
            adaptador.notifyDataSetChanged()
    }



}