package ir.tafreshiali.compose_commom_ui.timeLine

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Time Line Option
 * A data class for defining properties of time line view*/
data class TimeLineOption(
    @DrawableRes val circleIcon: Int,
    val circleSize: Dp = Dp.Unspecified,
    val circleColor: Color = Color.Unspecified,
    val lineColor: Color = Color.LightGray,
    val lineWidth: Dp = 2.dp
)