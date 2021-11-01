package com.ironical_groundwork.delivery.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironical_groundwork.delivery.R
import com.ironical_groundwork.delivery.model.District
import com.ironical_groundwork.delivery.model.Route
import kotlinx.android.synthetic.main.item_list_m.view.*

interface DistrictDelegate {
    fun openDistrict(district_name: String?)
}

class DistrictListAdapter: RecyclerView.Adapter<DistrictListAdapter.MyViewHolder>() {

    private var districtList = emptyList<District>()
    private var delegate: DistrictDelegate? = null

    fun attachDelegate(delegate: DistrictDelegate) {
        this.delegate = delegate
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_m, parent, false))
    }

    override fun getItemCount(): Int {
        return districtList.size
    }

    override fun onBindViewHolder(holder: DistrictListAdapter.MyViewHolder, position: Int) {
        if (districtList[position].name!="")
            holder.itemView.title_1.text = districtList[position].name
        else
            holder.itemView.title_1.text = "Без района"
        holder.itemView.count.text = districtList[position].order_count.toString()

        holder.itemView.setOnClickListener {
            delegate?.openDistrict(districtList[position].name)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDistrictList: List<District>){
        districtList = newDistrictList
        notifyDataSetChanged()
    }

}