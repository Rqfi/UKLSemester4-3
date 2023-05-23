package com.example.uklaplikasiku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnBackToHome: ImageButton =
            findViewById(R.id.back_button)
        btnBackToHome.setOnClickListener(this)

        val database: TextView =
            findViewById(R.id.database)
        database.setOnClickListener(this)

        text = findViewById(R.id.emailText)

        val email = intent.getStringExtra("String")
        if (email != null){
            text.text = email
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.back_button -> {
                val moveToHome = Intent(this@ProfileActivity, HomeActivity::class.java)
                startActivity(moveToHome)
            }
            R.id.database -> {
                val database = Intent(this@ProfileActivity, FrenActivity::class.java)
                startActivity(database)
            }
        }
    }
}