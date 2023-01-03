package com.bimalghara.cleanarchitecture.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimalghara.cleanarchitecture.data.error.ERROR_NO_INTERNET_CONNECTION
import com.bimalghara.cleanarchitecture.domain.model.country.Country
import com.bimalghara.cleanarchitecture.domain.use_case.GetCountryListUseCase
import com.bimalghara.cleanarchitecture.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.cleanarchitecture.domain.use_case.GetUserSessionUseCase
import com.bimalghara.cleanarchitecture.presentation.base.BaseViewModel
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import com.bimalghara.cleanarchitecture.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by BimalGhara
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkConnectivitySource: NetworkConnectivitySource,
    private val errorDetailsUseCase: GetErrorDetailsUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase,
    private val getCountryListUseCase: GetCountryListUseCase
) : BaseViewModel() {
    private val TAG = javaClass.simpleName

    private val _networkConnectivityLiveData = MutableLiveData<NetworkConnectivitySource.Status>()
    val networkConnectivityLiveData: LiveData<NetworkConnectivitySource.Status> get() = _networkConnectivityLiveData

    private val _errorSingleEvent = MutableLiveData<SingleEvent<Any>>()
    val errorSingleEvent: LiveData<SingleEvent<Any>> get() = _errorSingleEvent

    private var _userSessionJob: Job? = null
    private val _userSessionLiveData = MutableLiveData<Long>()
    val userSessionLiveData: LiveData<Long> get() = _userSessionLiveData

    private var _countriesJob: Job? = null
    private val _countriesLiveData = MutableLiveData<ResourceWrapper<List<Country>>>()
    val countriesLiveData: LiveData<ResourceWrapper<List<Country>>> get() = _countriesLiveData

    init {
        observeNetworkStatus()
        getUserSessionDetails()
    }

    fun showError(errorCode: Int?) = viewModelScope.launch {
        errorCode?.let {
            val error = errorDetailsUseCase(errorCode)
            _errorSingleEvent.value = SingleEvent(error.description)
        }
    }

    private fun observeNetworkStatus() = viewModelScope.launch {
        networkConnectivitySource.observe().collectLatest {
            Log.e(TAG, "network status: $it")
            _networkConnectivityLiveData.value = it
        }
    }

    private fun getUserSessionDetails(){
        _userSessionJob?.cancel()
        _userSessionJob = getUserSessionUseCase().onEach {
            _userSessionLiveData.value = it
        }.launchIn(viewModelScope)
    }

    //it will instantiate new Flow
    //to prevent this cancel the old flow if exists[it's for reloading button or so]
    fun getCountryList() {
        if(networkConnectivityLiveData.value != NetworkConnectivitySource.Status.Available) {
            showError(ERROR_NO_INTERNET_CONNECTION)
            return
        }

        _countriesJob?.cancel()
        _countriesJob = getCountryListUseCase().onEach {

        _countriesLiveData.value = it

        }.launchIn(viewModelScope)
    }


}