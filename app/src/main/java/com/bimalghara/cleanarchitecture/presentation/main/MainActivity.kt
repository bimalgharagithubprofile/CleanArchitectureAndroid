package com.bimalghara.cleanarchitecture.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.bimalghara.cleanarchitecture.data.error.ERROR_NETWORK_ERROR_404
import com.bimalghara.cleanarchitecture.data.error.ERROR_USER_NAME_ERROR
import com.bimalghara.cleanarchitecture.databinding.ActivityMainBinding
import com.bimalghara.cleanarchitecture.presentation.base.BaseActivity
import com.bimalghara.cleanarchitecture.utils.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnPress.setOnClickListener {
            mainViewModel.showError(ERROR_USER_NAME_ERROR)
        }

    }


    override fun observeViewModel() {
        observeError(mainViewModel.errorSingleEvent)

        observe(mainViewModel.countriesLiveData) {
            Log.e(TAG, "observe countriesLiveData | ${it.toString()}")
            when (it) {
                is ResourceWrapper.Loading -> {
                    Log.e(TAG, "Loading...")
                    binding.progressBar.toVisible()
                }
                is ResourceWrapper.Success -> {
                    Log.e(TAG, "successful =>=> ${it.data?.size}")
                    binding.progressBar.toGone()
                }
                else -> {
                    Log.e(TAG, "error ==>> ${it.errorCode}")
                    binding.progressBar.toGone()
                    mainViewModel.showError(it.errorCode)
                }
            }
        }
    }

    private fun observeError(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}