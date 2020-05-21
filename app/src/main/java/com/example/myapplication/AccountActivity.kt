package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val sharedPref = this?.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val email1 = sharedPref.getString("email", "error")
        val name1 = sharedPref.getString("name", "error")
        val photo1 = sharedPref.getInt("photo", -1)

        val photo = findViewById<ImageView>(R.id.photo)
        photo.setImageResource(photo1)
        val email = findViewById<TextView>(R.id.email)
        email.text = email1
        val name = findViewById<TextView>(R.id.name)
        name.text = name1
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)

        logoutBtn.setOnClickListener{
            finish()
        }
    }
}
