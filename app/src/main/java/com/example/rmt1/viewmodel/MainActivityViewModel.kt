package com.example.rmt1.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rmt1.helpers.MystringUtils
import com.example.rmt1.repository.CharacterListDataSourceFactory
import com.example.rmt1.repository.EpisodeListDataSourceFactory
import com.example.rmt1.repository.LocationListDataSourceFactory
import com.example.rmt1.models.CharactersData
import com.example.rmt1.models.EpisodeData
import com.example.rmt1.models.LocationsData
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class MainActivityViewModel:ViewModel() {
    private var characterList: LiveData<PagedList<CharactersData>>? = null
    private var characterFilteredList: LiveData<PagedList<CharactersData>>? = null
    private var episodeList: LiveData<PagedList<EpisodeData>>? = null
    private var locationList: LiveData<PagedList<LocationsData>>? = null



    val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(34)
        .build()


    val executor: Executor = Executors.newFixedThreadPool(5)

    init{
    }

     fun beginPaging(fragmentName: String){
         if (fragmentName == MystringUtils.charactersFragment){
                 val factory = CharacterListDataSourceFactory("")
                 characterList = LivePagedListBuilder<Int, CharactersData>(factory, config)
                         .setFetchExecutor(executor)
                         .build()
         }else if (fragmentName == MystringUtils.episodesFragment){
             val factory = EpisodeListDataSourceFactory()
             episodeList = LivePagedListBuilder<Int, EpisodeData>(factory, config)
                 .setFetchExecutor(executor)
                 .build()
         }else if(fragmentName == MystringUtils.locationFragment){
             val factory = LocationListDataSourceFactory()
             locationList = LivePagedListBuilder<Int, LocationsData>(factory, config)
                 .setFetchExecutor(executor)
                 .build()
         }

    }
    fun beginFilterPaging(query: String){
            if(query!=null){
                val factory = CharacterListDataSourceFactory(query)
                characterFilteredList = LivePagedListBuilder<Int, CharactersData>(factory, config)
                        .setFetchExecutor(executor)
                        .build()
            }
    }

    fun getCharacterListData(): LiveData<PagedList<CharactersData>>?{
        return characterList
    }

    fun getCharacterFilteredListData(): LiveData<PagedList<CharactersData>>?{
        return characterFilteredList
    }
    fun getEpisodesListData(): LiveData<PagedList<EpisodeData>>?{
        return episodeList
    }
    fun getLocationsListData(): LiveData<PagedList<LocationsData>>?{
        return locationList
    }
    

}