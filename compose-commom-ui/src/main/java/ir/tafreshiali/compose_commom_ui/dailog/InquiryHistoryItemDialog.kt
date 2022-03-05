package ir.tafreshiali.compose_commom_ui.dailog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.tafreshiali.compose_commom_ui.input.simple.SimpleInput
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

@ExperimentalComposeUiApi
@Composable
fun InquiryHistoryItemDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    value: String,
    nationalCode: String,
    onValueChange: (String) -> Unit,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    saveButtonColor: Color = Color.Green,
    inputHeader: String,
    inputPlaceHolder: String,
    appName: String,
    saveButtonTitle: String = "ذخیره",
    deleteButtonTextTitle: String = "حذف",
    icon: @Composable () -> Unit,
    inputBackgroundColor: Color = Color.LightGray,
    contentColor: Color = contentColorFor(backgroundColor),
    onSaveButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(
            modifier = modifier,
            shape = shape,
            color = backgroundColor,
            contentColor = contentColor
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.default),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DialogHeaderContent(onCloseClick = onDismissRequest)

                DialogMainContent(
                    onValueChange = onValueChange,
                    value = value,
                    nationalCode = nationalCode,
                    inputPlaceHolder = inputPlaceHolder,
                    inputHeader = inputHeader,
                    appName = appName,
                    inputBackgroundColor = inputBackgroundColor,
                    icon = icon
                )

                DialogBottomContent(
                    onSaveButtonClick = onSaveButtonClick,
                    onDeleteButtonClick = onDeleteButtonClick,
                    saveButtonColor = saveButtonColor,
                    saveButtonTitle = saveButtonTitle,
                    deleteButtonTextTitle = deleteButtonTextTitle
                )
            }
        }
    }
}


@Composable
private fun DialogHeaderContent(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Icon(
            modifier = modifier.clickable(onClick = onCloseClick),
            imageVector = Icons.Default.Close,
            contentDescription = "close button"
        )
    }
}


@ExperimentalComposeUiApi
@Composable
private fun DialogMainContent(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    nationalCode: String,
    inputHeader: String,
    inputPlaceHolder: String,
    appName: String,
    inputBackgroundColor: Color = Color.LightGray,
    icon: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
    ) {

        icon()

        Text(
            modifier = modifier.fillMaxWidth(),
            text = "$appName - $nationalCode",
            style = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center)
        )

        Text(
            modifier = modifier.fillMaxWidth(),
            text = inputHeader,
            style = MaterialTheme.typography.subtitle2.copy(textAlign = TextAlign.Start)
        )

        SimpleInput(
            onValueChange = onValueChange,
            value = value,
            placeHolder = inputPlaceHolder,
            inputType = KeyboardType.Text,
            inputBackGroundColor = inputBackgroundColor
        )
    }
}


@ExperimentalComposeUiApi
@Composable
private fun DialogBottomContent(
    modifier: Modifier = Modifier,
    saveButtonTitle: String = "ذخیره",
    saveButtonColor: Color = Color.Green,
    deleteButtonTextTitle: String = "حذف",
    onSaveButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {

        Button(
            modifier = modifier.weight(1.5f),
            onClick = {
                focusManager.clearFocus()
                keyboardController?.hide()
                onSaveButtonClick()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = saveButtonColor)
        ) {
            Text(
                text = saveButtonTitle,
                style = MaterialTheme.typography.button.copy(textAlign = TextAlign.Center)
            )
        }

        Button(
            modifier = modifier.weight(1f),
            onClick = {
                focusManager.clearFocus()
                keyboardController?.hide()
                onDeleteButtonClick()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
        ) {
            Text(
                text = deleteButtonTextTitle,
                style = MaterialTheme.typography.button.copy(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onError
                )
            )
        }
    }
}