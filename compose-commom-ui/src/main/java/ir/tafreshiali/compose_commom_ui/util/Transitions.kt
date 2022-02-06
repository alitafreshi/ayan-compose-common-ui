package ir.tafreshiali.compose_commom_ui.util

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween

/** [slideOutHorizontally] Animation + [fadeOut] Animation*/

@ExperimentalAnimationApi
fun slideOutHorizontally(width: Int) = slideOutHorizontally(
    targetOffsetX = { width },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
) + fadeOut(animationSpec = tween(300))


/** [slideInHorizontally] Animation + [fadeIn] Animation*/
@ExperimentalAnimationApi
fun slideInHorizontally(width: Int) = slideInHorizontally(
    initialOffsetX = { width },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
) + fadeIn(animationSpec = tween(300))





