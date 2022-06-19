package com.mac.flights.data

import com.mac.flights.data.remote.FlightsRemoteDataSource
import com.mac.flights.di.IoDispatcher
import com.mac.flights.model.FlightsResponse
import com.mac.flights.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class FlightsRepository @Inject constructor(
    private val flightsRemoteDataSource: FlightsRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getFlights(): Flow<Result<FlightsResponse>?> {
        return flow {
            emit(Result.loading())
            val result = flightsRemoteDataSource.fetchFlights()
            emit(result)
        }.flowOn(ioDispatcher)
    }
}