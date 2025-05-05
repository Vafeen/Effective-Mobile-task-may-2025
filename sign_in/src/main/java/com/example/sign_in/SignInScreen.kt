package com.example.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.common.android.components.FieldWithHintComponent
import com.example.common.android.ui.CustomTheme
import com.example.common.android.ui.ThemeColor
import com.example.common.domain.navigation.Navigator
import com.example.sign_in.components.ActionsComponent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SignInScreen(navigator: Navigator) {
    val context = LocalContext.current
    val viewModel: SignInViewModel = koinViewModel { parametersOf(navigator) }
    val state by viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CustomTheme.colors.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                fontSize = 28.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 140.dp, bottom = 28.dp),
                color = CustomTheme.colors.text
            )
            FieldWithHintComponent(
                text = state.email,
                topName = stringResource(R.string.email),
                hint = stringResource(R.string.test_email),
                onTextChanged = viewModel::processEmail,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            Spacer(modifier = Modifier.height(16.dp))
            FieldWithHintComponent(
                text = state.password,
                topName = stringResource(R.string.password),
                hint = stringResource(R.string.enter_password),
                onTextChanged = viewModel::processPassword,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                enabled = state.isButtonSignInActive,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 24.dp),
                onClick = viewModel::signIn,
                colors = ButtonDefaults.buttonColors(containerColor = ThemeColor.green)
            ) {
                Text(stringResource(R.string.sign_in), color = Color.White)
            }
            ActionsComponent(modifier = Modifier.padding(top = 16.dp))
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 32.dp),
                thickness = 1.dp,
                color = Color.Gray
            )
            Row(
                modifier = Modifier.padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.openVk(context) },
                    colors = ButtonDefaults.buttonColors(containerColor = ThemeColor.vkColor)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.vk),
                        contentDescription = stringResource(R.string.vk)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    onClick = { viewModel.openOk(context) },
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            brush = Brush.verticalGradient(ThemeColor.okColors),
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ok),
                        contentDescription = stringResource(R.string.ok)
                    )
                }
            }
        }
    }
}