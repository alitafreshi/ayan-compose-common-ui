package ir.tafreshiali.compose_commom_ui.list.item.selectable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 * Generic Selectable Item
 * @param [elevation] [cardRoundedCorner] [backGroundColor] are the item properties
 * @param item of generic type
 * @param checkSate the stage of a single item of type [Boolean]
 * @param onCheckedChange a lambda function for reacting to the user changes like ( selecting / unselecting ) an item . it accepts a [Boolean] for reacting to the user changes and an item of generic type
 * @param content a lambda function of type [Composable] that let up stream apis implement their custom item views . it accepts a [Boolean] for reacting to the user changes and an item of generic type  */


@ExperimentalMaterialApi
@Composable
fun <T> SelectableItem(
    modifier: Modifier = Modifier,
    elevation: Dp = MaterialTheme.spacing.extraSmall,
    cardRoundedCorner: Shape = MaterialTheme.shapes.medium,
    backGroundColor: Color = MaterialTheme.colors.background,
    item: T,
    checkSate: Boolean = false,
    onCheckedChange: (checkSate: Boolean, item: T) -> Unit,
    content: @Composable (checkSate: Boolean, item: T) -> Unit
) {
    Card(
        backgroundColor = backGroundColor,
        elevation = elevation,
        shape = cardRoundedCorner,
        modifier = modifier.fillMaxWidth(),
        onClick = { onCheckedChange(!checkSate, item) }
    ) {
        content(checkSate, item)
    }
}