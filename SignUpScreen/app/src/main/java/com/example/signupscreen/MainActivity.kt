package com.example.signupscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.signupscreen.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        val current = auth.currentUser

        if(current != null){
            val intent = Intent(this@MainActivity,MainScreenActivity::class.java)
            startActivity(intent)
        }

    }


    fun logIn(view: View){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if(email.equals("") || password.equals("")){
            Toast.makeText(this@MainActivity,"lütfen boşlukları doldurun",Toast.LENGTH_LONG).show()
        }
        else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this@MainActivity,MainScreenActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }


        }

    }

    fun signIn(view: View){
        val intent = Intent(this@MainActivity,SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}