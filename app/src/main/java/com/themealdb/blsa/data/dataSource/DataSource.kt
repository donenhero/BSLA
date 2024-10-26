package com.themealdb.blsa.data.dataSource

import com.themealdb.blsa.domain.model.MealListResult

interface DataSource {
    suspend fun getMealListResponse(search:String): MealListResult
}