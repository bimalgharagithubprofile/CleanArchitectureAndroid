package com.bimalghara.cleanarchitecture.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bimalghara.cleanarchitecture.R
import com.bimalghara.cleanarchitecture.databinding.FragmentAuthBinding
import com.bimalghara.cleanarchitecture.presentation.base.BaseFragment
import com.bimalghara.cleanarchitecture.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class AuthFragment : BaseFragment() {
    private val TAG = javaClass.simpleName

    private lateinit var binding: FragmentAuthBinding
    private val authViewModel: AuthViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)

        binding.btnRegister.setOnClickListener {
            binding.root.hideKeyboard()

            authViewModel.authenticate(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        return binding.root
    }

    override fun observeViewModel() {
        //observeError(binding.container, authViewModel.errorSingleEvent)

        observe(authViewModel.registerOrLoginLiveData) {
            Log.e(TAG, "observe registerOrLoginLiveData | ${it.toString()}")
            binding.txtResult.text = it.toString()
            when (it) {
                is ResourceWrapper.Loading -> {
                    Log.e(TAG, "Loading...")
                    binding.progressBar.toVisible()
                }
                is ResourceWrapper.Success -> {
                    Log.e(TAG, "successful =>=> id=${it.data}")
                    binding.progressBar.toGone()
                    navigateToMainScreen()
                }
                else -> {
                    Log.e(TAG, "error ==>> ${it.errorCode}")
                    binding.progressBar.toGone()
                    authViewModel.showError(it.errorCode)
                }
            }
        }
    }

    private fun navigateToMainScreen() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_DELAY.toLong())

            if(findNavController().currentDestination?.id == R.id.authFragment)
                findNavController().navigate(R.id.action_authFragment_to_homeFragment)
        }
    }
}