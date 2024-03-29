package com.example.uklaplikasiku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtUsername = findViewById(R.id.username)
        edtPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(this)

        val btnReg: TextView =
            findViewById(R.id.btnReg)
        btnReg.setOnClickListener(this)

        val btnForgor: TextView =
            findViewById(R.id.forgor)
        btnForgor.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var emptyEmail: Boolean = false
        var emptyPassword: Boolean = false

        if(edtUsername.text.toString().trim().length == 0){
            edtUsername.error = "Email tidak boleh kosong"
            emptyEmail = true
        }

        if(edtPassword.text.toString().trim().length == 0){
            edtPassword.error = "Password tidak boleh kosong"
            emptyPassword = true
        }

        if(!emptyEmail && !emptyPassword){
            val dataReceived = Intent(this@LoginActivity, HomeActivity::class.java)
            val email = edtUsername.text.toString()
            dataReceived.putExtra("String", email)
            startActivity(dataReceived)
        }
        when (v.id) {
            R.id.btnReg -> {
                val moveToReg = Intent(this@LoginActivity, FrenActivity::class.java)
                startActivity(moveToReg)
            }
            R.id.forgor -> {
                val moveToF = Intent(this@LoginActivity, ForgotPasswordActivity:: class.java)
                startActivity(moveToF)
            }
        }
    }
}