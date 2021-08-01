package com.example.authenticaion

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*


class SignInActivity : AppCompatActivity() {

     var mAuth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        mAuth =  FirebaseAuth.getInstance()
        btnRejestter.setOnClickListener {
            Rejestter()
        }
    }
    private fun Rejestter(){
        var e_mail = edmail.text.toString()
        var pass = edpass.text.toString()
        if (e_mail.isNotEmpty()&&pass.isNotEmpty()){
            progressBar.visibility = View.VISIBLE
            progressBar.setBackgroundColor(Color.BLACK)
            mAuth!!.createUserWithEmailAndPassword(e_mail,pass)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        sentverify()
                        progressBar.visibility = View.GONE
                    }else {
                        Toast.makeText(applicationContext,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
        }else{
            Toast.makeText(applicationContext,"Empty",Toast.LENGTH_LONG).show()
        }
    }
    private fun sentverify(){
     var user =    mAuth!!.currentUser
        user!!.sendEmailVerification().addOnCompleteListener {

            if (it.isSuccessful){
                val intenttologin = Intent(this,LogInActivity::class.java)
                startActivity(intenttologin)
            }else{
                Toast.makeText(applicationContext,it.exception.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }
}