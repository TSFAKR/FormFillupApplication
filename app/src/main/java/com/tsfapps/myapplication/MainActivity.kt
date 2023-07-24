package com.tsfapps.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.tsfapps.myapplication.databinding.ActivityMainBinding
import com.tsfapps.myapplication.db.preference.MySharedPreference

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mySharedPreference: MySharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mySharedPreference = MySharedPreference(this@MainActivity)
        mySharedPreference.getSessionKey()?.let { Log.d("TSF_APPS", "SESSION_KEY $it") }
        mySharedPreference.getUserId()?.let { Log.d("TSF_APPS", "USER_ID $it") }
    }
}