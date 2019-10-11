package com.example.ucandevelop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class Register : AppCompatActivity() {

    private lateinit var emailRegister: EditText
    private lateinit var passRegister: EditText
    private lateinit var confPssRegister: EditText
    private lateinit var btn_register: Button
    private lateinit var singInRegister: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
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
                        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this@Register,object: OnCompleteListener<AuthResult>{
                            override fun onComplete(task: Task<AuthResult>) {
                                if (!task.isSuccessful){
                                    Toast.makeText(this@Register,"Error en la red",Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    startActivity(Intent(this@Register,ListaEjercicios::class.java))
                                }
                            }
                        })
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
