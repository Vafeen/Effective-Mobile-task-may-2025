package com.example.common.android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.android.ui.CustomTheme

@Composable
fun FieldWithHintComponent(
    text: String,
    topName: String,
    hint: String,
    onTextChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions? = null,
    visualTransformation: VisualTransformation? = null,
) {
    Column {
        Text(topName, color = CustomTheme.colors.text, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = CustomTheme.colors.cardColor,
                unfocusedContainerColor = CustomTheme.colors.cardColor,
                focusedTextColor = CustomTheme.colors.text,
                unfocusedTextColor = CustomTheme.colors.text,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = text,
            placeholder = { Text(text = hint) },
            onValueChange = onTextChanged,
            textStyle = TextStyle(fontSize = 14.sp),
            keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
            visualTransformation = visualTransformation ?: VisualTransformation.None
        )
    }
}