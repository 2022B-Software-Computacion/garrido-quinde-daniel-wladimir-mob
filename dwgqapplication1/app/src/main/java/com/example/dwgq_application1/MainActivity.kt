package com.example.dwgq_application1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")} presente")
                }
            }
        }

    val contenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == RESULT_OK){
            if(result.data != null){
                if(result.data!!.data !=null){
                    val uri : Uri = result.data!!.data!!
                    val cursor = contentResolver.query(
                        uri,null,null,null,null,null
                    )
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(indiceTelefono!!)
                    cursor?.close()
                    Log.i("intent-epn","telefono ${telefono}")
                }

            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Base de datos
        EBaseDeDatos.TablaEntrenador = ESqliteHelperEntrenador(this)


        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener{
                irActividad(ACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener{
            irActividad(BListView::class.java)
        }
        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

        val botonSQLlite = findViewById<Button>(R.id.btn_sqllite)
        botonSQLlite.setOnClickListener {
            irActividad(ECrudEntrenador::class.java)
        }

        val botonRecyclerView = findViewById<Button>(R.id.btn_revcycler_viewv)
        botonRecyclerView.setOnClickListener {
            irActividad(GRecyclerView::class.java)
        }

        val botonMaps = findViewById<Button>(R.id.btn_google_maps)
        botonMaps.setOnClickListener {
            irActividad(HGoogleMapsActivity::class.java)
        }
        val botonFirebase = findViewById<Button>(R.id.btn_intent_firebase_ui)
        botonFirebase.setOnClickListener {
            irActividad(IFirebaseUIAuth::class.java)
        }
    }




    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this,clase)
        startActivity(intent)
    }


    fun abrirActividadConParametros(clase:Class<*>){
        val intentExplicito = Intent(this,clase)
        intentExplicito.putExtra("nombre","Daniel")
        intentExplicito.putExtra("apellido: ","garrido")
        intentExplicito.putExtra("edad",22)
        intentExplicito.putExtra(
            "Entrenador_Principal",Bentrenador(1,"Daniel2","Garrido2")
        )
        contenidoIntentExplicito.launch(intentExplicito)
    }
}