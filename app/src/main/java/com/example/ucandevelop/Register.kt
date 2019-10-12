package com.example.ucandevelop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class Register : AppCompatActivity() {

    private lateinit var emailRegister: EditText
    private lateinit var passRegister: EditText
    private lateinit var confPssRegister: EditText
    private lateinit var btn_register: Button
    private lateinit var singInRegister: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference()

        emailRegister = findViewById(R.id.et_register_email)
        passRegister = findViewById(R.id.et_register_pass)
        confPssRegister = findViewById(R.id.et_register_confpass)
        btn_register = findViewById(R.id.btn_register_register)
        singInRegister = findViewById(R.id.tv_register_signin)

        btn_register.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val email: String = emailRegister.text.toString()
                val pass: String = passRegister.text.toString()
                val confPass: String = confPssRegister.text.toString()

                if (email.isEmpty() || pass.isEmpty() || confPass.isEmpty()){
                    Toast.makeText(this@Register,"No deje ningun campo vacio",Toast.LENGTH_SHORT).show()
                }
                else{
                    if (pass != confPass){
                        Toast.makeText(this@Register,"Las contraseñas tienen que ser iguales",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if (pass.length >= 6){
                            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this@Register,object: OnCompleteListener<AuthResult>{
                                override fun onComplete(task: Task<AuthResult>) {
                                    if (!task.isSuccessful){
                                        Toast.makeText(this@Register,"Error en la red: "+task.result+" CORREGIR",Toast.LENGTH_SHORT).show()
                                    }
                                    else{
                                        val map = mutableMapOf<String, Any?>()
                                        map.put("email",email)
                                        map.put("pass",pass)
                                        val id = auth.currentUser!!.uid

                                        database.child("Usuarios").child(id).setValue(map).addOnCompleteListener(this@Register, object: OnCompleteListener<Void>{
                                            override fun onComplete(task2: Task<Void>) {
                                                if (task2.isSuccessful){
                                                    startActivity(Intent(this@Register,ListaEjercicios::class.java))
                                                    finish()
                                                }
                                                else{
                                                    Toast.makeText(this@Register,"No se registraron los datos en la Base de Datos",Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        })
                                        startActivity(Intent(this@Register,ListaEjercicios::class.java))
                                    }
                                }
                            })
                        }
                        else{
                            Toast.makeText(this@Register,"La contraseña tiene que ser de 6 digitos",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })

        singInRegister.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val i = Intent(this@Register,Login::class.java)
                startActivity(i)
            }
        })
    }
}
