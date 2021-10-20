package com.ironical_groundwork.delivery.ui.activity

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.ironical_groundwork.delivery.MainActivity
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.repository.ApiRepository
import com.ironical_groundwork.delivery.util.checkForInternet
import com.ironical_groundwork.delivery.util.replaceActivity
import com.ironical_groundwork.delivery.util.showToast
import com.ironical_groundwork.delivery.viewModel.ApiViewModel
import com.ironical_groundwork.delivery.viewModel.ApiViewModelFactory
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val repository = ApiRepository()
        val viewModelFactory = ApiViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ApiViewModel::class.java)

        button_resume.setOnClickListener {
            Handler().postDelayed({
                checkCode()
            }, 500)

        }
    }

    private fun checkCode() {
        if (checkForInternet(this)) {
            val code = edit_code.text.toString()

            if (code.length == 4) {

                val userId = OneSignal.getDeviceState()?.userId
                val phoneName = "${Build.MANUFACTURER} ${Build.MODEL}"

                viewModel.pushPostLogin(code,userId, phoneName)
                viewModel.loginPostResponse.observe(this, { response ->
                    if(response.isSuccessful) {
                        if (response.body()?.id != 0) {
                            val sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.apply {
                                putInt("id", response.body()!!.id)
                                putString("name", response.body()!!.name)
                                putString("image", response.body()!!.image)
                                putString("description", response.body()!!.description)
                                putInt("status", response.body()!!.status)
                            }.apply()

                            replaceActivity(MainActivity())
                        }
                        else
                        {
                            showToast("Неверный код авторизации!")
                        }
                    }
                    else {
                        showToast("${response.code()}: Ошибка сервера")
                    }
                })
            }
        }
        else {
            showToast("Отсутствует интернет соединение!")
        }
    }
}

