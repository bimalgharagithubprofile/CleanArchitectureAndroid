package com.bimalghara.cleanarchitecture.presentation.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimalghara.cleanarchitecture.data.error.CustomException
import com.bimalghara.cleanarchitecture.data.error.ErrorDetails
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import com.bimalghara.cleanarchitecture.domain.use_case.GetAllRegisteredUsersUseCase
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
    errorDetailsUseCase: GetErrorDetailsUseCase,
    private val registerOrLoginUseCase: RegisterOrLoginUseCase,
    private val getAllRegisteredUsersUseCase: GetAllRegisteredUsersUseCase
) : BaseViewModel(errorDetailsUseCase) {
    private val logTag = javaClass.simpleName

    private var _getAllUsersJob: Job? = null
    private val _getAllUsersLiveData = MutableLiveData<List<AuthData>>(emptyList())
    val getAllUsersLiveData: LiveData<List<AuthData>> get() = _getAllUsersLiveData

    private var _registerOrLoginJob: Job? = null
    private val _registerOrLoginLiveData = MutableLiveData<ResourceWrapper<Long>>()
    val registerOrLoginLiveData: LiveData<ResourceWrapper<Long>> get() = _registerOrLoginLiveData

    init {
        getAllRegisteredUsers()
    }


    //it will instantiate new Flow
    //to prevent this cancel the old flow if exists[it's for re-attempt to register/login or so]
    fun authenticate(userName: String, passWord: String) {
        _registerOrLoginJob?.cancel()
        _registerOrLoginJob = registerOrLoginUseCase(userName, passWord).onEach {

            when(it){
                is ResourceWrapper.Error -> showError(it.error)
                else-> Unit
            }

            _registerOrLoginLiveData.value = it

        }.launchIn(viewModelScope)

    }

    private fun getAllRegisteredUsers(){
        getAllRegisteredUsersUseCase().onEach {

            _getAllUsersLiveData.value = (getAllUsersLiveData.value?.plus(it))

        }.launchIn(viewModelScope)
    }


}