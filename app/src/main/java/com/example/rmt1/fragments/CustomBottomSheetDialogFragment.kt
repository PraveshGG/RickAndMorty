 package com.example.rmt1.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rmt1.R
import com.example.rmt1.adapters.HorizontalEpisodeAdapter
import com.example.rmt1.databinding.FragmentCustomBottomSheetDialogBinding
import com.example.rmt1.models.CharactersData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class CustomBottomSheetDialogFragment(characterData: CharactersData) : BottomSheetDialogFragment() {

    private var characterData: CharactersData
    private var episodes: MutableList<String>

    init {
        this.characterData = characterData
        episodes = characterData.episode!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentCustomBottomSheetDialogBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_custom_bottom_sheet_dialog,container,false)
        val view: View = binding.root
        val homerecyclerview = view.findViewById<RecyclerView>(R.id.homesRecyclerView)

        binding.characterInfo = characterData

        homerecyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
        homerecyclerview.adapter = HorizontalEpisodeAdapter(getList(episodes))
        return view
    }

    companion object {
        const val TAG = "CustomBottomSheetDialogFragment"
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(view: ImageView,url: String?){
            Glide.with(view)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_question)
            .into(view)

        }
    }

    private fun getList(list: MutableList<String>): MutableList<String>{
        val testlist = mutableListOf<String>()
        for(items in list){
            val check = items.takeLast(2)
            if(check.contains("/")){
                val addv = items.takeLast(1)
                testlist.add("Ep0${addv}")}
            else{
                val addv = items.takeLast(2)
                testlist.add("Ep${addv}")
            }

        }
        return testlist
    }
}