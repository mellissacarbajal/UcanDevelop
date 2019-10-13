package com.example.ucandevelop

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

object ToastUtil {

    fun show(context: Context, info: String) {
        if (!TextUtils.isEmpty(info)) {
            Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
        }
    }

    fun showLONG(context: Context, info: String) {
        if (!TextUtils.isEmpty(info)) {
            Toast.makeText(context, info, Toast.LENGTH_LONG).show()
        }
    }
}