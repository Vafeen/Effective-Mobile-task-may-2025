package com.example.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.common.android.ui.CustomTheme
import com.example.common.android.ui.ThemeColor
import com.example.common.domain.navigation.Navigator
import com.example.onboarding.components.CourseCard
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parameterSetOf

@Composable
fun OnboardingScreen(navigator: Navigator) {
    val viewModel: OnboardingViewModel = koinViewModel { parameterSetOf(navigator) }
    val state by viewModel.state.collectAsState()
    val gridState = rememberLazyStaggeredGridState()
    LaunchedEffect(null) {
        if (state.courses.size > 4) gridState.scrollToItem(4)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 140.dp),
            text = stringResource(R.string.thousand_courses), fontSize = 28.sp,
            color = CustomTheme.colors.text
        )
        LazyHorizontalStaggeredGrid(
            state = gridState,
            modifier = Modifier
                .height(316.dp)
                .padding(top = 32.dp),
            rows = StaggeredGridCells.Fixed(4)
        ) {
            items(items = state.courses) { course ->
                course.CourseCard()
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 15.dp)
                .padding(bottom = 56.dp),
            onClick = { viewModel.toContinue() },
            colors = ButtonDefaults.buttonColors(containerColor = ThemeColor.green)
        ) {
            Text(stringResource(R.string.continue_str), color = Color.White)
        }
    }
}