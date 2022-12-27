package com.bimalghara.cleanarchitecture.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bimalghara.cleanarchitecture.R
import com.bimalghara.cleanarchitecture.databinding.ActivityAuthBinding
import com.bimalghara.cleanarchitecture.presentation.base.BaseActivity
import com.bimalghara.cleanarchitecture.presentation.main.MainActivity
import com.bimalghara.cleanarchitecture.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity : BaseActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var binding: ActivityAuthBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun initViewBinding() {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnRegister.setOnClickListener {
            binding.root.hideKeyboard()

            authViewModel.authenticate(
                binding.etUsername.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    override fun observeViewModel() {
        observeError(binding.root, authViewModel.errorSingleEvent)

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

            val nextScreenIntent = Intent(this@AuthActivity, MainActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }
    }
}