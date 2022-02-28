package ir.tafreshiali.compose_commom_ui.input.simple

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

@ExperimentalComposeUiApi
@Composable
fun SimpleInput(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeHolder: String,
    shape: Shape = MaterialTheme.shapes.medium,
    borderStroke: BorderStroke = BorderStroke(
        width = MaterialTheme.spacing.unspecified,
        color = MaterialTheme.colors.onBackground
    ),
    inputBackGroundColor: Color = Color.Transparent,
    inputTextStyle: TextStyle = MaterialTheme.typography.body1.copy(
        textAlign = TextAlign.Center,
        color = Color.Black
    ),
    placeHolderTextStyle: TextStyle = MaterialTheme.typography.body1.copy(
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
        fontWeight = FontWeight.Bold
    ),
    cursorBrushColor: Brush = SolidColor(MaterialTheme.colors.primary),
    maxLines: Int = 1,
    inputType: KeyboardType,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        modifier = modifier
            .background(
                color = inputBackGroundColor,
                shape = shape
            )
            .border(border = borderStroke, shape = shape)
            .fillMaxWidth(),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        cursorBrush = cursorBrushColor,
        textStyle = inputTextStyle,
        decorationBox = { innerTextField ->
            Row(
                modifier.padding(vertical = MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                if (leadingIcon != null) leadingIcon()

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeHolder,
                            style = placeHolderTextStyle
                        )
                    }
                    innerTextField()
                }

                if (trailingIcon != null) trailingIcon()
            }
        },
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
        maxLines = maxLines
    )
}

