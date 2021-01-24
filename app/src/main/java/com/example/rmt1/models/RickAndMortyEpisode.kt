package com.example.rmt1.models

data class RMEpisodeList(val results: ArrayList<EpisodeData>)
data class EpisodeData(val id: Int?,
                         val name: String?,
                         val air_date: String?,
                         val episode: String?,
                         val characters:ArrayList<String>?,
                         val url: String?,
                         val created: String?)


