package com.example.rmt1.models

data class RMCharacterList(val results: ArrayList<CharactersData>)
data class CharactersData(val id: Int?,
                         val name: String?,
                         val status: String?,
                         val species: String?,
                         val type: String?,
                         val gender: String?,
                         val origin: Origin?,
                         val location: Location?,
                         val image: String?,
                         val episode: ArrayList<String>?,
                         val url: String?,
                         val created: String?)

data class Origin( val name: String?, val url: String?)
data class Location( val name: String?, val url: String?)
