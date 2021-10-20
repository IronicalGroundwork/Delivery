package com.ironical_groundwork.delivery.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.model.Login
import com.ironical_groundwork.delivery.repository.Repository
import com.ironical_groundwork.delivery.util.showToast
import com.ironical_groundwork.delivery.viewModel.DeliveryViewModel
import com.ironical_groundwork.delivery.viewModel.DeliveryViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: DeliveryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val repository = Repository()
        val viewModelFactory = DeliveryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DeliveryViewModel::class.java)

        button_resume.setOnClickListener {
            val code = edit_code.text.toString()

            if (code.length == 4) {
                viewModel.pushPostLogin(code,"", "")
                viewModel.loginPostResponse.observe(this, { response ->
                    if(response.isSuccessful) {
                        Log.d("Response", response.body().toString())
                    }
                    else {
                        showToast(response.code().toString())
                    }
                })
            }
        }
    }
}