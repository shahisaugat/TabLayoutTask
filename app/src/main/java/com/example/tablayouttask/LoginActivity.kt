package com.example.tablayouttask

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tablayouttask.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var storedEmail: String
    private lateinit var storedPassword : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loginBinding.loginBtn.setOnClickListener {
            val emailAddress = loginBinding.emailField.text.toString()
            val password = loginBinding.pwdField.text.toString()

            if (emailAddress.isNotEmpty() && password.isNotEmpty()) {
                sharedPreferences = getSharedPreferences("user-details", MODE_PRIVATE)
                storedEmail = sharedPreferences.getString("EmailAddress", "") ?:""
                storedPassword = sharedPreferences.getString("Password", "")?:""

                if (emailAddress == storedEmail && password == storedPassword) {
                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Incorrect Password!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fill in the details first!", Toast.LENGTH_SHORT).show()
            }
        }

        loginBinding.signupNow.setOnClickListener {
            val intent = Intent(this@LoginActivity, NewAccountActivity::class.java)
            startActivity(intent)
        }
    }
}