package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletandil.domain.entity.Spell
import com.mobiletandil.domain.usecase.GetOneSpellUseCase
import com.mobiletandil.domain.utils.ResponseResult
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.SpellDetailActivityStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.SpellDetailActivityStatus.ERROR_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.SpellDetailActivityStatus.INIT_UI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class SpellDetailActivityViewModel(private val getOneSpell: GetOneSpellUseCase) : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<SpellDetailActivityData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI(spellID: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) { getOneSpell(spellID) }.let {
            when (it) {
                is ResponseResult.Success<Spell> -> {
                    if (it.data.id.isNotEmpty()) {
                        mutableLiveData.postValue(Event(SpellDetailActivityData(statusType = INIT_UI, data = it.data)))
                    } else {
                        mutableLiveData.postValue(Event(SpellDetailActivityData(statusType = EMPTY_STATE)))
                    }
                }
                is ResponseResult.Failure -> {
                    mutableLiveData.postValue(Event(SpellDetailActivityData(statusType = ERROR_STATE)))
                }
            }
        }
    }
}

data class SpellDetailActivityData(
    var statusType: SpellDetailActivityStatus,
    var data: Spell? = null
)

enum class SpellDetailActivityStatus {
    INIT_UI,
    EMPTY_STATE,
    ERROR_STATE
}
