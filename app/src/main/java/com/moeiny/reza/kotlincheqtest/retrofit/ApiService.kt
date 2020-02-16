package com.moeiny.reza.kotlincheqtest.retrofit

import com.moeiny.reza.cheqtest.data.Cheq
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("13u6ok")
    fun getCheq():Call<Cheq>
}