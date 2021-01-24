package com.example.rmt1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rmt1.R
import com.example.rmt1.models.LocationsData
import kotlinx.android.synthetic.main.location_list_row.view.*

class LocationAdapter: PagedListAdapter<LocationsData, LocationAdapter.MyViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<LocationsData>() {

            override fun areItemsTheSame(oldItem: LocationsData, newItem: LocationsData): Boolean{
                return oldItem.name == newItem.name
            }


            override fun areContentsTheSame(oldItem: LocationsData, newItem: LocationsData): Boolean{
                return oldItem.name == newItem.name
            }

        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewName = view.tvName
        val textViewType = view.tvType
        val textViewDimensions = view.tvDimension

        fun bind(data: LocationsData){
            textViewName.text = data.name
            textViewType.text = "Place Type: ${data.type}"
            textViewDimensions.text = "Dimension Name: ${data.dimension}"
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.location_list_row,parent,false)
        return MyViewHolder(inflater)
    }


}