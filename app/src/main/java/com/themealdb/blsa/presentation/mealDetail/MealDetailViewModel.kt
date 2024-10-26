package com.themealdb.blsa.presentation.mealDetail

import androidx.lifecycle.ViewModel
import com.themealdb.blsa.data.local.MealDao
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
    private val mealDao: MealDao
) : ViewModel() {

    val mealListFlowData = MutableStateFlow(MealItem())
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun getMealDetail(id:String){
        coroutineScope.launch {
            mealListFlowData.value = mealDao.getById(id) ?: MealItem()
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel();
    }
}