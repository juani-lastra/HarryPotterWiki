package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.utils.Event
import org.koin.core.component.KoinComponent

class HouseTraitsTabFragmentViewModel : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<HouseTraitsData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI(house: House) = mutableLiveData.postValue(Event(HouseTraitsData(houseData = house, statusType = HouseTraitsStatus.INIT_UI)))
}

data class HouseTraitsData(
    var statusType: HouseTraitsStatus,
    var houseData: House? = null
)

enum class HouseTraitsStatus {
    INIT_UI
}
