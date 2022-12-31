package com.bimalghara.cleanarchitecture.presentation.base

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.bimalghara.cleanarchitecture.utils.SingleEvent
import com.bimalghara.cleanarchitecture.utils.showToast
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {
    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    /*fun observeError(rootLayout: ConstraintLayout, event: LiveData<SingleEvent<Any>>) {
        rootLayout.showToast(this, event, Snackbar.LENGTH_LONG)
    }*/
}