package com.example.vili.Common.Complex

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vili.Common.Complex.BottomBarClass.Companion.updateIndex
import com.example.vili.R
import viliApp.MainScreenViewModel
import viliApp.NavigationFunctions

@Composable
fun BottomBar(viewModel: MainScreenViewModel = hiltViewModel()) {

    AnimatedVisibility(
        visible = BottomBarClass.showBar.value, enter = slideInVertically(
            initialOffsetY = { fullHeight -> +fullHeight }, animationSpec = tween(
                durationMillis = 300, easing = LinearOutSlowInEasing
            )
        ), exit = slideOutVertically(
            targetOffsetY = { fullHeight -> +fullHeight }, animationSpec = tween(
                durationMillis = 300, easing = FastOutLinearInEasing
            )
        )
    ) {
        BottomNavigation(
            modifier = Modifier.fillMaxHeight(0.07f),
            elevation = 10.dp,
            backgroundColor = Color(0xDD050505)
        ) {

            BottomNavigationItem(icon = {
                Icon(imageVector = Icons.Default.Home, "")
            }, selected = (BottomBarClass.selectedIndex.value == 1), onClick = {
                updateIndex(1)
                NavigationFunctions.changeScreen(1)
            }, selectedContentColor = Color.Red, unselectedContentColor = Color.White
            )

            BottomNavigationItem(icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.list),
                    "",
                    modifier = Modifier.size(30.dp)
                )
            }, selected = (BottomBarClass.selectedIndex.value == 2), onClick = {
                updateIndex(2)
                NavigationFunctions.changeScreen(2)
            }, selectedContentColor = Color.Red, unselectedContentColor = Color.White
            )
        }
    }
}

class BottomBarClass(){
    companion object{
        var selectedIndex = mutableStateOf(1)
        var showBar = mutableStateOf(true)

        fun turnBottomBar(){
            showBar.value = !showBar.value
        }

        fun updateIndex(newIndex : Int){
            selectedIndex.value = newIndex
        }
    }
}