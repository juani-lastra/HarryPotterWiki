package com.mobiletandil.data.service.api

import com.mobiletandil.data.service.response.HouseResultResponse
import com.mobiletandil.data.service.response.SpellsResponse
import com.mobiletandil.data.service.response.WizardsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterApi {
    @GET("houses/{house}")
    fun getHouse(@Path("house") house: String): Call<HouseResultResponse>

    @GET("wizards/")
    fun getAllWizards(): Call<List<WizardsResponse>>

    @GET("spells/")
    fun getAllSpells(): Call<List<SpellsResponse>>
}
