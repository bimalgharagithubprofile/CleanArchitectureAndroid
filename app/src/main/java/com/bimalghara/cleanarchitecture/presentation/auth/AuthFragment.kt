package com.bimalghara.cleanarchitecture.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    private val logTag = javaClass.simpleName

    private val authViewModel: AuthViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAuthBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            binding.root.hideKeyboard()

            authViewModel.authenticate(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    override fun observeViewModel() {
        observeError(authViewModel.errorSingleEvent)

        observe(authViewModel.registerOrLoginLiveData) {
            Log.e(logTag, "observe registerOrLoginLiveData | $it")
            binding.txtResult.text = it.toString()
            when (it) {
                is ResourceWrapper.Loading -> {
                    Log.e(logTag, "Loading...")
                    binding.progressBar.toVisible()
                }
                is ResourceWrapper.Success -> {
                    Log.e(logTag, "successful =>=> id=${it.data}")
                    binding.progressBar.toGone()
                    navigateToMainScreen()
                }
                else -> {
                    binding.progressBar.toGone()
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