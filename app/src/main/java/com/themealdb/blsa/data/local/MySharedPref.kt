package com.themealdb.blsa.data.local

import android.content.Context
import android.content.SharedPreferences

class MySharedPref (context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    companion object {
        private const val PREF_NAME = "my_preferences"
        private const val KEY_FAV_LIST = "fav_list"
    }

    // ذخیره یک علاقمندی در لیست
    fun addString(mealId: String) {
        val set = getStringSet().toMutableSet()
        set.add(mealId)
        saveStringSet(set)
    }

    // جستجو در علاقمندی
    fun searchString(mealId: String): Boolean {
        return getStringSet().contains(mealId)
    }

    // حذف یک علاقمندی از لیست
    fun removeString(mealId: String) {
        val set = getStringSet().toMutableSet()
        set.remove(mealId)
        saveStringSet(set)
    }

    // ذخیره‌سازی Set از علاقمندیها
    private fun saveStringSet(set: Set<String>) {
        prefs.edit().putStringSet(KEY_FAV_LIST, set).apply()
    }

    // خواندن Set از علاقمندیها
    private fun getStringSet(): Set<String> {
        return prefs.getStringSet(KEY_FAV_LIST, emptySet()) ?: emptySet()
    }
}