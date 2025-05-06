package com.example.sign_in.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android.R
import com.example.common.android.ui.CustomTheme
import com.example.common.android.ui.ThemeColor

@Composable
fun ActionsComponent(
    modifier: Modifier = Modifier,
    onSignUpClick: (() -> Unit)? = null,
    onForgotPasswordClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(R.string.no_account), color = CustomTheme.colors.text)
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.signing_up), color = ThemeColor.green,
                modifier = Modifier.let {
                    if (onSignUpClick != null) it.clickable(onClick = onSignUpClick) else it
                })
        }
        Text(
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.forgot_password), color = ThemeColor.green,
            modifier = Modifier
                .let {
                    if (onForgotPasswordClick != null) it.clickable(onClick = onForgotPasswordClick) else it
                }
                .padding(top = 8.dp)
        )
    }
}