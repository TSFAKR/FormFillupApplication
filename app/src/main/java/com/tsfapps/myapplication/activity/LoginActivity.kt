package com.tsfapps.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.tsfapps.myapplication.MainActivity
import com.tsfapps.myapplication.R
import com.tsfapps.myapplication.databinding.ActivityLoginBinding
import com.tsfapps.myapplication.db.preference.MySharedPreference
import com.tsfapps.myapplication.network.ModelClass
import com.tsfapps.myapplication.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mySharedPreference: MySharedPreference

    private var strEtUserName: String = ""
    private var strEtPassword: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySharedPreference = MySharedPreference(context = this@LoginActivity)


        if(mySharedPreference.getSessionKey() != null){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }else{
            login()
        }
    }

    private fun login(){
        binding.btnLogin.setOnClickListener {

            strEtUserName = binding.edtUserName.text.toString()
            strEtPassword = binding.edtPassword.text.toString()


            if (strEtUserName.isEmpty() || strEtPassword.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val requestData = ModelClass(strEtUserName, strEtPassword)
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val response = NetworkService.api.login(requestData)
                    if (response.success) {
                        mySharedPreference.setSessionKey(response.sessionKey, strEtUserName)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                        Log.d("TSF_APPS", "Success: ${response.sessionKey}")

                    }else
                    {
                        Log.d("TSF_APPS", "Success Fail: ${response.sessionKey}")

                    }
                } catch (e: Exception) {
                    Log.i("TSF_APPS", "Error ${e.message}")
                }
            }


        }

    }
}