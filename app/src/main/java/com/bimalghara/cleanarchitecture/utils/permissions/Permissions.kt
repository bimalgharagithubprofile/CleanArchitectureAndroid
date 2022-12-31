package com.bimalghara.cleanarchitecture.utils.permissions

import android.Manifest.permission.*

/**
 * Created by BimalGhara
 */

sealed class Permissions(vararg val permissions: String) {
    // Individual
    object Camera : Permissions(CAMERA)

    // Grouped
    object Location : Permissions(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
    object Storage : Permissions(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)

}