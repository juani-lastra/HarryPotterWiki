package com.mobiletandil.harrypotterwiki.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiletandil.harrypotterwiki.utils.Event
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class SplashActivityViewModel : ViewModel(), KoinComponent {
    private var mutableLiveData: MutableLiveData<Event<Data>> = MutableLiveData()

    fun liveData() = mutableLiveData

    fun loadMainActivity() = viewModelScope.launch {
        withContext(IO) {
            delay(LOAD_TIME)
            mutableLiveData.postValue(Event(Data(statusType = Status.LOAD_COMPLETE)))
        }
    }

    companion object {
        private const val LOAD_TIME: Long = 3000
    }
}

data class Data(
    var statusType: Status
)

enum class Status { LOAD_COMPLETE }
