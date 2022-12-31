package com.bimalghara.cleanarchitecture.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bimalghara.cleanarchitecture.databinding.ActivityMainBinding
import com.bimalghara.cleanarchitecture.presentation.base.BaseActivity
import com.bimalghara.cleanarchitecture.utils.*
import com.bimalghara.cleanarchitecture.utils.permissions.Permissions
import com.bimalghara.cleanarchitecture.utils.permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val permissionManager = PermissionManager().from(this)


    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //binding.btnPermissions.setOnClickListener {

            permissionManager
                .request(Permissions.Camera)
                .rationale("We need all Permissions to see your face")
                .checkPermission { granted ->
                    if(granted)
                        Log.e(TAG, "runtime permissions allowed")
                    else
                        Log.e(TAG, "runtime permissions denied")
                }

        //}

        binding.btnPress.setOnClickListener {
            binding.root.hideKeyboard()

            mainViewModel.getCountryList()
        }

    }


    override fun observeViewModel() {
        observeError(binding.root, mainViewModel.errorSingleEvent)

        observe(mainViewModel.countriesLiveData) {
            Log.e(TAG, "observe countriesLiveData | ${it.toString()}")
            binding.txtResult.text = it.toString()
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
}