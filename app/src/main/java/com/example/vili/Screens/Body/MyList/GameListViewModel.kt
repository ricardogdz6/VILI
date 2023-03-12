package com.example.vili.Screens.Body.MyList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.vili.Model.Querys.FBAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import viliApp.CentralizedData
import viliApp.FBCRUD
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    var tabIndex by savedStateHandle.saveable { mutableStateOf(0) }


    init {

        viewModelScope.launch {
            CentralizedData.gameList.value = FBCRUD.getUserGameList(CentralizedData.profileID.value)

        }

        viewModelScope.launch {
            CentralizedData.planningList.value = FBCRUD.getUserGamePlanningList(CentralizedData.profileID.value)
        }

    }

    fun reloadList(){

        viewModelScope.launch {
            CentralizedData.gameList.value = FBCRUD.getUserGameList(CentralizedData.profileID.value)
        }

        viewModelScope.launch {

            CentralizedData.planningList.value = FBCRUD.getUserGamePlanningList(CentralizedData.profileID.value)
        }



    }

    fun updateTabData(newValue: Int){
        tabIndex = newValue
    }

    fun sortList(newValue: Int){

        when(newValue){

            0 -> {
                if (tabIndex==0){
                    CentralizedData.gameList.value = CentralizedData.gameList.value.sortedBy { it.name }}
                else if (tabIndex==1){
                    CentralizedData.planningList.value = CentralizedData.planningList.value.sortedBy { it.name }}

            }

            1 -> {
                CentralizedData.gameList.value = CentralizedData.gameList.value.sortedByDescending { it.userScore }
            }

        }

    }

    //region NavigateHome
    var popBack by savedStateHandle.saveable { mutableStateOf(false) }
    fun popBack(){
        popBack = !popBack
    }
    //endregion


}
