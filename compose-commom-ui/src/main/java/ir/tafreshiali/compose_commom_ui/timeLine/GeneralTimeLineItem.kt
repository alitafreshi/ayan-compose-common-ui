package ir.tafreshiali.compose_commom_ui.timeLine

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 * Time Line Item View [Composable]
 * @param modifier
 * @param [topSpacing] [contentPadding] [lineWidth] are the time line item properties
 * @param isLastItem a [Boolean] for detecting the last item
 * @param itemContent a lambda function of type [Composable] for the left side of each time line item that enable up stream apis define their own view for each item
 * */

@Composable
fun <T> GeneralTimeLineItem(
    modifier: Modifier = Modifier,
    topSpacing: Dp = MaterialTheme.spacing.extraLarge,
    contentPadding: Dp = MaterialTheme.spacing.small,
    lineWidth: Dp = MaterialTheme.spacing.dividerExtraLarge,
    timeLineOption: TimeLineOption,
    isLastItem: Boolean = false,
    item: T,
    itemContent: @Composable ColumnScope.(T) -> Unit,
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (circle, topLine, bottomLine, timeLineContent, contentSpacing) = createRefs()


        Spacer(modifier = modifier
            .fillMaxWidth()
            .constrainAs(contentSpacing) {
                top.linkTo(parent.top)
                height = Dimension.value(topSpacing)
            })

        Icon(
            painter = painterResource(
                id = timeLineOption.circleIcon
            ),
            contentDescription = "Item Image",
            tint = MaterialTheme.colors.primary,
            modifier = modifier
                .zIndex(3f)
                .constrainAs(circle) {
                    top.linkTo(contentSpacing.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)

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


        if (!isLastItem)
            Divider(
                modifier = Modifier
                    .constrainAs(bottomLine) {
                        top.linkTo(circle.bottom)
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