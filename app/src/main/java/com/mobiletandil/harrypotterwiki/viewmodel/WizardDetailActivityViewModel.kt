package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletandil.domain.entity.Wizard
import com.mobiletandil.domain.usecase.GetOneWizardsUseCase
import com.mobiletandil.domain.utils.ResponseResult
import com.mobiletandil.harrypotterwiki.utils.Event
import com.mobiletandil.harrypotterwiki.viewmodel.WizardDetailActivityStatus.EMPTY_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.WizardDetailActivityStatus.ERROR_STATE
import com.mobiletandil.harrypotterwiki.viewmodel.WizardDetailActivityStatus.INIT_UI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class WizardDetailActivityViewModel(private val getOneWizard: GetOneWizardsUseCase) : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<WizardDetailActivityData>> = MutableLiveData()
    fun liveData() = mutableLiveData

    fun initUI(wizardID: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) { getOneWizard(wizardID) }.let {
            when (it) {
                is ResponseResult.Success<Wizard> -> {
                    if (it.data.id.isNotEmpty()) {
                        mutableLiveData.postValue(Event(WizardDetailActivityData(statusType = INIT_UI, data = it.data)))
                    } else {
                        mutableLiveData.postValue(Event(WizardDetailActivityData(statusType = EMPTY_STATE)))
                    }
                }
                is ResponseResult.Failure -> {
                    mutableLiveData.postValue(Event(WizardDetailActivityData(statusType = ERROR_STATE)))
                }
            }
        }
    }
}

data class WizardDetailActivityData(
    var statusType: WizardDetailActivityStatus,
    var data: Wizard? = null
)

enum class WizardDetailActivityStatus {
    INIT_UI,
    EMPTY_STATE,
    ERROR_STATE
}
