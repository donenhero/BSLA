package com.themealdb.blsa.data.service

import com.themealdb.blsa.domain.model.MealListResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //دریافت لیست دستورپخت ها
    @GET("api/json/v1/1/search.php")
    suspend fun mealList(
        @Query("s") search:String
    ): MealListResult
}