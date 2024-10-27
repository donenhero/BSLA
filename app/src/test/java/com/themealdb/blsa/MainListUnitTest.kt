package com.themealdb.blsa

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.themealdb.blsa.data.local.MealDao
import com.themealdb.blsa.data.local.MySharedPref
import com.themealdb.blsa.domain.model.MealListResult
import com.themealdb.blsa.domain.useCase.GetMealListResponseUseCase
import com.themealdb.blsa.presentation.mainList.MainListViewModel
import com.themealdb.blsa.utils.Resource
import com.themealdb.blsa.utils.Status
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

/**
 * تست های واحد مربوط به دریافت لیست دستورپخت ها در [MainListViewModel]
 */
@ExperimentalCoroutinesApi
class MainListUnitTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainListViewModel
    private lateinit var context: Context

    @Mock
    private lateinit var useCase: GetMealListResponseUseCase
    @Mock
    private lateinit var mealDao: MealDao
    @Mock
    private lateinit var sharedPref: MySharedPref
    private val testDispatcher = StandardTestDispatcher()



    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        context = mock(Context::class.java)
        viewModel = MainListViewModel(useCase,mealDao,sharedPref)
    }

    /**
     * در صورتی که لیست دستورپخت به صورت صحیح از سرور دریافت میشود
     */
    @Test
    fun loadMealList_Success() = runTest {
        // Arrange
        val expectedResult = MealListResult(ArrayList())
        whenever(useCase.invoke("")).thenReturn(expectedResult)

        // Act
        viewModel.getMealListFlowData()

        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val job = launch {
            viewModel.mealListFlowData.collect { resource ->
                if (resource.status == Status.SUCCESS)
                    assertEquals(Resource.success(expectedResult), viewModel.mealListFlowData.value)
            }
        }

        advanceUntilIdle()
        job.cancel()
    }

    /**
     * در صورتی که دریافت لیست دستورپخت با خطا رو به رو میشود
     */
    @Test
    fun loadMealList_ServerError() = runTest {
        // Arrange
        whenever(useCase.invoke(""))
            .thenThrow(RuntimeException("Server error"))

        // Act
        viewModel.getMealListFlowData()

        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val job = launch {
            viewModel.mealListFlowData.collect { resource ->
                if(resource.status == Status.ERROR)
                    assertEquals(Resource.error(null, "Server error"), viewModel.mealListFlowData.value)
            }
        }

        advanceUntilIdle()
        job.cancel()
    }


}