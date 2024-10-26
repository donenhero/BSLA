package com.themealdb.blsa.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var idMeal: String = "",
    @ColumnInfo(name = "str_meal")
    var strMeal: String = "",
    @ColumnInfo(name = "str_category")
    var strCategory: String? = null,
    @ColumnInfo(name = "str_area")
    var strArea: String? = null,
    @ColumnInfo(name = "str_instructions")
    var strInstructions: String? = null,
    @ColumnInfo(name = "str_meal_thumb")
    var strMealThumb: String? = null,
    @ColumnInfo(name = "str_tags")
    var strTags: String? = null,
    @ColumnInfo(name = "str_ingredient1")
    var strIngredient1: String? = null,
    @ColumnInfo(name = "str_ingredient2")
    var strIngredient2: String? = null,
    @ColumnInfo(name = "str_ingredient3")
    var strIngredient3: String? = null,
    @ColumnInfo(name = "str_ingredient4")
    var strIngredient4: String? = null,
    @ColumnInfo(name = "str_ingredient5")
    var strIngredient5: String? = null,
    @ColumnInfo(name = "str_ingredient6")
    var strIngredient6: String? = null,
    @ColumnInfo(name = "str_ingredient7")
    var strIngredient7: String? = null,
    @ColumnInfo(name = "str_ingredient8")
    var strIngredient8: String? = null,
    @ColumnInfo(name = "str_ingredient9")
    var strIngredient9: String? = null,
    @ColumnInfo(name = "str_ingredient10")
    var strIngredient10: String? = null,
    @ColumnInfo(name = "str_ingredient11")
    var strIngredient11: String? = null,
    @ColumnInfo(name = "str_ingredient12")
    var strIngredient12: String? = null,
    @ColumnInfo(name = "str_ingredient13")
    var strIngredient13: String? = null,
    @ColumnInfo(name = "str_ingredient14")
    var strIngredient14: String? = null,
    @ColumnInfo(name = "str_ingredient15")
    var strIngredient15: String? = null,
    @ColumnInfo(name = "str_ingredient16")
    var strIngredient16: String? = null,
    @ColumnInfo(name = "str_ingredient17")
    var strIngredient17: String? = null,
    @ColumnInfo(name = "str_ingredient18")
    var strIngredient18: String? = null,
    @ColumnInfo(name = "str_ingredient19")
    var strIngredient19: String? = null,
    @ColumnInfo(name = "str_ingredient20")
    var strIngredient20: String? = null,
    @ColumnInfo(name = "str_measure1")
    var strMeasure1: String? = null,
    @ColumnInfo(name = "str_measure2")
    var strMeasure2: String? = null,
    @ColumnInfo(name = "str_measure3")
    var strMeasure3: String? = null,
    @ColumnInfo(name = "str_measure4")
    var strMeasure4: String? = null,
    @ColumnInfo(name = "str_measure5")
    var strMeasure5: String? = null,
    @ColumnInfo(name = "str_measure6")
    var strMeasure6: String? = null,
    @ColumnInfo(name = "str_measure7")
    var strMeasure7: String? = null,
    @ColumnInfo(name = "str_measure8")
    var strMeasure8: String? = null,
    @ColumnInfo(name = "str_measure9")
    var strMeasure9: String? = null,
    @ColumnInfo(name = "str_measure10")
    var strMeasure10: String? = null,
    @ColumnInfo(name = "str_measure11")
    var strMeasure11: String? = null,
    @ColumnInfo(name = "str_measure12")
    var strMeasure12: String? = null,
    @ColumnInfo(name = "str_measure13")
    var strMeasure13: String? = null,
    @ColumnInfo(name = "str_measure14")
    var strMeasure14: String? = null,
    @ColumnInfo(name = "str_measure15")
    var strMeasure15: String? = null,
    @ColumnInfo(name = "str_measure16")
    var strMeasure16: String? = null,
    @ColumnInfo(name = "str_measure17")
    var strMeasure17: String? = null,
    @ColumnInfo(name = "str_measure18")
    var strMeasure18: String? = null,
    @ColumnInfo(name = "str_measure19")
    var strMeasure19: String? = null,
    @ColumnInfo(name = "str_measure20")
    var strMeasure20: String? = null
)  {

     fun getIngredients(): List<String?> {
         val ingredients = mutableListOf<String?>()
         ingredients.add(strIngredient1)
         ingredients.add(strIngredient2)
         ingredients.add(strIngredient3)
         ingredients.add(strIngredient4)
         ingredients.add(strIngredient5)
         ingredients.add(strIngredient6)
         ingredients.add(strIngredient7)
         ingredients.add(strIngredient8)
         ingredients.add(strIngredient9)
         ingredients.add(strIngredient10)
         ingredients.add(strIngredient11)
         ingredients.add(strIngredient12)
         ingredients.add(strIngredient13)
         ingredients.add(strIngredient14)
         ingredients.add(strIngredient15)
         ingredients.add(strIngredient16)
         ingredients.add(strIngredient17)
         ingredients.add(strIngredient18)
         ingredients.add(strIngredient19)
         ingredients.add(strIngredient20)
         return ingredients.filter { !it.isNullOrEmpty() }
    }

    fun getConcatenatedIngredients(): String {
        return getIngredients().filter { !it.isNullOrEmpty() }.joinToString(", ")
    }

    fun getMeasures(): List<String?> {
        val measures =mutableListOf<String?>()
        measures.add(strMeasure1)
        measures.add(strMeasure2)
        measures.add(strMeasure3)
        measures.add(strMeasure4)
        measures.add(strMeasure5)
        measures.add(strMeasure6)
        measures.add(strMeasure7)
        measures.add(strMeasure8)
        measures.add(strMeasure9)
        measures.add(strMeasure10)
        measures.add(strMeasure11)
        measures.add(strMeasure12)
        measures.add(strMeasure13)
        measures.add(strMeasure14)
        measures.add(strMeasure15)
        measures.add(strMeasure16)
        measures.add(strMeasure17)
        measures.add(strMeasure18)
        measures.add(strMeasure19)
        measures.add(strMeasure20)
        return measures.filter { !it.isNullOrEmpty() }
    }

    fun getConcatenatedMeasures(): String {
        return getMeasures().filter { !it.isNullOrEmpty() }.joinToString(", ")
    }

}




