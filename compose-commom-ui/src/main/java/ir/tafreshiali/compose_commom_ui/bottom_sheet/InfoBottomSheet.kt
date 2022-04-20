package ir.tafreshiali.compose_commom_ui.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
 * @param [backGroundColor] [contentPadding] [contentVerticalArrangement] are the bottom sheet properties
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
    contentPadding: Dp = MaterialTheme.spacing.large,
    onButtonClick: () -> Unit,
    content: @Composable ((
        infoTitle: String,
        infoTitleTextStyle: TextStyle,
        infoDescription: String,
        infoDescriptionTextStyle: TextStyle,
        infoButton: String,
        infoButtonTextStyle: TextStyle,
        contentPadding: Dp,
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
            contentPadding = contentPadding,
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
            contentPadding = contentPadding,
            onButtonClick = onButtonClick
        )

    }
}

@Composable
fun InfoBottomSheetContent(
    modifier: Modifier = Modifier,
    infoTitle: String = stringResource(id = R.string.tv_info),
    infoTitleTextStyle: TextStyle = MaterialTheme.typography.h4,
    infoDescription: String,
    infoDescriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    infoButton: String = stringResource(id = R.string.btn_ok),
    infoButtonTextStyle: TextStyle = MaterialTheme.typography.button,
    backGroundColor: Color = Color.White,
    contentPadding: Dp = MaterialTheme.spacing.default,
    onButtonClick: () -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backGroundColor)
            .padding(contentPadding)
    ) {
        val (tvInfoTitle, tvInfoDescription, btnInfo) = createRefs()

        Text(
            text = infoTitle,
            style = infoTitleTextStyle,
            modifier = modifier
                .fillMaxWidth()
                .constrainAs(tvInfoTitle) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = infoDescription,
            style = infoDescriptionTextStyle,
            fontWeight = FontWeight.Medium,
            modifier = modifier
                .constrainAs(tvInfoDescription) {
                    top.linkTo(tvInfoTitle.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
                .padding(vertical = MaterialTheme.spacing.extraSmall),
        )

        Text(
            text = infoButton,
            style = infoButtonTextStyle,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .constrainAs(btnInfo) {
                    top.linkTo(tvInfoDescription.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
                .noRippleClickable(onClick = onButtonClick)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun InfoBottomSheetContentPreview() {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr)
    {
        InfoBottomSheetContent(
            infoDescription = "jkdfhgkdf",
            infoTitle = "sdgvfsdg",
            infoButton = "sdGVSD"
        ) {

        }
    }
}

