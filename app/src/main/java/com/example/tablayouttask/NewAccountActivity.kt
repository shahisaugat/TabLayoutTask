package com.example.tablayouttask

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tablayouttask.databinding.ActivityNewAccountBinding

class NewAccountActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    companion object {
        var pref_name = "user-details"
    }

    private lateinit var newAccountBinding: ActivityNewAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        newAccountBinding = ActivityNewAccountBinding.inflate(layoutInflater)
        setContentView(newAccountBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        newAccountBinding.createAccBtn.setOnClickListener {
            val emailAddress = newAccountBinding.emailEntry.text.toString()
            val password = newAccountBinding.pwdEntry.text.toString()
            val cfmPassword = newAccountBinding.cfmPwdEntry.text.toString()

            if (password == cfmPassword) {
                val sharedPreferences = getSharedPreferences(pref_name, MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                editor.putString("EmailAddress", emailAddress)
                editor.putString("Password", password)
                editor.apply()

                Toast.makeText(this, "Account Successfully Created!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Password didn't match!", Toast.LENGTH_SHORT).show()
            }
        }

        newAccountBinding.loginBack.setOnClickListener {
            val intent = Intent(this@NewAccountActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}