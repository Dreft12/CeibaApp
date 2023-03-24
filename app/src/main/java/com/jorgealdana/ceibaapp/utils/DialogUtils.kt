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

}