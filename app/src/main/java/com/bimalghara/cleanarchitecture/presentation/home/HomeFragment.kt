package com.bimalghara.cleanarchitecture.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val logTag = javaClass.simpleName

    private val homeViewModel: HomeViewModel by viewModels()

    private val permissionManager = PermissionManager.from(this)

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPermissions.setOnClickListener {
            permissionManager
                .request(Permissions.Camera)
                .rationale("We need all Permissions to see your face")
                .checkPermission { granted ->
                    if(granted)
                        Log.e(logTag, "runtime permissions allowed")
                    else
                        Log.e(logTag, "runtime permissions denied")
                }
        }

        binding.btnPress.setOnClickListener {
            binding.root.hideKeyboard()

            homeViewModel.getCountryList()
        }
    }


    override fun observeViewModel() {
        observeError(homeViewModel.errorSingleEvent)

        observe(homeViewModel.networkConnectivityLiveData) {
            binding.tvNetworkStatus.text = "Network Status: $it"
        }
        observe(homeViewModel.userSessionLiveData) {
            if(it > 0) {
                binding.tvUserSession.text = "Last Logged-In: $it"
            }
        }
        observe(homeViewModel.countriesLiveData) {
            Log.e(logTag, "observe countriesLiveData | $it")
            binding.txtResult.text = it.toString()
            when (it) {
                is ResourceWrapper.Loading -> {
                    Log.e(logTag, "Loading...")
                    binding.progressBar.toVisible()
                }
                is ResourceWrapper.Success -> {
                    Log.e(logTag, "successful =>=> ${it.data?.size}")
                    binding.progressBar.toGone()
                }
                else -> {
                    binding.progressBar.toGone()
                }
            }
        }
    }


}