package ir.tafreshiali.compose_commom_ui.timeLine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

/**
 * Advance Time Line Item [Composable]
 * now contents bounds to the top and bottom of the parent and there is no extra padding at the top of each item
 * @param modifier
 * @param [backgroundColor] [contentPadding] [hasTopLine] [hasBottomLine] [infoContentFraction] [timeLineOption] are the AdvanceTimeLineItem properties
 * @param item of generic type
 * @param infoContent a lambda function of type [Composable] for the right side of each time line item that enable up streams define their custom view
 * @param itemContent a lambda function of type [Composable] for the left side of each time line item that enable up streams define their custom view*/

@Composable
fun <T> AdvanceTimeLineItem(
    modifier: Modifier = Modifier,
    item: T,
    backgroundColor: Color = Color.White,
    contentPadding: Dp = MaterialTheme.spacing.small,
    hasTopLine: Boolean = false,
    hasBottomLine: Boolean = false,
    infoContentFraction: Float = 0.3f,
    timeLineOption: TimeLineOption,
    infoContent: @Composable () -> Unit,
    itemContent: @Composable (T) -> Unit,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {

        val (circle, infoContainer, topLine, bottomLine, timeLineContent) = createRefs()

        Surface(
            modifier = modifier
                .constrainAs(infoContainer) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(infoContentFraction),
        ) {
            infoContent()
        }

        Icon(
            painter = painterResource(
                id = timeLineOption.circleIcon
            ),
            contentDescription = "Item Image",
            tint = timeLineOption.circleColor,
            modifier = Modifier
                .zIndex(3f)
                .constrainAs(circle) {
                    top.linkTo(infoContainer.top)
                    bottom.linkTo(infoContainer.bottom)
                    start.linkTo(anchor = infoContainer.end)
                }
        )

        Column(
            modifier = modifier
                .constrainAs(timeLineContent) {
                    start.linkTo(anchor = circle.end, margin = contentPadding)
                    top.linkTo(circle.top)
                    bottom.linkTo(circle.bottom)
                    end.linkTo(anchor = parent.end)
                    width = Dimension.fillToConstraints
                }, verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.dividerLarge)
        ) {
            itemContent(item)
        }

        if (hasTopLine)
            Divider(
                modifier = modifier.constrainAs(topLine) {
                    top.linkTo(parent.top)
                    bottom.linkTo(circle.top)
                    start.linkTo(circle.start)
                    end.linkTo(circle.end)
                    width = Dimension.value(timeLineOption.lineWidth)
                    height = Dimension.fillToConstraints
                }, color = timeLineOption.lineColor
            )

        if (hasBottomLine)
            Divider(
                modifier = Modifier
                    .constrainAs(bottomLine) {
                        top.linkTo(anchor = circle.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(circle.start)
                        end.linkTo(circle.end)
                        width = Dimension.value(timeLineOption.lineWidth)
                        height = Dimension.fillToConstraints
                    },
                color = timeLineOption.lineColor
            )
    }
}