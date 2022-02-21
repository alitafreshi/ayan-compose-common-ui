package ir.tafreshiali.compose_commom_ui.list.item.inquiry

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ir.tafreshiali.ayan_core.inquiry_history.InquiryHistoryResponse
import ir.tafreshiali.compose_commom_ui.R
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing
import ir.tafreshiali.compose_commom_ui.util.noRippleClickable

/** Inquiry History Item [Composable]
 * @param modifier
 * @param inquiryItem of type [InquiryHistoryResponse]
 * @param dividerPadding enable setting some padding for bottom divider
 * @param [onFavoriteClick] [onShowDialogClick] [onItemClick] are the ( actions / events) that user can fire off*/

@Composable
fun InquiryHistoryItem(
    modifier: Modifier = Modifier,
    inquiryItem: InquiryHistoryResponse,
    dividerPadding: PaddingValues = PaddingValues(
        top = MaterialTheme.spacing.extraSmall
    ),
    onFavoriteClick: (item: InquiryHistoryResponse) -> Unit,
    onShowDialogClick: (item: InquiryHistoryResponse) -> Unit,
    onItemClick: (item: InquiryHistoryResponse) -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = { onItemClick(inquiryItem) })
    ) {

        val (bookMarkIcon, tvNote, tvQueryValue, verticalMenu, divider) = createRefs()

        IconButton(onClick = { onFavoriteClick(inquiryItem) },
            modifier = modifier.constrainAs(bookMarkIcon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = "imgFavorite",
                tint = if (inquiryItem.Favorite) MaterialTheme.colors.primary else Color.LightGray,
                modifier = modifier.size(MaterialTheme.spacing.iconMedium)
            )
        }

        Text(
            text = inquiryItem.Note,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Right,
            modifier = modifier.constrainAs(tvNote) {
                top.linkTo(parent.top)
                start.linkTo(bookMarkIcon.end)
            }
        )

        Text(
            text = inquiryItem.QueryValue,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Right,
            color = Color.Gray,
            modifier = modifier.constrainAs(tvQueryValue) {
                bottom.linkTo(parent.bottom)
                top.linkTo(tvNote.bottom)
                start.linkTo(bookMarkIcon.end)
            }
        )

        IconButton(
            onClick = { onShowDialogClick(inquiryItem) },
            modifier = modifier
                .constrainAs(verticalMenu) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "vertical menu icon",
                modifier = modifier.size(MaterialTheme.spacing.iconMedium)
            )
        }

        Divider(modifier = modifier
            .padding(dividerPadding)
            .constrainAs(divider) {
                top.linkTo(tvQueryValue.bottom)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                width = Dimension.fillToConstraints
            })
    }
}