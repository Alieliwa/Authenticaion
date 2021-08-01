package com.example.authenticaion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_signin.*

class LogInActivity : AppCompatActivity() {
    var mAuth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        mAuth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            login()
        }
    }
    private fun login(){
        var mail = edit_Email.text.toString()
        var password = editPassword.text.toString()
        if (mail.isNotEmpty() && password.isNotEmpty()){
            mAuth!!.signInWithEmailAndPassword(mail,password).addOnCompleteListener{
                if (it.isSuccessful){
                    verifyEmailAdd()
                }else {
                    Toast.makeText(this,"invalid E_mail or Password", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(this,"E_mail or Password Is Empty", Toast.LENGTH_LONG).show()
        }
    }
    private fun verifyEmailAdd(){
      var User  =   mAuth?.currentUser
        if (User!!.isEmailVerified){
            val intenttomain  = Intent(this,MainActivity::class.java)
            startActivity(intenttomain)
        }else{
            Toast.makeText(this,"please verify your account....",Toast.LENGTH_LONG).show()
        }
    }
    }
