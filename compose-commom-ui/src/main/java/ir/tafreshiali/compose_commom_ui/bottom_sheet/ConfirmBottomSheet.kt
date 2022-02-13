package ir.tafreshiali.compose_commom_ui.bottom_sheet

import androidx.compose.runtime.Composable

/**
 * confirm bottom sheet [Composable]
 * @param content a [Composable] that enables up stream to decide which view matches their use case
 */

@Composable
fun ConfirmBottomSheet(
    content: @Composable () -> Unit
) {
    content()
}

