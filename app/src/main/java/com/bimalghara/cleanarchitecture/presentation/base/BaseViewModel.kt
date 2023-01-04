package com.bimalghara.cleanarchitecture.presentation.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimalghara.cleanarchitecture.data.error.CustomException
import com.bimalghara.cleanarchitecture.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.cleanarchitecture.utils.SingleEvent
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val errorDetailsUseCase: GetErrorDetailsUseCase,
) : ViewModel(){
    private val logTag = javaClass.simpleName

    private val _errorSingleEvent = MutableLiveData<SingleEvent<Any>>()
    val errorSingleEvent: LiveData<SingleEvent<Any>> get() = _errorSingleEvent

    protected fun showError(errorDetails: CustomException?) = viewModelScope.launch {
        errorDetails?.message?.let {
            Log.e(logTag, "showing error: $it")
            val error = errorDetailsUseCase(it)
            _errorSingleEvent.value = SingleEvent(error.description)
        }
    }

}