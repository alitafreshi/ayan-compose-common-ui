package ir.tafreshiali.compose_commom_ui.responsiveness

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ir.tafreshiali.compose_commom_ui.responsiveness.Dimensions

/**
 * a modifier extension function that َallow consumer to specify ui elements / widgets features associated with each screen size
 * @param comparator
 * @param modifier
 * */
@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.mediaQuery(
    comparator: Dimensions.DimensionComparator,
    modifier: Modifier
): Modifier = composed {

    val screenWidth =
        LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density
    val screenHeight =
        LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density

    if (comparator.compare(screenWidth = screenWidth, screenHeight = screenHeight)) {
        this.then(modifier)
    } else this
}