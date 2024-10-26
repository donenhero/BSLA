package com.themealdb.blsa.presentation.search

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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
class SearchViewModel@Inject constructor(
    private val mealListResponseUseCase: GetMealListResponseUseCase,
    private val mealDao: MealDao
) : ViewModel() {

    val mealListFlowData = MutableStateFlow(Resource.loading<MealListResult>())
    private val coroutineScope = CoroutineScope(Dispatchers.Main)


    fun getMealListFlowData(strSearch: String){
        mealListFlowData.value = Resource.loading()
        coroutineScope.launch {
            try {
                val mealListResult = mealListResponseUseCase.invoke(strSearch)
                if (mealListResult.meals.isNullOrEmpty())
                    getMealFlowDataOffline(Exception("Not Found"),strSearch)
                else {
                    mealDao.insert(mealListResult.meals ?: ArrayList())
                    mealListFlowData.value = Resource.success(mealListResult)
                }
            } catch (exception: Exception) {
                getMealFlowDataOffline(exception,strSearch)
            }
        }
    }

    fun getMealFlowDataOffline(exception: Exception,strSearch: String){
        coroutineScope.launch {
            val mealListResult = MealListResult(ArrayList())
            mealListResult.meals = mealDao.searchAll(strSearch)
            if(mealListResult.meals.isNullOrEmpty()){
                mealListFlowData.value = Resource.error(null, exception.message ?: "Server Error")
            }
            else
                mealListFlowData.value = Resource.success(mealListResult)
        }
    }


    fun hideInputKeyboard(context: Context) {
        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = (context as Activity).currentFocus
        if (view == null) {
            view = View(context)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel();
    }



}