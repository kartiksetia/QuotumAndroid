package com.quotum.quotum.quotum.utility

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.Patterns
import com.quotum.quotum.quotum.R


object Utils {
    lateinit var dialog: ProgressDialog

    fun showProgressBar(context: Context?) {
        dialog = ProgressDialog(context)
        val window = dialog.window
        window!!.setDimAmount(.1f)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.show()
        dialog.setContentView(R.layout.layout_progress_bar)
    }

    fun hideProgressBar() {
        dialog.cancel()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target)
            .matches()
    }
}

