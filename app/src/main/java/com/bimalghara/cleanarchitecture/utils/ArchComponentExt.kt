package com.bimalghara.cleanarchitecture.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by BimalGhara
 */

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<SingleEvent<T>>, action: (t: SingleEvent<T>) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}
