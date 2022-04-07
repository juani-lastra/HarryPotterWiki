package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletandil.domain.entity.Spells
import com.mobiletandil.domain.usecase.GetAllSpellsUseCase
import com.mobiletandil.domain.utils.ResponseResult
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.SpellsActivityStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.SpellsActivityStatus.ERROR_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.SpellsActivityStatus.GO_TO_DETAILED_SCREEN
import com.mobiletandil.harrypotterwiki.viewmodel.SpellsActivityStatus.INIT_UI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class SpellsActivityViewModel(private val getAllSpells: GetAllSpellsUseCase) : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<SpellsActivityData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getAllSpells() }.let {
            when (it) {
                is ResponseResult.Success<List<Spells>> -> {
                    if (it.data.isNotEmpty()) {
                        mutableLiveData.postValue(Event(SpellsActivityData(listOfSpells = it.data, statusType = INIT_UI)))
                    } else {
                        mutableLiveData.postValue(Event(SpellsActivityData(statusType = EMPTY_STATE)))
                    }
                }
                is ResponseResult.Failure -> {
                    mutableLiveData.postValue(Event(SpellsActivityData(statusType = ERROR_STATE)))
                }
            }
        }
    }

    fun goToDetailedScreen(spell: Spells) {
        mutableLiveData.postValue(Event(SpellsActivityData(spell = spell, statusType = GO_TO_DETAILED_SCREEN)))
    }
}

data class SpellsActivityData(
    var statusType: SpellsActivityStatus,
    var listOfSpells: List<Spells>? = null,
    var spell: Spells? = null
)

enum class SpellsActivityStatus {
    INIT_UI,
    ERROR_STATE,
    EMPTY_STATE,
    GO_TO_DETAILED_SCREEN
}
