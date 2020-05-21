package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = this?.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("email", "dina@mail.com")
            putString("password", "Qwer123")
            putInt("photo",R.drawable.avatar)
            putString("name", "Ibragimova Dina")
            commit()
        }

        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val password = findViewById<EditText>(R.id.password)
        val email = findViewById<EditText>(R.id.email)

        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val matchResult = Regex("^[A-Za-z0-9._%+-]+@([A-Za-z0-9]([A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+[A-Za-z]{2,6}\$").find(s)
                if (matchResult == null) {
                    email.error = "E-mail is not correct!"
                    loginBtn.isEnabled = false;
                }
                else {
                    email.error = null
                    if(!email.text.isEmpty() && !password.text.isEmpty() && email.error == null && password.error == null){
                        loginBtn.isEnabled = true
                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val matchResult = Regex("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}\$").find(s)
                if (matchResult == null) {
                    password.error = "Password is not correct!"
                    loginBtn.isEnabled = false
                }
                else {
                    password.error = null
                    if(!email.text.isEmpty() && !password.text.isEmpty() && email.error == null && password.error == null){
                        loginBtn.isEnabled = true
                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
        })

        loginBtn.setOnClickListener{
            val login = sharedPref.getString("email", "error")
            val pass = sharedPref.getString("password", "error")
            if(login.equals(email.text.toString()) && pass.equals(password.text.toString())){
                val intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
            }
            else{
                val toast = Toast.makeText(this, "Account is not found!", Toast.LENGTH_LONG)
                toast.show()
            }
        }

    }
}



