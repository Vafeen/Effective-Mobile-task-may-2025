package com.example.common.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openLink(link: String) {
    startActivity(Intent(Intent.ACTION_VIEW, link.toUri()))
}