package ir.tafreshiali.compose_commom_ui.timeLine

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

/**
 * Advance Time Line Item [Composable]
 * @param modifier
 * @param [topSpacing] [contentPadding] [lineWidth] [hasTopLine] [hasBottomLine] [timeLineOption] are the AdvanceTimeLineItem properties
 * @param item of generic type
 * @param infoContent a lambda function of type [Composable] for the right side of each time line item that enable up streams define their custom view
 * @param itemContent a lambda function of type [Composable] for the left side of each time line item that enable up streams define their custom view*/

@Composable
fun <T> AdvanceTimeLineItem(
    modifier: Modifier = Modifier,
    topSpacing: Dp = MaterialTheme.spacing.extraLarge,
    contentPadding: Dp = MaterialTheme.spacing.small,
    lineWidth: Dp = MaterialTheme.spacing.dividerExtraLarge,
    hasTopLine: Boolean = false,
    hasBottomLine: Boolean = false,
    timeLineOption: TimeLineOption,
    item: T,
    infoContent: @Composable () -> Unit,
    itemContent: @Composable ColumnScope.(T) -> Unit,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val (circle, infoContainer, topLine, bottomLine, timeLineContent, contentSpacing) = createRefs()

        Spacer(modifier = modifier
            .fillMaxWidth()
            .constrainAs(contentSpacing) {
                top.linkTo(parent.top)
                height = Dimension.value(topSpacing)
            })

        Surface(
            modifier = modifier
                .constrainAs(infoContainer) {
                    top.linkTo(contentSpacing.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth(0.2f),
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
                    width = Dimension.value(lineWidth)
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
                        width = Dimension.value(lineWidth)
                        height = Dimension.fillToConstraints
                    },
                color = timeLineOption.lineColor
            )

    }
}