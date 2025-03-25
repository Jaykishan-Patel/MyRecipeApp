package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeViewModel:ViewModel() {
    private val _categoriesState= mutableStateOf(RecipeAction())
    val categoriesState:State<RecipeAction> =_categoriesState
    init {
        fetchCategories()
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response= recipeService.getCategories()
                _categoriesState.value=_categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }
            catch (e:Exception){
                _categoriesState.value=_categoriesState.value.copy(
                    loading = false,
                    error="error fetching categories ${e.message}"
                )
            }
        }
    }
    data class RecipeAction(
        val loading: Boolean=true,
        val list: List<Category> = emptyList(),
        val error:String?=null
    )
}