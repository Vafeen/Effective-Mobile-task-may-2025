package com.example.common.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.common.android.ui.CustomTheme

@Composable
fun ErrorComponent(modifier: Modifier = Modifier, errorMessage: String, refresh: () -> Unit) {
    Column(
        modifier.clickable { refresh() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(50.dp),
                painter = painterResource(R.drawable.error),
                contentDescription = stringResource(R.string.error),
                tint = CustomTheme.colors.text
            )
            Text(errorMessage, color = CustomTheme.colors.text, fontSize = 20.sp)
        }
    }
}