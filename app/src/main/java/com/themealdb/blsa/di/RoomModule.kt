package com.themealdb.blsa.di

import android.content.Context
import com.themealdb.blsa.data.local.AppDataBase
import com.themealdb.blsa.data.local.MealDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.builder(context)
    }

    @Singleton
    @Provides
    fun provideMealDao(appDataBase: AppDataBase): MealDao {
        return appDataBase.mealDao()
    }
}