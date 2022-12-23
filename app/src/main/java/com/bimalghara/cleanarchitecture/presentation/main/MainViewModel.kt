package com.bimalghara.cleanarchitecture.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimalghara.cleanarchitecture.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.cleanarchitecture.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.cleanarchitecture.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by BimalGhara
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val errorDetailsUseCase: GetErrorDetailsUseCase
) : BaseViewModel() {


    init {
        Log.e("chk", "reached MainViewModel")

    }

    fun test() {
        Log.e("chk", "reached MainViewModel test()")
        viewModelScope.launch {
            val m = errorDetailsUseCase(ERROR_NETWORK_ERROR)
            Log.e("chk", "hmm:: ${m.code} ${m.description}")

        }
    }
}