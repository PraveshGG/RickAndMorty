package com.example.rmt1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rmt1.models.CharactersData
import com.example.rmt1.R
import kotlinx.android.synthetic.main.character_item_row.view.*
import java.util.*
import kotlin.collections.ArrayList

class CharacterAdapter(private val listener:OnItemClickListener): PagedListAdapter<CharactersData, CharacterAdapter.MyViewHolder>(COMPARATOR) {

    lateinit var  context: Context

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CharactersData>() {

            override fun areItemsTheSame(oldItem: CharactersData, newItem: CharactersData): Boolean{
                return oldItem.name == newItem.name
            }


            override fun areContentsTheSame(oldItem: CharactersData, newItem: CharactersData): Boolean{
                return oldItem.name == newItem.name
            }

        }
    }

    init {

    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{

        val textViewName = view.tvNamef
        val textViewSpecies = view.tvSpeciesf
        val imageViewPicture= view.ivImage
        val imageViewGender = view.ivGenderf
        val imageViewStatus = view.ivStatusf

        fun bind(data: CharactersData){
            textViewName.text = data.name
            textViewSpecies.text = data.species

            val imageUrl = data.image

            if (data.gender.equals("Male"))
                imageViewGender.setImageResource(R.drawable.ic_male_gender)
            else if(data.gender.equals("Female"))
                imageViewGender.setImageResource(R.drawable.ic_female_gender)
            else
                imageViewGender.setImageResource(R.drawable.ic_question)

            if (data.status.equals("Alive"))
                imageViewStatus.setImageResource(R.drawable.ic_status_alive)
            else if(data.status.equals("Dead"))
                imageViewStatus.setImageResource(R.drawable.ic_status_dead)
            else
                imageViewStatus.setImageResource(R.drawable.ic_question)

            Glide.with(imageViewPicture)
                .load(imageUrl)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_question)
                .into(imageViewPicture)
        }

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val postion: Int = adapterPosition
            listener.onItemClick(postion)
        }

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.character_item_row,parent,false)
        return MyViewHolder(inflater)
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)

    }



}