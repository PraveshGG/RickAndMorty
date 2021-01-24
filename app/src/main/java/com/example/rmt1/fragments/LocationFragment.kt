package com.example.rmt1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rmt1.R
import com.example.rmt1.adapters.LocationAdapter
import com.example.rmt1.helpers.MystringUtils
import com.example.rmt1.models.LocationsData
import com.example.rmt1.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_characters.*
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_location.loading
import kotlinx.coroutines.*


class LocationFragment : Fragment() {

    private lateinit var locationAdapter: LocationAdapter
    private lateinit var viewModel: MainActivityViewModel
    private var job: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        initViewModelForLocationList()
    }

    private fun initRecyclerView() {
        locationsRecyclerView.apply{
            layoutManager =  LinearLayoutManager(context)
            locationAdapter = LocationAdapter()
            adapter = locationAdapter
        }
    }
    private fun initViewModelForLocationList(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.beginPaging(MystringUtils.locationFragment)

        viewModel.getLocationsListData()?.observe(viewLifecycleOwner, Observer<PagedList<LocationsData>>{
            if(it!=null){
                locationAdapter.submitList(it)
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