package com.themealdb.blsa.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.themealdb.blsa.domain.model.MealItem

@Database(
    entities = [MealItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase:RoomDatabase(){
    abstract fun mealDao(): MealDao

    companion object{
        private const val DATABASE_NAME = "meals.db"
        fun builder(context: Context): AppDataBase{
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
