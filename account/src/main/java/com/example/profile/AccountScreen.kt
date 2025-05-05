package com.example.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun AccountScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(stringResource(com.example.android.R.string.account_screen))
    }

}