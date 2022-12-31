package com.bimalghara.cleanarchitecture.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bimalghara.cleanarchitecture.R
import com.bimalghara.cleanarchitecture.databinding.FragmentHomeBinding
import com.bimalghara.cleanarchitecture.presentation.base.BaseFragment
import com.bimalghara.cleanarchitecture.utils.*
import com.bimalghara.cleanarchitecture.utils.permissions.PermissionManager
import com.bimalghara.cleanarchitecture.utils.permissions.Permissions
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val TAG = javaClass.simpleName

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    private val permissionManager = PermissionManager().from(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.btnPermissions.setOnClickListener {

        permissionManager
            .request(Permissions.Camera)
            .rationale("We need all Permissions to see your face")
            .checkPermission { granted ->
                if(granted)
                    Log.e(TAG, "runtime permissions allowed")
                else
                    Log.e(TAG, "runtime permissions denied")
            }

        }

        binding.btnPress.setOnClickListener {
            binding.root.hideKeyboard()

            homeViewModel.getCountryList()
        }

        return binding.root
    }


    override fun observeViewModel() {
        observeError(binding.container, homeViewModel.errorSingleEvent)

        observe(homeViewModel.countriesLiveData) {
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
                    homeViewModel.showError(it.errorCode)
                }
            }
        }
    }
}