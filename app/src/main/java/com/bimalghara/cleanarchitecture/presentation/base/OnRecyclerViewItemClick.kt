package com.bimalghara.cleanarchitecture.presentation.base

interface OnRecyclerViewItemClick<T> {
    fun onItemClick(position: Int, data: T)
}