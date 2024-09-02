package com.infbyte.amuze.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes

fun Context.openWebLink(@StringRes linkRes: Int) {
    val link = getString(linkRes)
    startActivity(
        Intent(Intent.ACTION_VIEW)
            .setData(Uri.parse(link))
    )
}
