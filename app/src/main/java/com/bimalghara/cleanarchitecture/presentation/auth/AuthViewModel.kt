package com.bimalghara.cleanarchitecture.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimalghara.cleanarchitecture.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.cleanarchitecture.domain.use_case.RegisterOrLoginUseCase
import com.bimalghara.cleanarchitecture.presentation.base.BaseViewModel
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import com.bimalghara.cleanarchitecture.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by BimalGhara
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val errorDetailsUseCase: GetErrorDetailsUseCase,
    private val registerOrLoginUseCase: RegisterOrLoginUseCase
) : BaseViewModel() {

    private val _errorSingleEvent = MutableLiveData<SingleEvent<Any>>()
    val errorSingleEvent: LiveData<SingleEvent<Any>> get() = _errorSingleEvent

    private var _registerOrLoginJob: Job? = null
    private val _registerOrLoginLiveData = MutableLiveData<ResourceWrapper<Long>>()
    val registerOrLoginLiveData: LiveData<ResourceWrapper<Long>> get() = _registerOrLoginLiveData


    fun showError(errorCode: Int?) = viewModelScope.launch {
        errorCode?.let {
            val error = errorDetailsUseCase(it)
            _errorSingleEvent.value = SingleEvent(error.description)
        }
    }

    //it will instantiate new Flow
    //to prevent this cancel the old flow if exists[it's for re-attempt to register/login or so]
    fun authenticate(userName: String, passWord: String) {
        _registerOrLoginJob?.cancel()
        _registerOrLoginJob = registerOrLoginUseCase(userName, passWord).onEach {
            _registerOrLoginLiveData.value = it
        }.launchIn(viewModelScope)

    }



}