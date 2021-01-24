package com.example.rmt1.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.rmt1.models.*
import com.example.rmt1.retrofit.RetroInstance
import com.example.rmt1.retrofit.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListDataSource(var name: String): PageKeyedDataSource<Int, CharactersData>() {

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharactersData>) {
        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        var call: Call<RMCharacterList>
        Log.d("cjckk", "loadAfter: "+name)
        if(name!=null){
             call = retroInstance.getCharactersFiltered(params.key,name)
        }else{
             call = retroInstance.getCharacters(params.key)

        }
        call.enqueue(object :  Callback<RMCharacterList> {
            override fun onResponse(
                    call: Call<RMCharacterList>,
                    response: Response<RMCharacterList>) {
                if(response.isSuccessful){
                    callback.onResult(response?.body()?.results!!,params.key+1)
                }
            }
            override fun onFailure(call: Call<RMCharacterList>, t: Throwable) {
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharactersData>) {
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharactersData>
    ) {
        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        var call: Call<RMCharacterList>

        if(name!=null){
            call = retroInstance.getCharactersFiltered(1,name)
        }else{
            call = retroInstance.getCharacters(1)

        }
        call.enqueue(object :  Callback<RMCharacterList> {
            override fun onResponse(
                call: Call<RMCharacterList>,
                response: Response<RMCharacterList>) {

                if(response.isSuccessful){
                    callback.onResult(response?.body()?.results!!,null,2)
                }
            }
            override fun onFailure(call: Call<RMCharacterList>, t: Throwable) {
            }


        })
    }

}

class EpisodeListDataSource: PageKeyedDataSource<Int, EpisodeData>() {

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, EpisodeData>) {
        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getEpisode(params.key)
        call.enqueue(object :  Callback<RMEpisodeList> {
            override fun onResponse(
                    call: Call<RMEpisodeList>,
                    response: Response<RMEpisodeList>) {
                if(response.isSuccessful){
                    callback.onResult(response?.body()?.results!!,params.key+1)
                }
            }
            override fun onFailure(call: Call<RMEpisodeList>, t: Throwable) {
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, EpisodeData>) {
    }

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, EpisodeData>
    ) {
        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getEpisode(1)
        call.enqueue(object :  Callback<RMEpisodeList> {
            override fun onResponse(
                    call: Call<RMEpisodeList>,
                    response: Response<RMEpisodeList>) {

                if(response.isSuccessful){
                    callback.onResult(response?.body()?.results!!,null,2)
                }
            }
            override fun onFailure(call: Call<RMEpisodeList>, t: Throwable) {
            }


        })
    }

}

class LocationListDataSource: PageKeyedDataSource<Int, LocationsData>() {

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, LocationsData>) {
        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getLocation(params.key)
        call.enqueue(object :  Callback<RMLocationList> {
            override fun onResponse(
                    call: Call<RMLocationList>,
                    response: Response<RMLocationList>) {
                if(response.isSuccessful){
                    callback.onResult(response?.body()?.results!!,params.key+1)
                }
            }
            override fun onFailure(call: Call<RMLocationList>, t: Throwable) {
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, LocationsData>) {
    }

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, LocationsData>
    ) {
        val retroInstance  = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getLocation(1)
        call.enqueue(object :  Callback<RMLocationList> {
            override fun onResponse(
                    call: Call<RMLocationList>,
                    response: Response<RMLocationList>) {

                if(response.isSuccessful){
                    callback.onResult(response?.body()?.results!!,null,2)
                }
            }
            override fun onFailure(call: Call<RMLocationList>, t: Throwable) {
            }


        })
    }

}