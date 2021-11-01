package com.ironical_groundwork.delivery.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.model.Route
import kotlinx.android.synthetic.main.item_list_m.view.*

interface RouteDelegate {
    fun openRoute(routeId: Int, routeNumber: String?)
}

class RouteListAdapter: RecyclerView.Adapter<RouteListAdapter.MyViewHolder>() {

    private var routeList = emptyList<Route>()
    private var delegate: RouteDelegate? = null

    fun attachDelegate(delegate: RouteDelegate) {
        this.delegate = delegate
    }

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

        holder.itemView.setOnClickListener {
            delegate?.openRoute(routeList[position].id, routeList[position].number)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newRouteList: List<Route>){
        routeList = newRouteList
        notifyDataSetChanged()
    }

}