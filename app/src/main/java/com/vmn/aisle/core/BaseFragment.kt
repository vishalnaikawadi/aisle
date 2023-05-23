package com.vmn.aisle.core

import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment

/**
 * abstract base class for common operations.
 */
abstract class BaseFragment : Fragment() {

    fun showShortMessage(str: String?) {
        if (!str.isNullOrBlank()) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * To handle back in case of some fragments
     */
    fun onBackPressed(block: () -> Unit) {
        activity?.onBackPressedDispatcher?.addCallback {
            block()
        }
    }

}