package com.example.rmt1.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.rmt1.models.EpisodeData
import com.example.rmt1.models.CharactersData
import com.example.rmt1.models.LocationsData


class CharacterListDataSourceFactory(var name: String): DataSource.Factory<Int, CharactersData>() {
    private var mutableLiveData: MutableLiveData<CharacterListDataSource>? = null

    init {
         mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, CharactersData> {

        val listDataSource = CharacterListDataSource(name)
        mutableLiveData?.postValue(listDataSource)
        return listDataSource

    }

}

class EpisodeListDataSourceFactory: DataSource.Factory<Int, EpisodeData>() {

    private var mutableLiveData: MutableLiveData<EpisodeListDataSource>? = null

    init {
        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, EpisodeData> {

        val listDataSource = EpisodeListDataSource()
        mutableLiveData?.postValue(listDataSource)
        return listDataSource

    }

}

class LocationListDataSourceFactory: DataSource.Factory<Int, LocationsData>() {

    private var mutableLiveData: MutableLiveData<LocationListDataSource>? = null

    init {
        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, LocationsData> {

        val listDataSource = LocationListDataSource()
        mutableLiveData?.postValue(listDataSource)
        return listDataSource

    }



}


