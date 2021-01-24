package com.example.rmt1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rmt1.R
import com.example.rmt1.adapters.EpisodeAdapter
import com.example.rmt1.helpers.MystringUtils
import com.example.rmt1.models.EpisodeData
import com.example.rmt1.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_episodes.*
import kotlinx.android.synthetic.main.fragment_episodes.loading
import kotlinx.coroutines.*


class EpisodesFragment : Fragment() {

    private lateinit var episodeAdapter: EpisodeAdapter
    private lateinit var viewModel: MainActivityViewModel
    private var job: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        initViewModelForEpisodeList()
    }
    private fun initRecyclerView() {
        episodesRecyclerView.apply{
            layoutManager =  LinearLayoutManager(context)
            episodeAdapter = EpisodeAdapter()
            adapter = episodeAdapter
        }
    }
    private fun initViewModelForEpisodeList(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.beginPaging(MystringUtils.episodesFragment)

        viewModel.getEpisodesListData()?.observe(
            viewLifecycleOwner,
            Observer<PagedList<EpisodeData>> {
                if (it != null) {
                episodeAdapter.submitList(it)
                }
            })

        if (loading != null) {
            job = GlobalScope.launch(Dispatchers.Main) {
                delay(500)
                if (loading.visibility != View.INVISIBLE)
                    loading.visibility = View.INVISIBLE
            }
        }

    }
    override fun onPause() {
        job?.cancel()
//        job = null
        super.onPause()
    }

}