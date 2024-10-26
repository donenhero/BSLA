package com.themealdb.blsa.presentation.mainList

import androidx.lifecycle.ViewModel
import com.themealdb.blsa.data.local.MealDao
import com.themealdb.blsa.domain.model.MealListResult
import com.themealdb.blsa.domain.useCase.GetMealListResponseUseCase
import com.themealdb.blsa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel@Inject constructor(
    private val mealListResponseUseCase: GetMealListResponseUseCase,
    private val mealDao: MealDao
) : ViewModel() {

    val mealListFlowData = MutableStateFlow(Resource.loading<MealListResult>())
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    init {
        getMealListFlowData()
    }

    fun getMealListFlowData(){
        mealListFlowData.value = Resource.loading()
        coroutineScope.launch {
            try {
                val videoListResult = mealListResponseUseCase.invoke("")
                if (videoListResult.meals.isNullOrEmpty())
                    getMealFlowDataOffline(Exception("Not Found"))
                else {
                    mealDao.insert(videoListResult.meals ?: ArrayList())
                    mealListFlowData.value = Resource.success(videoListResult)
                }
            } catch (exception: Exception) {
                getMealFlowDataOffline(exception)
            }
        }
    }

    fun getMealFlowDataOffline(exception: Exception){
        coroutineScope.launch {
            val mealListResult = MealListResult(ArrayList())
            mealListResult.meals = mealDao.getAll()
            if(mealListResult.meals.isNullOrEmpty()){
                mealListFlowData.value = Resource.error(null, exception.message ?: "Server Error")
            }
            else
                mealListFlowData.value = Resource.success(mealListResult)
        }
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel();
    }



}