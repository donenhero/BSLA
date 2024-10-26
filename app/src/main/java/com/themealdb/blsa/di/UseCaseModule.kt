package com.themealdb.blsa.di

import com.themealdb.blsa.data.repository.DataRepository
import com.themealdb.blsa.domain.useCase.GetMealListResponseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(dataRepository: DataRepository): GetMealListResponseUseCase =
        GetMealListResponseUseCase(dataRepository)
}