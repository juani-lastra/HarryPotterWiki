package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.usecase.GetOneHouseUseCase
import com.mobiletandil.domain.utils.Houses
import com.mobiletandil.domain.utils.ResponseResult
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityStatus.ERROR_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.HousesActivityStatus.SET_TABS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class HousesActivityViewModel(private val getOneHouse: GetOneHouseUseCase) : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<HousesActivityData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun setTabs(nameOfHouse: Houses) = viewModelScope.launch {
        withContext(Dispatchers.IO) { getOneHouse(nameOfHouse) }.let {
            when (it) {
                is ResponseResult.Success<House> -> {
                    if (it.data.name.isNotEmpty()) {
                        mutableLiveData.postValue(Event(HousesActivityData(houseData = it.data, statusType = SET_TABS)))
                    } else {
                        mutableLiveData.postValue(Event(HousesActivityData(statusType = EMPTY_STATE)))
                    }
                }
                is ResponseResult.Failure -> {
                    mutableLiveData.postValue(Event(HousesActivityData(statusType = ERROR_STATE)))
                }
            }
        }
    }
}

data class HousesActivityData(
    var statusType: HousesActivityStatus,
    var houseData: House? = null
)

enum class HousesActivityStatus {
    SET_TABS,
    ERROR_STATE,
    EMPTY_STATE
}
