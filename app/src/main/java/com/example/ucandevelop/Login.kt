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
import com.google.firebase.auth.FirebaseUser


class Login : AppCompatActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passLogin: EditText
    private lateinit var btn_Login: Button
    private lateinit var singUpLogin: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        emailLogin = findViewById(R.id.et_login_email)
        passLogin = findViewById(R.id.et_login_pass)
        btn_Login = findViewById(R.id.btn_login_register)
        singUpLogin = findViewById(R.id.tv_login_signUp)

        authListener = object: FirebaseAuth.AuthStateListener{

            override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                val firebaseUser: FirebaseUser? = auth.currentUser
                if (firebaseUser != null){
                    Toast.makeText(this@Login,"Ya estas conectado", Toast.LENGTH_SHORT).show()
                    val i = Intent(this@Login,Home::class.java)
                    startActivity(i)
                }
                else{
                    Toast.makeText(this@Login,"Inicia Sesion", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btn_Login.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val email: String = emailLogin.text.toString()
                val pass: String = passLogin.text.toString()

                if (email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(this@Login,"No deje ningun campo vacio",Toast.LENGTH_SHORT).show()
                }
                else{
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this@Login, object: OnCompleteListener<AuthResult>{
                        override fun onComplete(task: Task<AuthResult>) {
                            if (!task.isSuccessful){
                                Toast.makeText(this@Login,"Error al ingresar, Intente otra vez",Toast.LENGTH_SHORT).show()
                            }
                            else{
                                val i =  Intent(this@Login,Home::class.java)
                                startActivity(i)
                            }
                        }
                    })
                }
            }
        })

        singUpLogin.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val i = Intent(this@Login, Register::class.java)
                startActivity(i)
            }
        })
    }

    protected override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener)
    }
}
