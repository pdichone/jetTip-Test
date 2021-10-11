package com.bawp.bmi_copy.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bawp.jettip_test.MyApp



@Composable
fun RoundedCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.background,
    elevation: Dp = 4.dp,
    content: @Composable() () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = color,
        elevation = elevation
    ) {
        content()
    }
}
val textStyle = TextStyle(
    fontSize = 16.sp,
    color = Color.Black.copy(alpha = 0.8f)
                         )
@Preview
@Composable
private fun RoundedCardPreview() {
    MyApp {
        RoundedCard {
            Text(text = "RoundedCard", modifier = Modifier.padding(8.dp),
                style = textStyle )
        }
    }
}
