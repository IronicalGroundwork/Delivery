package com.ironical_groundwork.delivery.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.databinding.FragmentRouteBinding
import com.ironical_groundwork.delivery.databinding.FragmentSettingBinding
import android.content.SharedPreferences
import com.ironical_groundwork.delivery.MainActivity
import com.ironical_groundwork.delivery.ui.activity.LoginActivity
import com.ironical_groundwork.delivery.util.replaceActivity


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.buttonExit.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Да") {_,_ ->
                val sharedPreferences = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()

                replaceActivity(LoginActivity())

            }
            builder.setNegativeButton("Нет") {_,_ -> }
            builder.setTitle("Выход")
            builder.setMessage("Вы уверены, что хотите выйти?")
            builder.create().show()
        }

        return binding.root

    }
}