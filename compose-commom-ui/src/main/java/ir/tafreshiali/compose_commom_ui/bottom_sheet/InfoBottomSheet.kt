package ir.tafreshiali.compose_commom_ui.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import ir.tafreshiali.compose_commom_ui.R
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing
import ir.tafreshiali.compose_commom_ui.util.noRippleClickable

/**
 * Info Bottom Sheet [Composable]
 * actually we need to enable up stream apis to implement their custom view so we add nullable [Composable]
 * @param infoTitle the title of bottom sheet
 * @param infoTitleTextStyle the style of title of type [TextStyle]
 * @param infoDescription the description of bottom sheet
 * @param infoDescriptionTextStyle the style of description of type [TextStyle]
 * @param infoButton the text of bottom sheet button
 * @param infoButtonTextStyle the style of bottom sheet button of type [TextStyle]
 * @param [backGroundColor] [horizontalContentPadding] [contentVerticalArrangement] are the bottom sheet properties
 * @param onButtonClick a lambda function for reacting to the user clicks of bottom sheet button
 * @param content a [Composable] that enables up stream to decide which view matches their use case
 */

@Composable
fun InfoBottomSheet(
    infoTitle: String = stringResource(id = R.string.tv_info),
    infoTitleTextStyle: TextStyle = MaterialTheme.typography.h4,
    infoDescription: String,
    infoDescriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    infoButton: String = stringResource(id = R.string.btn_ok),
    infoButtonTextStyle: TextStyle = MaterialTheme.typography.button,
    backGroundColor: Color = Color.White,
    horizontalContentPadding: Dp = MaterialTheme.spacing.large,
    contentVerticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    onButtonClick: () -> Unit,
    content: @Composable ((
        infoTitle: String,
        infoTitleTextStyle: TextStyle,
        infoDescription: String,
        infoDescriptionTextStyle: TextStyle,
        infoButton: String,
        infoButtonTextStyle: TextStyle,
        backGroundColor: Color,
        horizontalContentPadding: Dp,
        contentVerticalArrangement: Arrangement.HorizontalOrVertical,
        onButtonClick: () -> Unit
    ) -> Unit)? = null

) {
    if (content != null) {
        content(
            infoTitle = infoTitle,
            infoTitleTextStyle = infoTitleTextStyle,
            infoDescription = infoDescription,
            infoDescriptionTextStyle = infoDescriptionTextStyle,
            infoButton = infoButton,
            infoButtonTextStyle = infoButtonTextStyle,
            backGroundColor = backGroundColor,
            horizontalContentPadding = horizontalContentPadding,
            contentVerticalArrangement = contentVerticalArrangement,
            onButtonClick = onButtonClick
        )

    } else {

        InfoBottomSheetContent(
            infoTitle = infoTitle,
            infoTitleTextStyle = infoTitleTextStyle,
            infoDescription = infoDescription,
            infoDescriptionTextStyle = infoDescriptionTextStyle,
            infoButton = infoButton,
            infoButtonTextStyle = infoButtonTextStyle,
            backGroundColor = backGroundColor,
            horizontalContentPadding = horizontalContentPadding,
            contentVerticalArrangement = contentVerticalArrangement,
            onButtonClick = onButtonClick
        )

    }
}


@Composable
private fun InfoBottomSheetContent(
    modifier: Modifier = Modifier,
    infoTitle: String = stringResource(id = R.string.tv_info),
    infoTitleTextStyle: TextStyle = MaterialTheme.typography.h4,
    infoDescription: String,
    infoDescriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    infoButton: String = stringResource(id = R.string.btn_ok),
    infoButtonTextStyle: TextStyle = MaterialTheme.typography.button,
    backGroundColor: Color = Color.White,
    horizontalContentPadding: Dp = MaterialTheme.spacing.large,
    contentVerticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    onButtonClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backGroundColor)
            .padding(horizontal = horizontalContentPadding),
        verticalArrangement = contentVerticalArrangement
    ) {
        Text(
            text = infoTitle,
            style = infoTitleTextStyle,
            modifier = modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        Text(
            text = infoDescription,
            style = infoDescriptionTextStyle,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Right,
            modifier = modifier
                .fillMaxWidth(),
        )

        Text(
            text = infoButton,
            style = infoButtonTextStyle,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .noRippleClickable(onClick = onButtonClick)
                .align(Alignment.End)
        )
    }
}