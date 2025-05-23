package com.infbyte.amuze.utils

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.core.net.toUri

fun Context.openWebLink(
    @StringRes linkRes: Int,
) {
    val link = getString(linkRes)
    startActivity(
        Intent(Intent.ACTION_VIEW)
            .setData(link.toUri()),
    )
}
