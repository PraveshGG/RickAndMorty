package com.example.rmt1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rmt1.R
import kotlinx.android.synthetic.main.customdialogfragment_episode_name_row.view.*

class HorizontalEpisodeAdapter(private var episodes: MutableList<String>): RecyclerView.Adapter<HorizontalEpisodeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.customdialogfragment_episode_name_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.episodesName!!.text = episodes[position]
    }

    override fun getItemCount() = episodes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val episodesName: TextView? = itemView.tvEpisode
    }

}
