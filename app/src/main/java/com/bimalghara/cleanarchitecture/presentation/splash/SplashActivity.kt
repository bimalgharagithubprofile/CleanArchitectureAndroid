package com.bimalghara.cleanarchitecture.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bimalghara.cleanarchitecture.R
import com.bimalghara.cleanarchitecture.databinding.ActivitySplashBinding
import com.bimalghara.cleanarchitecture.presentation.base.BaseActivity
import com.bimalghara.cleanarchitecture.presentation.main.MainActivity
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

            val nextScreenIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }
    }
}