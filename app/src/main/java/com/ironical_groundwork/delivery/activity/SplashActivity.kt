package com.ironical_groundwork.delivery.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ironical_groundwork.delivery.MainActivity
import com.ironical_groundwork.delivery.util.replaceActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)

        if (sharedPreferences.getInt("id", 0) != 0) {
            replaceActivity(MainActivity())
        }
        else {
            replaceActivity(LoginActivity())
        }
    }

}