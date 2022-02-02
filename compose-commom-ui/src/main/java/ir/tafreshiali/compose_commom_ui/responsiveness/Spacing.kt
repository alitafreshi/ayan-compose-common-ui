package ir.tafreshiali.compose_commom_ui.responsiveness

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Here we define custom [MaterialTheme] property and pass it down to the all ( hierarchies / nodes ) */
data class Spacing(
    val unspecified: Dp = 0.dp,
    val default: Dp = 15.dp,
    val dividerExtraSmall: Dp = 0.25.dp,
    val dividerSmall: Dp = 0.5.dp,
    val dividerMedium: Dp = 0.75.dp,
    val dividerLarge: Dp = 1.dp,
    val dividerExtraLarge: Dp = 2.dp,
    val iconSmall: Dp = 18.dp,
    val iconMedium: Dp = 24.dp,
    val menuIconMedium: Dp = 30.dp,
    val extraSmall: Dp = 5.dp,
    val small: Dp = 10.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 20.dp,
    val extraLarge: Dp = 25.dp,
    val topBar: Dp = 56.dp,
    val defaultItemSize: Dp = 85.dp,
    val defaultGridCells: Dp = 110.dp,
    val bottomSheetDefault: Dp = 150.dp
)

val localSpacing = compositionLocalOf { Spacing() }
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = localSpacing.current

