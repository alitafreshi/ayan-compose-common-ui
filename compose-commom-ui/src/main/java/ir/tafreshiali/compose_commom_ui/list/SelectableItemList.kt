package ir.tafreshiali.compose_commom_ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.tafreshiali.compose_commom_ui.list.item.selectable.SelectableItem
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

/**
 * Generic Selectable Item list
 * @param modifier
 * @param itemList a list of objects of generic type
 * @param selectedItemList a list of objects that user select them of generic type
 * @param onCheckedChange a lambda function for reacting to the user changes like ( selecting / unselecting ) an item . it accepts a [Boolean] for reacting to the user changes and an item of generic type
 * @param itemContent a lambda function of type [Composable] that let up stream apis implement their custom item views . it accepts a [Boolean] for reacting to the user changes and an item of generic type  */

@ExperimentalMaterialApi
@Composable
fun <T> SelectableItemList(
    modifier: Modifier = Modifier,
    itemList: List<T>,
    selectedItemList: List<T>,
    onCheckedChange: (checkSate: Boolean, item: T) -> Unit,
    itemContent: @Composable (checkSate: Boolean, item: T) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.default
        ),
        contentPadding = PaddingValues(vertical = MaterialTheme.spacing.large)
    ) {

        itemsIndexed(itemList) { _, bill ->
            SelectableItem(
                item = bill,
                checkSate = selectedItemList.contains(bill),
                onCheckedChange = onCheckedChange,
                content = itemContent
            )
        }
    }
}