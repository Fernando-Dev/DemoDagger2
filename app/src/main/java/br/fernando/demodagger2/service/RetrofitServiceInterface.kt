package br.fernando.demodagger2.service

import br.fernando.demodagger2.model.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceInterface {

    @GET("repositories")
    fun getDataFromAPi(@Query("q") query: String): Call<RecyclerList>

}