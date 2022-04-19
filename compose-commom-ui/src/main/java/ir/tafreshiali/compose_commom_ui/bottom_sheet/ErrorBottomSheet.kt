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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ir.tafreshiali.compose_commom_ui.R
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing
import ir.tafreshiali.compose_commom_ui.util.noRippleClickable

/**
 * error bottom sheet [Composable]
 * actually we need to enable up stream apis to implement their custom view so we add nullable [Composable]
 * @param errorTitle the title of bottom sheet
 * @param errorTitleTextStyle the style of title of type [TextStyle]
 * @param errorDescription the description of bottom sheet
 * @param errorDescriptionTextStyle the style of description of type [TextStyle]
 * @param errorButton the text of bottom sheet button
 * @param errorButtonTextStyle the style of bottom sheet button of type [TextStyle]
 * @param [backGroundColor] [contentPadding] [contentVerticalArrangement] are the bottom sheet properties
 * @param onButtonClick a lambda function for reacting to the user clicks of bottom sheet button
 * @param content a [Composable] that enables up stream to decide which view matches their use case
 */

@Composable
fun ErrorBottomSheet(
    errorTitle: String,
    errorTitleTextStyle: TextStyle = MaterialTheme.typography.h4,
    errorDescription: String,
    errorDescriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    errorButton: String = stringResource(id = R.string.btn_retry),
    errorButtonTextStyle: TextStyle = MaterialTheme.typography.button,
    backGroundColor: Color = Color.White,
    contentPadding: Dp = MaterialTheme.spacing.default,
    onButtonClick: () -> Unit,
    content: @Composable ((
        errorTitle: String,
        errorTitleTextStyle: TextStyle,
        errorDescription: String,
        errorDescriptionTextStyle: TextStyle,
        errorButton: String,
        errorButtonTextStyle: TextStyle,
        contentPadding: Dp,
        onButtonClick: () -> Unit
    ) -> Unit)? = null
) {

    if (content != null) {

        content(
            errorTitle = errorTitle,
            errorTitleTextStyle = errorTitleTextStyle,
            errorDescription = errorDescription,
            errorDescriptionTextStyle = errorDescriptionTextStyle,
            errorButton = errorButton,
            errorButtonTextStyle = errorButtonTextStyle,
            contentPadding = contentPadding,
            onButtonClick = onButtonClick
        )

    } else {

        ErrorBottomSheetContent(
            errorTitle = errorTitle,
            errorTitleTextStyle = errorTitleTextStyle,
            errorDescription = errorDescription,
            errorDescriptionTextStyle = errorDescriptionTextStyle,
            errorButton = errorButton,
            errorButtonTextStyle = errorButtonTextStyle,
            backGroundColor = backGroundColor,
            contentPadding = contentPadding,
            onButtonClick = onButtonClick
        )
    }

}


/**
 * Error Bottom Sheet Content [Composable]
 * the default content for error bottom sheet
 * @param modifier
 * @param errorTitle the title of bottom sheet
 * @param errorTitleTextStyle the style of title of type [TextStyle]
 * @param errorDescription the description of bottom sheet
 * @param errorDescriptionTextStyle the style of description of type [TextStyle]
 * @param errorButton the text of bottom sheet button
 * @param errorButtonTextStyle the style of bottom sheet button of type [TextStyle]
 * @param [backGroundColor] [contentPadding] are the bottom sheet properties
 * @param onButtonClick a lambda function for reacting to the user clicks of bottom sheet button
 */


/*
@Composable
fun ErrorBottomSheetContent(
    modifier: Modifier = Modifier,
    errorTitle: String,
    errorTitleTextStyle: TextStyle = MaterialTheme.typography.h4,
    errorDescription: String,
    errorDescriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    errorButton: String = stringResource(id = R.string.btn_retry),
    errorButtonTextStyle: TextStyle = MaterialTheme.typography.button,
    backGroundColor: Color = Color.White,
    contentPadding: Dp = MaterialTheme.spacing.default,
    contentVerticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backGroundColor)
            .padding(contentPadding),
        verticalArrangement = contentVerticalArrangement
    ) {
        Text(
            text = errorTitle,
            style = errorTitleTextStyle,
            modifier = modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        Text(
            text = errorDescription,
            style = errorDescriptionTextStyle,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Right,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.extraSmall),
        )

        Text(
            text = errorButton,
            style = errorButtonTextStyle,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .noRippleClickable(onClick = onButtonClick)
                .align(Alignment.End)
        )
    }
}*/


@Composable
fun ErrorBottomSheetContent(
    modifier: Modifier = Modifier,
    errorTitle: String,
    errorTitleTextStyle: TextStyle = MaterialTheme.typography.h4,
    errorDescription: String,
    errorDescriptionTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    errorButton: String = stringResource(id = R.string.btn_retry),
    errorButtonTextStyle: TextStyle = MaterialTheme.typography.button,
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
        val (tvError, tvErrorDescription, btnError) = createRefs()

        Text(
            text = errorTitle,
            style = errorTitleTextStyle,
            modifier = modifier
                .constrainAs(tvError) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
        )


        Text(
            text = errorDescription,
            style = errorDescriptionTextStyle,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Right,
            modifier = modifier
                .constrainAs(tvErrorDescription) {
                    top.linkTo(tvError.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    width = Dimension.fillToConstraints
                }
                .padding(vertical = MaterialTheme.spacing.extraSmall),
        )

        Text(
            text = errorButton,
            style = errorButtonTextStyle,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .constrainAs(btnError) {
                    top.linkTo(tvErrorDescription.bottom)
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
fun ErrorBottomSheetContentPreview(

) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        ErrorBottomSheetContent(
            errorTitle = "error",
            errorDescription = "bad error happend",
            errorButton = "retry"
        ) {}
    }
}


