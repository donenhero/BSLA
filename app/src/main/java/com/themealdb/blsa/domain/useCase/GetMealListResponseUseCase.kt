package com.themealdb.blsa.domain.useCase

import com.themealdb.blsa.data.repository.DataRepository

class GetMealListResponseUseCase(private val dataRepository: DataRepository) {
    suspend operator fun invoke(search:String) = dataRepository.getMealListResponse(search)
}
