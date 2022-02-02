package ir.tafreshiali.compose_commom_ui.responsiveness

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ir.tafreshiali.compose_commom_ui.responsiveness.Dimensions

/**
 * a composable  function to detect different screen width / height size
 *
 * @param comparator
 *
 * @param content related content that should display on screen for specific screen  size
 *
 * */

@Composable
fun MediaQuery(comparator: Dimensions.DimensionComparator, content: @Composable () -> Unit) {

    val screenWidth =
        LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density
    val screenHeight =
        LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density

    if (comparator.compare(screenWidth = screenWidth, screenHeight = screenHeight)) {
        content()
    }
}
