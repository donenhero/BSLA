package com.themealdb.blsa.data.repository

import com.themealdb.blsa.data.dataSource.DataSource


class DataRepository(private val dataSource: DataSource) {
    suspend fun getMealListResponse(search:String) = dataSource.getMealListResponse(search)
}