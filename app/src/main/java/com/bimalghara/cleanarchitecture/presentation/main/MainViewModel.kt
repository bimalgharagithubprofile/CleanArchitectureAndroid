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

    fun authenticate(userName: String, passWord: String) = viewModelScope.launch {
        val isUsernameValid = isValidEmail(userName.trim())
        val isPassWordValid = passWord.trim().length > 4

        if (isUsernameValid && !isPassWordValid) {
            showError(ERROR_PASS_WORD_ERROR)
        } else if (!isUsernameValid && isPassWordValid) {
            showError(ERROR_USER_NAME_ERROR)
        } else if (!isUsernameValid && !isPassWordValid) {
            showError(ERROR_CHECK_YOUR_FIELDS)
        } else {

            getCountryList()
        }
    }

    private fun getCountryList(){
        getCountryListUseCase().onEach {

        _countriesLiveData.value = it

        }.launchIn(viewModelScope)
    }


}