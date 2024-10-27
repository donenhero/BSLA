package com.themealdb.blsa.data.local

interface SharedInterface {
    fun addString(mealId: String)
    fun searchString(mealId: String):Boolean
    fun removeString(mealId: String)
}