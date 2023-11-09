package com.example.moviesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviesapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LoginHandle()
    }

    private fun LoginHandle() {
        binding.btnLogin.setOnClickListener {
            if(binding.edtUsername.text.isNotEmpty()&&binding.edtPassword.text.isNotEmpty()){
                if(binding.edtUsername.text.toString().equals("test")){
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else{
                    Toast.makeText(this, "Username or password is not correct!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Username and password is not empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}