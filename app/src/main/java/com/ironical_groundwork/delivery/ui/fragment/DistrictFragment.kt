package com.ironical_groundwork.delivery.ui.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.adapter.DistrictDelegate
import com.ironical_groundwork.delivery.adapter.DistrictListAdapter
import com.ironical_groundwork.delivery.adapter.RouteDelegate
import com.ironical_groundwork.delivery.adapter.RouteListAdapter
import com.ironical_groundwork.delivery.databinding.FragmentRouteBinding
import com.ironical_groundwork.delivery.util.showToast
import com.ironical_groundwork.delivery.viewModel.ApiViewModel
import com.ironical_groundwork.delivery.viewModel.DataViewModel

class DistrictFragment : Fragment() {

    private lateinit var dataViewModel: DataViewModel
    private lateinit var binding: FragmentRouteBinding
    private val districtListAdapter by lazy { DistrictListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        binding = FragmentRouteBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.title = arguments?.getString("routeName")

        setupRecyclerview()

        onRefresh()

        getDistrict()

        return binding.root
    }

    private fun getDistrict() {
       dataViewModel.readDistrict(arguments?.getInt("routeId")!!).observe(viewLifecycleOwner,
           { districtList ->
                districtList.let {
                    if (it.isNotEmpty()) {
                        districtListAdapter.setData(it)
                    }
                    else
                    {
                        findNavController().popBackStack()
                    }
                }
            })
    }

    private fun onRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({

                binding.swipeRefreshLayout.isRefreshing = false
                findNavController().popBackStack()

            }, 500)
        }
    }

    private fun setupRecyclerview() {
        binding.recyclerView.adapter = districtListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        districtListAdapter.attachDelegate(object: DistrictDelegate {
            override fun openDistrict(district_name: String?) {
                if (district_name=="")
                    navigateToDistrict(arguments?.getInt("routeId"), "Без района")
                else
                    navigateToDistrict(arguments?.getInt("routeId"), district_name)
            }
        })
    }

    fun navigateToDistrict(route_id: Int?, district_name: String?){
        val bundle = Bundle()

        bundle.putInt("routeId", route_id!!)
        bundle.putString("districtName", district_name)
        findNavController().navigate(
            R.id.action_nav_district_to_nav_order,
            bundle
        )
    }
}