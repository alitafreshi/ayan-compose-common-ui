package ir.tafreshiali.compose_commom_ui.dialog_bottom_sheet

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

fun Modifier.customDialogModifier(pos: CustomDialogPosition) = layout { measurable, constraints ->

    val placeable = measurable.measure(constraints);
    layout(constraints.maxWidth, constraints.maxHeight) {
        when (pos) {
            CustomDialogPosition.BOTTOM -> {
                placeable.place(0, constraints.maxHeight - placeable.height, 10f)
            }
            CustomDialogPosition.TOP -> {
                placeable.place(0, 0, 10f)
            }
        }
    }
}