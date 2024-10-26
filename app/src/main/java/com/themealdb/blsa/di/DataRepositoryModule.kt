package com.themealdb.blsa.di

import com.themealdb.blsa.data.dataSource.DataSource
import com.themealdb.blsa.data.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(dataSource: DataSource):DataRepository =
        DataRepository(dataSource)
}