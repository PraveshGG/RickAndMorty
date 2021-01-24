package com.example.rmt1.retrofit

import com.example.rmt1.models.RMCharacterList
import com.example.rmt1.models.RMEpisodeList
import com.example.rmt1.models.RMLocationList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("character")
    fun getCharactersFiltered(@Query("page") page: Int, @Query("name") name: String) : Call<RMCharacterList>

    @GET("character")
    fun getCharacters(@Query("page") page: Int) : Call<RMCharacterList>

    @GET("location")
    fun getLocation(@Query("page") page: Int) : Call<RMLocationList>

    @GET("episode")
    fun getEpisode(@Query("page") page: Int) : Call<RMEpisodeList>
}