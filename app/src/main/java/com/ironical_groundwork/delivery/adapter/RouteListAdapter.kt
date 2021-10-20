package com.ironical_groundwork.delivery.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.model.Route
import kotlinx.android.synthetic.main.item_list_m.view.*

class RouteListAdapter: RecyclerView.Adapter<RouteListAdapter.MyViewHolder>() {

    private var routeList = emptyList<Route>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_m, parent, false))
    }

    override fun getItemCount(): Int {
        return routeList.size
    }

    override fun onBindViewHolder(holder: RouteListAdapter.MyViewHolder, position: Int) {
        holder.itemView.title_1.text = routeList[position].number
        holder.itemView.title_2.text = routeList[position].date
        holder.itemView.count.text = routeList[position].order_count.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newRouteList: List<Route>){
        routeList = newRouteList
        notifyDataSetChanged()
    }

}