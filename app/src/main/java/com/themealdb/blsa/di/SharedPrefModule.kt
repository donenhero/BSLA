package com.themealdb.blsa.di

import android.content.Context
import com.themealdb.blsa.data.local.MySharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {
    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context): MySharedPref {
        return MySharedPref(context)
    }
}