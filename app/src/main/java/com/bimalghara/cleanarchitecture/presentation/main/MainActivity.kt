package com.bimalghara.cleanarchitecture.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import com.bimalghara.cleanarchitecture.databinding.ActivityMainBinding
import com.bimalghara.cleanarchitecture.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by BimalGhara
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.test()
    }

    override fun observeViewModel() {
    }

}