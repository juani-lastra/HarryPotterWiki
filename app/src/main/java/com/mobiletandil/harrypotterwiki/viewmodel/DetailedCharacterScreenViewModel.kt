package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiletandil.domain.entity.HouseHead
import com.mobiletandil.domain.entity.Spells
import com.mobiletandil.domain.entity.Wizards
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.DetailedCharacterStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.DetailedCharacterStatus.INIT_UI_HEAD
import com.mobiletandil.harrypotterwiki.viewmodel.DetailedCharacterStatus.INIT_UI_SPELL
import com.mobiletandil.harrypotterwiki.viewmodel.DetailedCharacterStatus.INIT_UI_WIZARD
import org.koin.core.component.KoinComponent

class DetailedCharacterScreenViewModel() : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<DetailedCharacterData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI(head: HouseHead?, wizard: Wizards?, spell: Spells?) {
        when {
            head != null -> mutableLiveData.postValue(Event(DetailedCharacterData(headData = head, statusType = INIT_UI_HEAD)))
            wizard != null -> mutableLiveData.postValue(Event(DetailedCharacterData(wizardData = wizard, statusType = INIT_UI_WIZARD)))
            spell != null -> mutableLiveData.postValue(Event(DetailedCharacterData(spellData = spell, statusType = INIT_UI_SPELL)))
            else -> mutableLiveData.postValue(Event(DetailedCharacterData(statusType = EMPTY_STATE)))
        }
    }
}

data class DetailedCharacterData(
    var statusType: DetailedCharacterStatus,
    var headData: HouseHead? = null,
    var wizardData: Wizards? = null,
    var spellData: Spells? = null
)

enum class DetailedCharacterStatus {
    INIT_UI_HEAD,
    INIT_UI_WIZARD,
    INIT_UI_SPELL,
    EMPTY_STATE
}
