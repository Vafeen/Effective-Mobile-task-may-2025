package com.example.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.common.android.components.CourseCard
import com.example.common.android.components.ErrorComponent
import com.example.common.android.ui.CustomTheme
import com.example.common.android.ui.ThemeColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        isRefreshing = state.isLoading,
        state = pullRefreshState,
        onRefresh = { viewModel.fetchData() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            state.error?.let {
                ErrorComponent(
                    modifier = Modifier.fillMaxSize(),
                    errorMessage = it,
                    refresh = { viewModel.fetchData() }
                )
            }
            if (state.error == null && !state.isLoading) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextField(
                        readOnly = true, // по тз этот элемент не функционирует
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.search),
                                contentDescription = stringResource(R.string.search),
                                tint = CustomTheme.colors.text,
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = CustomTheme.colors.cardColor,
                            unfocusedContainerColor = CustomTheme.colors.cardColor,
                            focusedTextColor = CustomTheme.colors.text,
                            unfocusedTextColor = CustomTheme.colors.text,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        value = state.searchRequest,
                        onValueChange = viewModel::updateSearchRequest
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        enabled = false, // по тз этот элемент не функционирует
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(CustomTheme.colors.cardColor)
                            .size(56.dp),
                        onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.filter),
                            contentDescription = stringResource(R.string.filter),
                            tint = CustomTheme.colors.text,
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(vertical = 16.dp)
                        .clickable { viewModel.sortByPublishDate() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.sortType,
                        color = ThemeColor.green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(R.drawable.sorting),
                        contentDescription = stringResource(R.string.sorting),
                        tint = ThemeColor.green
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(items = state.courses, key = { it.id }) { course ->
                        course.CourseCard(onUpdateFavourites = { viewModel.updateFavourites(course) })
                    }
                }
            }
        }
    }
}