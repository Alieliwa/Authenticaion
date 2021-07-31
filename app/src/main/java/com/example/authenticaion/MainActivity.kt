package com.example.authenticaion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

var mAuth2:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth2 = FirebaseAuth.getInstance()

        SignUp.setOnClickListener {
            var intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        butLogin.setOnClickListener {
            val intentlogin = Intent(this, LogInActivity::class.java)
            startActivity(intentlogin)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu , menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if (id == R.id.itemlogout){
            FirebaseAuth.getInstance().signOut()
            val intetntoSignIn = Intent(this,SignInActivity::class.java)
            startActivity(intetntoSignIn)
        }
        return true
    }
}