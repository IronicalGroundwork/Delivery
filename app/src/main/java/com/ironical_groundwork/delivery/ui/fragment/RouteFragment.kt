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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironical_groundwork.delivery.MainActivity
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.adapter.RouteDelegate
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

        onRefresh()

        getRoute()

        return binding.root
    }

    private fun getRoute() {
        if (checkForInternet(requireContext())) {

            val sharedPreferences = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)

            when (sharedPreferences.getInt("status", 0)) {
                0, 1 -> apiViewModel.getAllRouteList()
                2, 3 -> apiViewModel.getRouteList(sharedPreferences.getInt("id", 0))
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

    private fun onRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({

                binding.swipeRefreshLayout.isRefreshing = false

                getRoute()

            }, 500)
        }
    }

    fun navigateToRoute(routeId: Int, routeNumber: String?){
        val bundle = Bundle()
        bundle.putInt("routeId", routeId)
        bundle.putString("routeName", routeNumber)
        findNavController().navigate(
            R.id.action_nav_route_to_nav_district,
            bundle
        )
    }

    private fun setupRecyclerview() {
        binding.recyclerView.adapter = routeListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        routeListAdapter.attachDelegate(object: RouteDelegate {
            override fun openRoute(routeId: Int, routeNumber: String?) {
                navigateToRoute(routeId, routeNumber)
            }
        })

        dataViewModel.readAllRoute.observe(viewLifecycleOwner, { routeList ->
            routeListAdapter.setData(routeList)
        })

    }

}