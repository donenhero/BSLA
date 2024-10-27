package com.themealdb.blsa.presentation.mealDetail

import androidx.lifecycle.ViewModel
import com.themealdb.blsa.data.local.MealDao
import com.themealdb.blsa.data.local.MySharedPref
import com.themealdb.blsa.data.local.SharedInterface
import com.themealdb.blsa.domain.model.MealItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel@Inject constructor(
    private val mealDao: MealDao,
    private val sharedPref: MySharedPref
) : ViewModel(),SharedInterface {

    val mealListFlowData = MutableStateFlow(MealItem())
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun getMealDetail(id:String){
        coroutineScope.launch {
            mealListFlowData.value = mealDao.getById(id) ?: MealItem()
        }
    }

    override fun addString(mealId: String) {
        sharedPref.addString(mealId)
    }

    override fun searchString(mealId: String): Boolean {
        return sharedPref.searchString(mealId)
    }

    override fun removeString(mealId: String) {
        return sharedPref.removeString(mealId)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel();
    }
}