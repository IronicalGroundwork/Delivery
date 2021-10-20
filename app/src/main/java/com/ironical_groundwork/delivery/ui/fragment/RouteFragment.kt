package com.ironical_groundwork.delivery.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironical_groundwork.delivery.MainActivity
import com.ironical_groundwork.delivery.adapter.RouteListAdapter
import com.ironical_groundwork.delivery.databinding.FragmentRouteBinding
import com.ironical_groundwork.delivery.model.*
import com.ironical_groundwork.delivery.repository.ApiRepository
import com.ironical_groundwork.delivery.ui.activity.LoginActivity
import com.ironical_groundwork.delivery.util.checkForInternet
import com.ironical_groundwork.delivery.util.replaceActivity
import com.ironical_groundwork.delivery.util.showToast
import com.ironical_groundwork.delivery.viewModel.ApiViewModel
import com.ironical_groundwork.delivery.viewModel.ApiViewModelFactory
import com.ironical_groundwork.delivery.viewModel.DataViewModel

class RouteFragment : Fragment() {

    private lateinit var apiViewModel: ApiViewModel
    private lateinit var dataViewModel: DataViewModel
    private lateinit var binding: FragmentRouteBinding
    private val routeListAdapter by lazy { RouteListAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val repository = ApiRepository()
        val viewModelFactory = ApiViewModelFactory(repository)
        apiViewModel = ViewModelProvider(this, viewModelFactory).get(ApiViewModel::class.java)

        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        binding = FragmentRouteBinding.inflate(inflater, container, false)

        setupRecyclerview()

        dataViewModel.readAllRoute.observe(viewLifecycleOwner, Observer { routeList ->
            routeListAdapter.setData(routeList)
        })

        onRefresh()

        getRoute()

        return binding.root
    }

    private fun onRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({

                binding.swipeRefreshLayout.isRefreshing = false

                getRoute()

            }, 500)
        }
    }

    private fun getRoute() {
        if (checkForInternet(requireContext())) {

            val sharedPreferences = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)

            when (sharedPreferences.getInt("status", 0)) {
                0 -> apiViewModel.getAllRouteList()
                1 -> apiViewModel.getAllRouteList()
                2 -> apiViewModel.getRouteList(sharedPreferences.getInt("id", 0))
                3 -> apiViewModel.getRouteList(sharedPreferences.getInt("id", 0))
            }


            apiViewModel.routeResponse.observe(requireActivity(), { response ->
                if(response.isSuccessful) {
                    response.body()?.let {
                        dataViewModel.updateAllData(it)
                    }
                }
                else {
                    showToast("${response.code()}: Ошибка сервера")
                }
            })

        }
        else {
            showToast("Отсутствует интернет соединение!")
        }
    }

    private fun setupRecyclerview() {
        binding.recyclerView.adapter = routeListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}