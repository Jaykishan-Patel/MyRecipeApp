package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RecipeApp(){
    val navController:NavHostController= rememberNavController()
    NavHost(navController = navController, startDestination ="firstScreen"){
        composable("firstScreen"){
               RecipeScreen(info = {
                   navController.currentBackStackEntry?.savedStateHandle?.set("info",it)
                   navController.navigate("secondScreen")
               })
        }
        composable("secondScreen") {
            val category=navController.previousBackStackEntry?.savedStateHandle?.get<Category>("info")?:Category("","","","")
            ScreenDetail(category = category)
        }
    }

}