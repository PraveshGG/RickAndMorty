package com.example.rmt1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rmt1.R
import com.example.rmt1.models.EpisodeData
import kotlinx.android.synthetic.main.episode_item_row.view.*

class EpisodeAdapter: PagedListAdapter<EpisodeData, EpisodeAdapter.MyViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<EpisodeData>() {

            override fun areItemsTheSame(oldItem: EpisodeData, newItem: EpisodeData): Boolean{
                return oldItem.name == newItem.name
            }
            override fun areContentsTheSame(oldItem: EpisodeData, newItem: EpisodeData): Boolean{
                return oldItem.name == newItem.name
            }

        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val textViewName = view.tvName
        val textViewAirDate = view.tvAirDate
        val textviewSeason = view.tvSeason
        val textViewEpisode = view.tvEpisode

        fun bind(data: EpisodeData){
            textViewName.text = data.name
            textViewAirDate.text = "Aired on: ${data.air_date}"
            textViewEpisode.text = "Episode:${data.episode?.takeLast(2)} "
            textviewSeason.text = "Season: ${data.episode?.take(3)?.takeLast(1)}"
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.episode_item_row,parent,false)
        return MyViewHolder(inflater)
    }



}