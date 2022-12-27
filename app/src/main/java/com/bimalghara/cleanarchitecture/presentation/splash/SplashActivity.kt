package com.bimalghara.cleanarchitecture.presentation.splash

import android.content.Intent
import android.os.Bundle
import com.bimalghara.cleanarchitecture.databinding.ActivitySplashBinding
import com.bimalghara.cleanarchitecture.presentation.auth.AuthActivity
import com.bimalghara.cleanarchitecture.presentation.base.BaseActivity
import com.bimalghara.cleanarchitecture.utils.SPLASH_DELAY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun initViewBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    override fun observeViewModel() {
    }

    private fun navigateToMainScreen() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_DELAY.toLong())

            val nextScreenIntent = Intent(this@SplashActivity, AuthActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }
    }
}