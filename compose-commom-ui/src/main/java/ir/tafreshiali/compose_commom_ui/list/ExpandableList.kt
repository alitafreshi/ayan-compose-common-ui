package ir.tafreshiali.compose_commom_ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


/**
 * Expandable List [Composable]
 * @param itemList a list of objects of generic type
 * @param generalInfo a lambda function of type [Composable] that let up stream apis implement their custom view for info's
 * @param stickyHeaderListInfo a lambda function of type [Composable] that let up stream apis implement their custom view for a header list
 * @param itemContent a lambda function of type [Composable] that let up stream apis implement their custom item views . it accepts a [Boolean] for reacting to the user changes and an item of generic type*/

@ExperimentalFoundationApi
@Composable
fun <T> ExpandableList(
    modifier: Modifier = Modifier,
    itemList: List<T>,
    generalInfo: @Composable () -> Unit,
    stickyHeaderListInfo: @Composable () -> Unit,
    itemContent: @Composable (item: T) -> Unit
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {

        item {
            generalInfo()
        }

        stickyHeader {
            stickyHeaderListInfo()
        }

        itemsIndexed(itemList) { _, item ->
            itemContent(item)
        }
    }
}