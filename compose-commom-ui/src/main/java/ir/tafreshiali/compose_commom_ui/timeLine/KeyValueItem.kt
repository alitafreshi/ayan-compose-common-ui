package ir.tafreshiali.compose_commom_ui.timeLine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 * Key Value List [Composable]
 * @param modifier
 * @param [backGroundColor] [verticalItemSpacing] are the properties
 * @param itemList a list of type [Triple]*/

@Composable
fun KeyValueList(
    modifier: Modifier = Modifier,
    backGroundColor: Color = Color.White,
    verticalItemSpacing: Dp = MaterialTheme.spacing.small,
    contentPadding: Dp = MaterialTheme.spacing.default,
    itemList: List<Triple<String, String?, Boolean>>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backGroundColor),
        verticalArrangement = Arrangement.spacedBy(verticalItemSpacing)
    ) {
        itemList.forEach { item ->
            KeyValueItem(item = item, contentPadding = contentPadding)
        }
    }
}


/**
 * Key Value Item [Composable]
 * @param [modifier] [backGroundColor] [keyTextStyle] [keyTextColor] [valueTextStyle] [valueTextColor] [dividerColor] [dividerThickness] are the properties
 * @param item of type [Triple] basically we need a type that accepts tree different values :
 * 1 - a key of type [String]
 * 2 - a value if type [String]
 * 3 - a [Boolean] for setting the divider visibility*/


@Composable
fun KeyValueItem(
    modifier: Modifier = Modifier,
    contentPadding: Dp = MaterialTheme.spacing.default,
    backGroundColor: Color = Color.White,
    keyTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    keyTextColor: Color = Color.Gray,
    valueTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    valueTextColor: Color = Color.White,
    dividerColor: Color = Color.LightGray,
    dividerThickness: Dp = MaterialTheme.spacing.dividerExtraSmall,
    item: Triple<String, String?, Boolean>,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(backGroundColor)
            .padding(contentPadding)
    ) {
        val (key, value, divider) = createRefs()
        Text(
            modifier = modifier.constrainAs(key) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            },
            text = item.first,
            style = keyTextStyle,
            color = keyTextColor
        )


        (if (!(item.second.isNullOrEmpty())) item.second else "")?.let {
            Text(
                modifier = modifier.constrainAs(value) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(anchor = key.end, margin = contentPadding)
                    width = Dimension.fillToConstraints
                },
                text = it,
                style = valueTextStyle,
                color = valueTextColor,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.End
            )
        }

        if (item.third)
            Divider(
                modifier = modifier
                    .constrainAs(divider) {
                        top.linkTo(key.bottom)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(top = MaterialTheme.spacing.small),
                thickness = dividerThickness,
                color = dividerColor
            )
    }
}