package ir.tafreshiali.compose_commom_ui.dailog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 * GOAL = having a generic dialog for performing actions like (delete / edit etc)
 * @param [backGroundColor] [contentColor] [shape] [confirmButtonColor] [dismissButtonColor] are dialog attributes
 * @param [onCancel] [onDismiss] [onOk] are dialog actions
 * @param content main content of dialog of type [Composable]
 * @param [dismissOnBackPress] [dismissOnClickOutside] are the dialog properties
 * @param header header of the dialog of type [Composable]
 * */


@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    header: @Composable () -> Unit,
    okButtonText: String = "تایید",
    cancelButtonText: String = "حذف",
    backGroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.onBackground,
    shape: CornerBasedShape = MaterialTheme.shapes.medium,
    confirmButtonColor: Color = MaterialTheme.colors.primary,
    dismissButtonColor: Color = MaterialTheme.colors.error,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onOk: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = header,
        text = content,
        buttons = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        end = MaterialTheme.spacing.default,
                        bottom = MaterialTheme.spacing.default
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onOk
                ) {
                    Text(
                        text = okButtonText,
                        style = MaterialTheme.typography.button,
                        color = confirmButtonColor
                    )
                }

                TextButton(
                    onClick = onCancel

                ) {
                    Text(
                        text = cancelButtonText,
                        style = MaterialTheme.typography.button,
                        color = dismissButtonColor
                    )
                }
            }
        },
        backgroundColor = backGroundColor,
        contentColor = contentColor,
        shape = shape,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside
        )
    )
}

/**
 * Header / Toolbar of dialog
 * @param [title] [onCloseClick] */
@Composable
fun GenericDialogTitle(
    modifier: Modifier = Modifier,
    title: String,
    onCloseClick: () -> Unit
) {

    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = modifier.align(alignment = Alignment.Center),
            text = title,
            style = MaterialTheme.typography.h5
        )

        IconButton(
            modifier = modifier
                .then(modifier.size(MaterialTheme.spacing.iconMedium))
                .align(alignment = Alignment.CenterStart),
            onClick = onCloseClick,
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close button"
            )
        }
    }
}