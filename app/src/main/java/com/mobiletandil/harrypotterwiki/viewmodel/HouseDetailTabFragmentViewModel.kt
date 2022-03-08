package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiletandil.domain.entity.House
import com.mobiletandil.harrypotterwiki.utils.Event
import org.koin.core.component.KoinComponent

class HouseDetailTabFragmentViewModel : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<HouseDetailData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI(house: House) = mutableLiveData.postValue(Event(HouseDetailData(houseData = house, statusType = HouseDetailStatus.INIT_UI)))
}

data class HouseDetailData(
    var statusType: HouseDetailStatus,
    var houseData: House? = null
)

enum class HouseDetailStatus {
    INIT_UI
}
