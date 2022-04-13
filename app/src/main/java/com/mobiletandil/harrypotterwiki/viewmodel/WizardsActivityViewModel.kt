package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.usecase.GetAllWizardsUseCase
import com.mobiletandil.domain.utils.Constants.EMPTY_STRING
import com.mobiletandil.domain.utils.ResponseResult
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.ERROR_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.GO_TO_DETAILED_SCREEN
import com.mobiletandil.harrypotterwiki.viewmodel.WizardsActivityStatus.INIT_UI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class WizardsActivityViewModel(private val getAllWizards: GetAllWizardsUseCase) : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<WizardsActivityData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI() = viewModelScope.launch {
        withContext(Dispatchers.IO) { getAllWizards() }.let {
            when (it) {
                is ResponseResult.Success<List<Wizard>> -> {
                    if (it.data.isNotEmpty()) {
                        mutableLiveData.postValue(Event(WizardsActivityData(listOfWizards = it.data, statusType = INIT_UI)))
                    } else {
                        mutableLiveData.postValue(Event(WizardsActivityData(statusType = EMPTY_STATE)))
                    }
                }
                is ResponseResult.Failure -> {
                    mutableLiveData.postValue(Event(WizardsActivityData(statusType = ERROR_STATE)))
                }
            }
        }
    }

    fun goToDetailedScreen(wizardID: String) {
        mutableLiveData.postValue(Event(WizardsActivityData(wizard = wizardID, statusType = GO_TO_DETAILED_SCREEN)))
    }
}

data class WizardsActivityData(
    var statusType: WizardsActivityStatus,
    var listOfWizards: List<Wizard> = emptyList(),
    var wizard: String = EMPTY_STRING
)

enum class WizardsActivityStatus {
    INIT_UI,
    ERROR_STATE,
    EMPTY_STATE,
    GO_TO_DETAILED_SCREEN
}
