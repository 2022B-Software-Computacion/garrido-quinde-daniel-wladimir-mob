package com.example.dwgq_application1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.concurrent.BlockingDeque

class ESqliteHelperEntrenador(
    contexto:Context?//nombre BDD

): SQLiteOpenHelper(
    contexto,
    "moviles",//nombre BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        //sql tal cual
        val scriptSqlCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSqlCrearTablaEntrenador)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
//puede estar vacio
    }


    fun crearEntrenador(nombre:String,descrpcion:String
    ):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre",nombre)
        valoresAGuardar.put("descripcion",descrpcion)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "ENTRENADOR",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return resultadoGuardar.toInt() != -1
    }


    fun eliminarEntrenadorFormulario(id:Int):Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return resultadoEliminacion != -1
        //clase 2023-01-17
        //42:06
    }

    fun actualizarEntrenadorFormulario(
        nombre:String,
        descrpcion: String,
        idActualizar:Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre",nombre)
        valoresAActualizar.put("descripcion",descrpcion)
        val resultadoActualizacion = conexionEscritura
            .update(
                "ENTRENADOR",
                valoresAActualizar,
                "id=?",
                arrayOf(idActualizar.toString())
            )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }


    fun consultarEntrenador(id:Int):Bentrenador{
        val baseDatosLectura = readableDatabase
        val scriptConsulta = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsulta = baseDatosLectura
            .rawQuery(
                scriptConsulta,
                arrayOf(id.toString())
            )
        //logica buscqueda
        val existenteUsuario = resultadoConsulta.moveToFirst()
        val usuarioEncontrado = Bentrenador(0,"","")
        if(existenteUsuario){
            do{
                val id = resultadoConsulta.getInt(0)
                val nombre = resultadoConsulta.getString(1)
                val descrpcion = resultadoConsulta.getString(2)
            if(id!=null){
                usuarioEncontrado.id=id
                usuarioEncontrado.nombre=nombre
                usuarioEncontrado.descripcion=descrpcion
            }
            }while (resultadoConsulta.moveToNext())
        }
        resultadoConsulta.close()
        return usuarioEncontrado
    }
}