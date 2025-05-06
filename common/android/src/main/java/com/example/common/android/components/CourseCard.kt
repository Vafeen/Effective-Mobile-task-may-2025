package com.example.common.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.common.android.ui.CustomTheme
import com.example.common.android.ui.ThemeColor
import com.example.common.domain.models.Course
import com.example.common.utils.formatDateToRussian


@Composable
fun Course.CourseCard(
    modifier: Modifier = Modifier,
    onUpdateFavourites: (Boolean) -> Unit = {},
    onModeDetails: () -> Unit = {}
) {
    Card(
        modifier.padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CustomTheme.colors.cardColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(114.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Red)
                .padding(8.dp)
        ) {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Black.copy(alpha = 0.3f)),
                onClick = { onUpdateFavourites(!hasLike) },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(if (hasLike) R.drawable.favourites_filled else R.drawable.favourites),
                    contentDescription = stringResource(R.string.add_to_favourites),
                    tint = Color.White
                )
            }
            Row(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.2f))
                ) {
                    Row(
                        modifier = Modifier.padding(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(R.drawable.star),
                            contentDescription = stringResource(R.string.rate),
                            tint = ThemeColor.green
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = rate,
                            fontSize = 14.sp,
                            color = CustomTheme.colors.text,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.2f))
                ) {
                    Text(
                        modifier = Modifier.padding(6.dp),
                        text = startDate.formatDateToRussian(),
                        fontSize = 14.sp,
                        color = CustomTheme.colors.text,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 12.dp),
        ) {
            Text(
                text = title,
                color = CustomTheme.colors.text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = text,
                color = CustomTheme.colors.text,
                fontSize = 12.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$price ₽",
                    color = CustomTheme.colors.text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onModeDetails() }) {
                    Text(
                        text = stringResource(R.string.more_details),
                        color = ThemeColor.green,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(R.drawable.more_details),
                        contentDescription = stringResource(R.string.more_details),
                        tint = ThemeColor.green,
                    )
                }
            }
        }

    }
}