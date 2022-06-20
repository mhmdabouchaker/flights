package com.mac.flights.network.services

import com.mac.flights.model.FlightsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Retrofit API Service
 */
interface FlightsService {

    @GET("flights")
    suspend fun getFlights(@Query("sort") sort:String,
                           @Query("asc") asc:Int,
                           @Query("locale") locale:String,
                           @Query("flyFrom") flyFrom: String,
                           @Query("to") flyTo: String,
                           @Query("date_from") dateFrom: String,
                           @Query("one_for_city") onForCity: Int,
                           @Query("featureName") featureName: String,
                           @Query("typeFlight") type: String,
                           @Query("v") version: Int,
                           @Query("limit") limit: Int,
                           @Query("partner") partner: String
    ): Response<FlightsResponse>
}