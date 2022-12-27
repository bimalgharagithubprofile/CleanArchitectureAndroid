package com.bimalghara.cleanarchitecture.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bimalghara.cleanarchitecture.data.error.ERROR_CHECK_YOUR_FIELDS
import com.bimalghara.cleanarchitecture.data.error.ERROR_PASS_WORD_ERROR
import com.bimalghara.cleanarchitecture.data.error.ERROR_USER_NAME_ERROR
import com.bimalghara.cleanarchitecture.domain.model.country.Country
import com.bimalghara.cleanarchitecture.domain.use_case.GetCountryListUseCase
import com.bimalghara.cleanarchitecture.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.cleanarchitecture.presentation.base.BaseViewModel
import com.bimalghara.cleanarchitecture.utils.RegexUtils.isValidEmail
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
class MainViewModel @Inject constructor(
    private val errorDetailsUseCase: GetErrorDetailsUseCase,
    private val getCountryListUseCase: GetCountryListUseCase
) : BaseViewModel() {

    private val _errorSingleEvent = MutableLiveData<SingleEvent<Any>>()
    val errorSingleEvent: LiveData<SingleEvent<Any>> get() = _errorSingleEvent

    private var _countriesJob: Job? = null
    private val _countriesLiveData = MutableLiveData<ResourceWrapper<List<Country>>>()
    val countriesLiveData: LiveData<ResourceWrapper<List<Country>>> get() = _countriesLiveData

    init {
        getCountryList()
    }

    fun showError(errorCode: Int?) = viewModelScope.launch {
        errorCode?.let {
            val error = errorDetailsUseCase(errorCode)
            _errorSingleEvent.value = SingleEvent(error.description)
        }
    }

    //it will instantiate new Flow
    //to prevent this cancel the old flow if exists[it's for reloading button or so]
    fun getCountryList(){
        _countriesJob?.cancel()
        _countriesJob = getCountryListUseCase().onEach {

        _countriesLiveData.value = it

        }.launchIn(viewModelScope)
    }


}