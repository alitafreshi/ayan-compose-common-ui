package ir.tafreshiali.compose_commom_ui.dialog_bottom_sheet

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

@Composable
fun BottomSheetDialog(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    backGroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.onBackground,
    shape: RoundedCornerShape = RoundedCornerShape(
        topStart = MaterialTheme.spacing.default,
        topEnd = MaterialTheme.spacing.default
    ),
    onDismiss: () -> Unit,
    bottomContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = header,
        text = content,
        buttons = bottomContent,
        backgroundColor = backGroundColor,
        contentColor = contentColor,
        shape = shape,
        properties = properties
    )
}