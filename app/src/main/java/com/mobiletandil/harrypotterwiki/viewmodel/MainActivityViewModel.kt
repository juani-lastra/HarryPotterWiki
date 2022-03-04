package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.utils.Houses
import com.mobiletandil.harrypotterwiki.utils.Houses.NONE
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityStatus.GO_TO_HOUSES_ACTIVITY
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityStatus.GO_TO_SPELLS_ACTIVITY
import com.mobiletandil.harrypotterwiki.viewmodel.MainActivityStatus.GO_TO_WIZARDS_ACTIVITY
import org.koin.core.component.KoinComponent

class MainActivityViewModel : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<MainActivityData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun goToHousesActivity(house: Houses) {
        mutableLiveData.postValue(Event(MainActivityData(house = house, statusType = GO_TO_HOUSES_ACTIVITY)))
    }

    fun goToSpellsActivity() {
        mutableLiveData.postValue(Event(MainActivityData(statusType = GO_TO_SPELLS_ACTIVITY)))
    }

    fun goToWizardsActivity() {
        mutableLiveData.postValue(Event(MainActivityData(statusType = GO_TO_WIZARDS_ACTIVITY)))
    }
}

data class MainActivityData(
    var statusType: MainActivityStatus,
    var house: Houses = NONE
)

enum class MainActivityStatus {
    GO_TO_HOUSES_ACTIVITY,
    GO_TO_SPELLS_ACTIVITY,
    GO_TO_WIZARDS_ACTIVITY
}
