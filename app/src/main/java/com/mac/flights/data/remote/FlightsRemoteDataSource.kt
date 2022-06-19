package com.mac.flights.data.remote

import android.util.Log
import com.mac.flights.model.FlightsResponse
import com.mac.flights.model.Result
import com.mac.flights.network.services.FlightsService
import com.mac.flights.utils.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * fetches data from remote source
 */
class FlightsRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchFlights() : Result<FlightsResponse>{
        val flightsService = retrofit.create(FlightsService::class.java)
        return getResponse(
            request = {flightsService.getFlights("popularity", 0, "en",
                "49.2-16.61-500km", "anywhere", "aggregateResults",
                "oneway", 3, 5, "skypicker-android")},
            defaultErrorMessage = "Something Went Wrong"
        )
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T>{
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Error" + e.message, null)
        }
    }
}