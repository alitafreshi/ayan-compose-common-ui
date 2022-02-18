package ir.tafreshiali.compose_commom_ui.list.item.key_value

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import ir.tafreshiali.ayan_core.key_value.AdvanceKeyValue
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 * Key Value List [Composable]
 * @param modifier
 * @param [backGroundColor] [verticalItemSpacing] are the list properties
 * @param [modifier] [backGroundColor] [keyTextStyle] [keyTextColor] [valueTextStyle] [valueTextColor] [dividerColor] [dividerThickness] are the key value item properties
 * @param itemList a list of type [AdvanceKeyValue]*/

@Composable
fun KeyValueList(
    modifier: Modifier = Modifier,
    backGroundColor: Color = Color.White,
    verticalItemSpacing: Dp = MaterialTheme.spacing.small,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default),
    itemBackGroundColor: Color = Color.White,
    keyTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    keyTextColor: Color = Color.Gray,
    valueTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    valueTextColor: Color = Color.White,
    valueTextSpacing: Dp = MaterialTheme.spacing.default,
    dividerColor: Color = Color.LightGray,
    dividerThickness: Dp = MaterialTheme.spacing.dividerExtraSmall,
    itemList: List<AdvanceKeyValue>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backGroundColor),
        verticalArrangement = Arrangement.spacedBy(verticalItemSpacing)
    ) {
        itemList.forEach { item ->
            KeyValueItem(
                item = item,
                contentPadding = contentPadding,
                backGroundColor = itemBackGroundColor,
                keyTextStyle = keyTextStyle,
                keyTextColor = keyTextColor,
                valueTextStyle = valueTextStyle,
                valueTextColor = valueTextColor,
                valueTextSpacing = valueTextSpacing,
                dividerColor = dividerColor,
                dividerThickness = dividerThickness
            )
        }
    }
}


/**
 * Key Value Item [Composable]
 * @param [modifier] [backGroundColor] [keyTextStyle] [keyTextColor] [valueTextStyle] [valueTextColor] [dividerColor] [dividerThickness] are the properties
 * @param item of type [AdvanceKeyValue] basically we need a type that accepts tree different values :
 * 1 - a key of type [String]
 * 2 - a value if type [String]
 * 3 - a [Boolean] for setting the divider visibility*/


@Composable
fun KeyValueItem(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default),
    backGroundColor: Color = Color.White,
    keyTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    keyTextColor: Color = Color.Gray,
    valueTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    valueTextColor: Color = Color.White,
    valueTextSpacing: Dp = MaterialTheme.spacing.default,
    dividerColor: Color = Color.LightGray,
    dividerThickness: Dp = MaterialTheme.spacing.dividerExtraSmall,
    item: AdvanceKeyValue,
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
            text = item.key,
            style = keyTextStyle,
            color = keyTextColor
        )


        (if (!(item.value.isNullOrEmpty())) item.value else "")?.let {
            Text(
                modifier = modifier.constrainAs(value) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(anchor = key.end, margin = valueTextSpacing)
                    width = Dimension.fillToConstraints
                },
                text = it,
                style = valueTextStyle,
                color = valueTextColor,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.End
            )
        }

        if (item.hasBottomDivider)
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