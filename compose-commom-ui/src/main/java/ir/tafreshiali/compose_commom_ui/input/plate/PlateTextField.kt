package ir.tafreshiali.compose_commom_ui.input.plate

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

/** in this stage we don't need this Composable*/

/*@ExperimentalComposeUiApi
@Composable
fun PlateInputDecoration(
    modifier: Modifier = Modifier,
    inputType: KeyboardType,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
    onPlateInputValue01Change: (String) -> Unit,
    onPlateInputValue02Change: (String) -> Unit,
    onPlateInputValue03Change: (String) -> Unit,
    plateInputValue01: String,
    plateInputValue02: String,
    plateInputValue03: String,
    plateInputValue04: String,
    appConfigOutput: AppConfigResponse? = null,
    expanded: Boolean = false,
    onOpenMenuClick: () -> Unit,
    onMenuItemSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {

            OutlinedTextField(
                modifier = modifier
                    .weight(1f),
                value = plateInputValue01,
                onValueChange = {
                    Log.d("TAG", "input 1 length is =${it.length} ")
                    if ((plateInputValue01.trim().length <= 1) || (it.trim().length <= 1)) {
                        onPlateInputValue01Change(it)
                    } else {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                },
                textStyle = MaterialTheme.typography.h5.copy(
                    textAlign = TextAlign.Center, color = Color.Black
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = inputType,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Row(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(
                        MaterialTheme.spacing.dividerLarge,
                        Color.LightGray,
                        MaterialTheme.shapes.small
                    )
                    .clickable(onClick = onOpenMenuClick)
                    .padding(
                        vertical = MaterialTheme.spacing.extraSmall,
                        horizontal = MaterialTheme.spacing.default
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = plateInputValue02,
                    maxLines = 1
                )

                if (appConfigOutput != null) {
                    DropDown(
                        plateList = appConfigOutput.VehiclePlateLetterMappingsVersion01,
                        expanded = expanded,
                        onMenuItemSelected = onMenuItemSelected,
                        onDismissRequest = onDismissRequest
                    )
                }
            }


            OutlinedTextField(
                modifier = modifier
                    .weight(1.3f),
                value = plateInputValue03,
                onValueChange = {
                    if ((plateInputValue03.trim().length <= 2) || (it.trim().length <= 2)) {
                        onPlateInputValue02Change(it)
                    } else {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                },
                textStyle = MaterialTheme.typography.h5.copy(
                    textAlign = TextAlign.Center, color = Color.Black
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = inputType,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Divider(
                modifier = modifier
                    .height(MaterialTheme.spacing.dividerExtraLarge)
                    .weight(0.1f),
                color = Color.Gray
            )


            OutlinedTextField(
                modifier = modifier
                    .weight(1.7f),
                value = plateInputValue04,
                onValueChange = {
                    if ((plateInputValue04.trim().length <= 1) || (it.trim().length <= 1)) {
                        onPlateInputValue03Change(it)
                    } else {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                },
                trailingIcon = {
                    Text(
                        text = "ایران",
                        modifier = Modifier
                            .rotate(90f),
                        style = MaterialTheme.typography.caption,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                },
                textStyle = MaterialTheme.typography.h5.copy(
                    textAlign = TextAlign.Center, color = Color.Black
                ),

                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = inputType,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}


@Composable
private fun DropDown(
    modifier: Modifier = Modifier,
    plateList: List<KeyValue>,
    expanded: Boolean,
    onMenuItemSelected: (selectedItem: String) -> Unit,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        modifier = modifier.wrapContentSize(),
        offset = DpOffset(0.dp, MaterialTheme.spacing.large),
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {

        plateList.forEachIndexed { index, plate ->
            DropdownMenuItem(onClick = { onMenuItemSelected(plate.Value ?: "") }
            ) {
                Text(
                    text = "${plate.Value}",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = modifier.weight(1f)
                )
            }
        }
    }
}*/
