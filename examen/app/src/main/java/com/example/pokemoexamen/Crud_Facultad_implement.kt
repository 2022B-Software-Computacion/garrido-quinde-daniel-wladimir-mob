package com.example.pokemoexamen


import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class Crud_Facultad_implement:CRUD<Facultad,String> {

    val fire = Firebase.firestore.collection("facultades")

    override fun create(entity: Facultad) {
        fire.document(entity.codigo.toString()).set(entity).addOnCompleteListener{
            Log.i("crud","se creo facultad ok")}
            .addOnFailureListener{Log.i("crud","no se creo la facultad")}
    }


    override fun update(entity: Facultad) {
        fire.document(entity.codigo.toString()).set(entity).addOnCompleteListener{
            Log.i("crud","se actualizo facultad ok")}
            .addOnFailureListener{Log.i("crud","no se actualizo la facultad")}
    }

    override fun delete(id: String) {
        fire.document(id).delete().addOnCompleteListener{Log.i("crud","se elimino facultad ok")}
            .addOnFailureListener{Log.i("crud","no se eliminio la facultad")}
    }

    suspend fun getAllFacultades():java.util.ArrayList<Facultad>{
        Log.i("test","3.1")
        val  document = Firebase.firestore.collection("facultades")
            .orderBy("Codigo",com.google.firebase.firestore.Query.Direction.ASCENDING)
            .get().await()
        val arregloFacultades:java.util.ArrayList<Facultad> = arrayListOf()
        for (fac in document){
            arregloFacultades.add(Facultad(
                fac.data.get("nombre").toString(),
                fac.data.get("codigo") as Number,
                fac.data.get("tieneMaestrias").toString().toBoolean(),
                fac.data.get("cantidadAlumnos") as Number,
                null
            ))
            Log.i("test","3.3")
        }
        Log.i("test","3.4")
        return arregloFacultades
    }

    override suspend fun read(id: String): Facultad? {
        val document = Firebase.firestore.collection("facultades").document(id)
        return try {
            val snap = document.get().await()
            Log.i("crud","se read facultad ok")
            Facultad(snap.data?.get("Nombre") as String,
            snap.data?.get("Codigo") as Number,
            snap.data?.get("tieneMaestrias") as Boolean,
            snap.data?.get("cantidad alumnos") as Number,
            null)
        }catch (e:FirebaseException){
            Log.i("crud","no se leyo/read la facultad")
            null
        }
    }



    fun writeData(){
        val facultades = Firebase.firestore.collection("facultades")

        //ejemplos alumnos
        val ejemploAlum1 = hashMapOf(
            "codigo" to 1,
            "nombre" to "Daniel",
            "ira" to 32.5,
            "esRepetido" to true,
            "sexo" to "M")

        val ejemploAlum2 = hashMapOf(
            "codigo" to 2,
            "nombre" to "Juan",
            "ira" to 28.5,
            "esRepetido" to true,
            "sexo" to "M")

        val ejemploAlum3 = hashMapOf(
            "codigo" to 3,
            "nombre" to "Daniela",
            "ira" to 40,
            "esRepetido" to false,
            "sexo" to "F")

        val ejemploAlum4 = hashMapOf(
            "codigo" to 4,
            "nombre" to "Wladimir",
            "ira" to 32.5,
            "esRepetido" to false,
            "sexo" to "M")

        val ejemploAlum5 = hashMapOf(
            "codigo" to 5,
            "nombre" to "Edu",
            "ira" to 32.5,
            "esRepetido" to true,
            "sexo" to "M")

        val ejemploAlum6 = hashMapOf(
            "codigo" to 6,
            "nombre" to "JAime",
            "ira" to 85,
            "esRepetido" to false,
            "sexo" to "M")

        val arregloAlum = arrayListOf(ejemploAlum1,ejemploAlum2,ejemploAlum3,ejemploAlum4
            ,ejemploAlum5,ejemploAlum6)

        val facu1 = hashMapOf(
            "Nombre" to "Sistemas",
            "Codigo" to 1,
            "tieneMaestrias" to true,
            "cantidad alumnos" to 300,
        )
        val facu2 = hashMapOf(
            "Nombre" to "Geologia",
            "Codigo" to 2,
            "tieneMaestrias" to false,
            "cantidad alumnos" to 500,
        )
        val facu3 = hashMapOf(
            "Nombre" to "Quimica",
            "Codigo" to 3,
            "tieneMaestrias" to true,
            "cantidad alumnos" to 100,
        )

        val listaFacultades = arrayListOf(facu1,facu2,facu3)
        for (facultad in listaFacultades){
            facultades.document(facultad["Codigo"].toString()).set(facultad)
            for (it in arregloAlum){
                facultades.document(facultad["Codigo"].toString()).collection("Alumnos")
                    .document(it["codigo"].toString()).set(it)
            }
        }

    }
}