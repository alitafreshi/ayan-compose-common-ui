package ir.tafreshiali.compose_commom_ui.input.simple


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 * generic simple centralize text field
 * @param [value] [onValueChange] [placeHolder] [placeHolderColor] [shape] [inputType] are the text filed attributes*/

@ExperimentalComposeUiApi
@Composable
fun SimpleCentralizeTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeHolder: String,
    placeHolderColor: Color = Color.Gray,
    shape: Shape = MaterialTheme.shapes.medium,
    inputType: KeyboardType
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.topBar)
            .border(
                width = MaterialTheme.spacing.dividerLarge,
                color = Color.LightGray,
                shape = shape
            ),
        textStyle = MaterialTheme.typography.subtitle2.copy(
            textAlign = TextAlign.Center,
            color = Color.Black
        ),
        placeholder = {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.caption,
                    color = placeHolderColor,
                    textAlign = TextAlign.Center
                )
            }

        },
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        shape = shape,
        keyboardOptions =
        KeyboardOptions.Default.copy(
            keyboardType = inputType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }),

        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true
    )
}