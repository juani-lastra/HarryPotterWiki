package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiletandil.domain.entity.House
import com.mobiletandil.domain.entity.HouseHead
import com.mobiletandil.harrypotterwiki.utils.Event
import org.koin.core.component.KoinComponent

class HouseHeadsTabFragmentViewModel : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<HouseHeadsData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI(house: House) = mutableLiveData.postValue(Event(HouseHeadsData(houseData = house, statusType = HouseHeadsStatus.INIT_UI)))
    fun goToDetailedActivity(head: HouseHead) =
        mutableLiveData.postValue(Event(HouseHeadsData(headData = head, statusType = HouseHeadsStatus.GO_TO_DETAILED_ACTIVITY)))
}

data class HouseHeadsData(
    var statusType: HouseHeadsStatus,
    var houseData: House? = null,
    var headData: HouseHead? = null
)

enum class HouseHeadsStatus {
    INIT_UI,
    GO_TO_DETAILED_ACTIVITY
}
