package com.example.dwgq_application1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.client.AuthUiInitProvider
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class IFirebaseUIAuth : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){
        res:FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode === RESULT_OK){
            if(res.resultCode != null){
                //logica del negocio
                //ya se logeo xd
                seLogeo(res.idpResponse!!)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirebase_uiauth)

        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        btnLogin.setOnClickListener {
            val providers = arrayListOf(
                //arreglo de proveedores para loggearse
                //ej correo,gacebook,teitter, google
                AuthUI.IdpConfig.EmailBuilder().build()
            )
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder().setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.setOnClickListener { seDeslogeo() }
    }

    fun seLogeo(res:IdpResponse
    ){
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogOut = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogin.visibility = View.INVISIBLE
        btnLogOut.visibility = View.VISIBLE
        if(res.isNewUser){
            registrarUsuarioPrimeraVez(res)
        }
    }

    fun registrarUsuarioPrimeraVez(usuario:IdpResponse){
            //Firestore
    }

    fun seDeslogeo(){
        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.visibility = View.VISIBLE
        btnLogin.visibility = View.VISIBLE
        FirebaseAuth.getInstance().signOut()
    }


}

