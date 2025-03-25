package com.example.myrecipeapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier:Modifier=Modifier,info:(Category)->Unit){
    val viewModel:RecipeViewModel= viewModel()
    val viewState by viewModel.categoriesState
    Box(){
        when{
            viewState.loading ->{
                Text(text = "Loading...",modifier.align(Alignment.Center))

            }
            viewState.error!=null ->{
                Text(text = "ERROR OCCURS ${viewState.error}")
            }
            else->{
                Column (){
                    Box (modifier.fillMaxWidth().height(160.dp)){
                        CategoryHScreen(categories = viewState.list,info)
                    }
                    Box {
                        CategoryScreen(categories = viewState.list,info)
                    }

                }
              
            }
        }
    }
}
@Composable
fun CategoryHScreen(categories:List<Category>,info:(Category)->Unit){
    LazyHorizontalGrid(rows =GridCells.Fixed(1) , modifier = Modifier.fillMaxSize()
        .padding(16.dp) ){
        items(categories){
                category->
            CategoryItem(category = category,info)
        }
    }
}

@Composable
fun CategoryScreen(categories:List<Category>,info:(Category)->Unit){
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)){
        items(categories){
            category->
            CategoryItem(category = category,info)
        }
    }
}

@Composable
fun CategoryItem(category:Category,info:(Category)->Unit){
    Column(
        Modifier
            .padding(5.dp)
            .fillMaxSize()
            .border(BorderStroke(1.dp, color = Color.Blue), RoundedCornerShape(5)).
        clickable { info(category) },
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = category.strCategory,
            color = Color.Cyan,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top=10.dp,))

        Image(painter = rememberAsyncImagePainter(model = category.strCategoryThumb), contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f))
    }
    
}