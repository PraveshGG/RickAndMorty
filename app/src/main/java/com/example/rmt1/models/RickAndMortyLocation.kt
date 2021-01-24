package com.example.rmt1.models

data class RMLocationList(val results: ArrayList<LocationsData>)
data class LocationsData(val id: Int?,
                         val name: String?,
                         val type: String?,
                         val dimension: String?,
                         val residents: ArrayList<String>?,
                         val url: String?,
                         val created: String?)

