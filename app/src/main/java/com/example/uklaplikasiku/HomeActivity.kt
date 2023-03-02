package com.example.uklaplikasiku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnLib: TextView =
            findViewById(R.id.btnSeeMore)
        btnLib.setOnClickListener(this)

        val btnProfil : TextView =
            findViewById(R.id.btnSeeProfile)
        btnProfil.setOnClickListener(this)

        text = findViewById(R.id.emailText)

        val email = intent.getStringExtra("String")
        if (email != null){
            text.text = email
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSeeMore -> {
                val moveToLib = Intent(this@HomeActivity, LibraryActivity::class.java)
                startActivity(moveToLib)
            }
            R.id.btnSeeProfile -> {
                val moveToProfil = Intent(this@HomeActivity, ProfileActivity::class.java)
                startActivity(moveToProfil)
            }
        }
    }
}
