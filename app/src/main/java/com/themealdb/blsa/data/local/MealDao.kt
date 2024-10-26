package com.themealdb.blsa.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themealdb.blsa.domain.model.MealItem

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meals: List<MealItem>)

    @Query("SELECT * FROM meals")
    suspend fun getAll(): List<MealItem>

    @Query("SELECT * FROM meals WHERE id = :pId")
    suspend fun getById(pId: String): MealItem?


}