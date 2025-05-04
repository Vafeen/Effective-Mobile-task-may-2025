package com.example.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.common.android.ui.ThemeColor
import com.example.onboarding.Course

@Composable
internal fun Course.CourseCard() {
    Box(
        modifier = Modifier
            .rotate(degreesIncline)
            .padding(horizontal = 2.dp, vertical = 4.dp)
            .background(
                color = if (degreesIncline == 0f) Color.Black.copy(alpha = 0.8f) else ThemeColor.green,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 8.dp)
            .padding(horizontal = 24.dp)
            .zIndex(if (degreesIncline == 0f) 1f else 0f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}