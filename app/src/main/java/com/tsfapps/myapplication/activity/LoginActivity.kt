package com.tsfapps.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.tsfapps.myapplication.MainActivity
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var etUserName: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        etUserName = binding.edtUserName
        etPassword = binding.edtPassword


        binding.btnLogin.setOnClickListener {

            if (etUserName.text.toString() != "abc@123"){
                etUserName.error = "Enter the correct user name"
            }else if (etPassword.text.toString() != "123456"){
                etPassword.error = "Enter the correct Password"
            }else
            {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}