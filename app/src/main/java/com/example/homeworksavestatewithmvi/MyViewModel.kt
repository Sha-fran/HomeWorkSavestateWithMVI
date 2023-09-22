package com.example.homeworksavestatewithmvi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {
    private val model:Model = ModelImpl
    val uiStateLiveData = MutableLiveData<UIState>(UIState.NoClickedValue)
    val intentLiveData = MutableLiveData<Intent>()
    private val observer  = Observer<Intent> {
        addItem()
    }

    init {
        intentLiveData.observeForever(observer)
    }

    private fun addItem() {
        model.addNumberOfClicks()
        uiStateLiveData.value = UIState.ClickedValue(model.getNumberOfClicks())
    }

    override fun onCleared() {
        super.onCleared()

        intentLiveData.removeObserver(observer)
    }
}

sealed class UIState {
    object NoClickedValue:UIState()
    class ClickedValue(val numberOfClicks:Int):UIState()
}

sealed class Intent {
    object AddItem: Intent()
}
