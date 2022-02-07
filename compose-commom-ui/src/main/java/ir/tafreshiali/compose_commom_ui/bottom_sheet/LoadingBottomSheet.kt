package ir.tafreshiali.compose_commom_ui.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import ir.tafreshiali.compose_commom_ui.R
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing
import ir.tafreshiali.compose_commom_ui.util.noRippleClickable

/**
 * Loading Bottom Sheet [Composable]
 * actually we need to enable up stream apis to implement their custom view so we add nullable [Composable]
 * @param loadingTitle the title of bottom sheet
 * @param loadingTitleTextStyle the style of title of type [TextStyle]
 * @param cancelTitle the description of bottom sheet
 * @param cancelTitleTextStyle the style of cancel title of type [TextStyle]
 * @param [backGroundColor] [contentHorizontalAlignment] [contentVerticalArrangement] are the bottom sheet properties
 * @param onButtonClick a lambda function for reacting to the user clicks of bottom sheet button
 * @param content a [Composable] that enables up stream to decide which view matches their use case
 */

@Composable
fun LoadingBottomSheet(
    loadingTitle: String = stringResource(id = R.string.tv_loading),
    loadingTitleTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    cancelTitle: String = stringResource(id = R.string.btn_cancel),
    cancelTitleTextStyle: TextStyle = MaterialTheme.typography.button,
    contentPadding: Dp = MaterialTheme.spacing.small,
    backGroundColor: Color = Color.White,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    contentVerticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onButtonClick: () -> Unit,
    content: @Composable ((
        loadingTitle: String,
        loadingTitleTextStyle: TextStyle,
        cancelTitle: String,
        cancelTitleTextStyle: TextStyle,
        contentPadding: Dp,
        progressIndicatorColor: Color,
        onButtonClick: () -> Unit
    ) -> Unit)? = null
) {
    if (content != null) {

        content(
            loadingTitle,
            loadingTitleTextStyle,
            cancelTitle,
            cancelTitleTextStyle,
            contentPadding,
            progressIndicatorColor,
            onButtonClick
        )

    } else {
        LoadingBottomSheetContent(
            loadingTitle = loadingTitle,
            loadingTitleTextStyle = loadingTitleTextStyle,
            cancelTitle = cancelTitle,
            cancelTitleTextStyle = cancelTitleTextStyle,
            contentPadding = contentPadding,
            backGroundColor = backGroundColor,
            progressIndicatorColor = progressIndicatorColor,
            contentVerticalArrangement = contentVerticalArrangement,
            contentHorizontalAlignment = contentHorizontalAlignment,
            onButtonClick = onButtonClick
        )
    }
}

/**
 * Loading Bottom Sheet Content [Composable]
 * the default content for loading bottom sheet
 * @param loadingTitle the title of bottom sheet
 * @param loadingTitleTextStyle the style of title of type [TextStyle]
 * @param cancelTitle the description of bottom sheet
 * @param cancelTitleTextStyle the style of cancel title of type [TextStyle]
 * @param [backGroundColor] [contentHorizontalAlignment] [contentVerticalArrangement] are the bottom sheet properties
 * @param onButtonClick a lambda function for reacting to the user clicks of bottom sheet button*/


@Composable
private fun LoadingBottomSheetContent(
    modifier: Modifier = Modifier,
    loadingTitle: String = stringResource(id = R.string.tv_loading),
    loadingTitleTextStyle: TextStyle = MaterialTheme.typography.subtitle2,
    cancelTitle: String = stringResource(id = R.string.btn_cancel),
    cancelTitleTextStyle: TextStyle = MaterialTheme.typography.button,
    contentPadding: Dp = MaterialTheme.spacing.small,
    backGroundColor: Color = Color.White,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    contentVerticalArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center,
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backGroundColor)
            .padding(contentPadding),
        verticalArrangement = contentVerticalArrangement,
        horizontalAlignment = contentHorizontalAlignment
    ) {

        CircularProgressIndicator(
            color = progressIndicatorColor
        )

        Text(
            text = loadingTitle,
            style = loadingTitleTextStyle,
            modifier = modifier.padding(vertical = MaterialTheme.spacing.extraSmall)
        )

        Text(
            text = cancelTitle,
            style = cancelTitleTextStyle,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = modifier.noRippleClickable(onClick = onButtonClick)
        )
    }
}