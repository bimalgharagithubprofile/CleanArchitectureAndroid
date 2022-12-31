package com.bimalghara.cleanarchitecture.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import com.bimalghara.cleanarchitecture.utils.SingleEvent
import com.bimalghara.cleanarchitecture.utils.showToast
import com.google.android.material.snackbar.Snackbar

/**
 * Created by BimalGhara
 */


abstract class BaseActivity : ComponentActivity() {

    abstract fun observeViewModel()
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        observeViewModel()
    }

    fun observeError(rootLayout: ConstraintLayout, event: LiveData<SingleEvent<Any>>) {
        rootLayout.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}
