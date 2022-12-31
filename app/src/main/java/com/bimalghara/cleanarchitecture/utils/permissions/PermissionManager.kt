package com.bimalghara.cleanarchitecture.utils.permissions

import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bimalghara.cleanarchitecture.R
import java.lang.ref.WeakReference

/**
 * Created by BimalGhara
 */

class PermissionManager {

    private var _weakReference: WeakReference<Any>? = null //[Fragment, ComponentActivity]


    private val requiredPermissions = mutableListOf<Permissions>()
    private var rationale: String? = null
    private var callback: (Boolean) -> Unit = {}


    /* Expose Fun(s) */
    fun from(activity: ComponentActivity): PermissionManager {
        _weakReference = WeakReference(activity)
        return this
    }
    fun from(fragment: Fragment): PermissionManager {
        _weakReference = WeakReference(fragment)
        return this
    }

    fun request(vararg permissions: Permissions): PermissionManager {
        requiredPermissions.addAll(permissions)
        return this
    }

    fun rationale(description: String): PermissionManager {
        rationale = description
        return this
    }

    fun checkPermission(callback: (Boolean) -> Unit) {
        this.callback = callback
        handlePermissionRequest()
    }


    /* Helpers Fun(s) */
    private fun handlePermissionRequest() {
        _weakReference?.get()?.let {
            when {
                areAllPermissionsGranted(it) -> sendPositiveResult()
                shouldShowPermissionRationale(it) -> displayRationale(it)
                else -> requestPermissions()
            }
        }
    }

    private fun requestPermissions() {
        permissionCheck()?.launch(getPermissionList())
    }

    private fun permissionCheck(): ActivityResultLauncher<Array<String>>? {
        return when (val ui = _weakReference?.get()) {
            is Fragment -> {
                ui.registerForActivityResult(RequestMultiplePermissions()) { }
            }
            is ComponentActivity -> {
                ui.registerForActivityResult(RequestMultiplePermissions()) { }
            }
            else -> null
        }
    }

    private fun sendPositiveResult() {
        sendResultAndCleanUp(getPermissionList().associate { it to true })
    }

    private fun displayRationale(ui: Any) {
        val context = when (ui) {
            is ComponentActivity -> ui.applicationContext
            is Fragment -> ui.requireContext()
            else -> null
        }

        context?.let {
            AlertDialog.Builder(it)
                .setTitle(it.getString(R.string.dialog_permission_title))
                .setMessage(rationale ?: it.getString(R.string.dialog_permission_default_message))
                .setCancelable(false)
                .setPositiveButton(it.getString(R.string.dialog_permission_button_positive)) { _, _ ->
                    requestPermissions()
                }
                .show()
        }
    }

    private fun sendResultAndCleanUp(grantResults: Map<String, Boolean>) {
        callback(grantResults.all { it.value })
        cleanUp()
    }

    private fun cleanUp() {
        requiredPermissions.clear()
        rationale = null
        callback = {}
    }

    private fun areAllPermissionsGranted(ui: Any) = requiredPermissions.all { it.isGranted(ui) }

    private fun Permissions.isGranted(ui: Any) =
        permissions.all { hasPermission(ui, it) }

    private fun shouldShowPermissionRationale(ui: Any) =
        requiredPermissions.any { it.requiresRationale(ui) }

    private fun Permissions.requiresRationale(ui: Any) : Boolean {
        return when (ui) {
            is ComponentActivity -> permissions.any { ui.shouldShowRequestPermissionRationale(it) }
            is Fragment -> permissions.any { ui.shouldShowRequestPermissionRationale(it) }
            else -> false
        }
    }

    private fun hasPermission(ui: Any, permission: String): Boolean {
        return when (ui) {
            is ComponentActivity -> ContextCompat.checkSelfPermission(ui.applicationContext, permission) == PackageManager.PERMISSION_GRANTED
            is Fragment -> ContextCompat.checkSelfPermission(ui.requireContext(), permission) == PackageManager.PERMISSION_GRANTED
            else -> false
        }
    }

    private fun getPermissionList() =
        requiredPermissions.flatMap { it.permissions.toList() }.toTypedArray()
}