package com.jorgealdana.ceibaapp.utils

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.jorgealdana.ceibaapp.R

object DialogUtils {
    fun showLoadingDialog(
        context: Context?,
    ): AlertDialog {
        return AlertDialog.Builder(
            context!!
        )
            .setView(R.layout.progress_dialog)
            .setCancelable(false)
            .create()
    }

    private fun doKeepDialog(dialog: Dialog) {
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = lp
    }
}