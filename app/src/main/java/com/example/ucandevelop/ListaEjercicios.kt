package com.example.ucandevelop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_lista_ejercicios.*

class ListaEjercicios : AppCompatActivity() {

    private lateinit var logout: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ejercicios)

        logout = findViewById(R.id.btn_logout)

        logout.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                FirebaseAuth.getInstance().signOut()
                val i = Intent(this@ListaEjercicios, Login::class.java)
                startActivity(i)
            }
        })
    }
}
