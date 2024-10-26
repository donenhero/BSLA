package com.themealdb.blsa.di

import com.themealdb.blsa.data.dataSource.DataSource
import com.themealdb.blsa.data.service.ApiService
import com.themealdb.blsa.domain.model.MealListResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMealListDataSource(apiService: ApiService): DataSource {
        val dataSource:DataSource = object : DataSource {
            override suspend fun getMealListResponse(search: String): MealListResult {
                return apiService.mealList(search)
            }
        }
        return dataSource
    }
}