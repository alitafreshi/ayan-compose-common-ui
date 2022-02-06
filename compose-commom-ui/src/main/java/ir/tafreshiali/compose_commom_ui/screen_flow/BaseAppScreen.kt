package ir.tafreshiali.compose_commom_ui.screen_flow

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import ir.tafreshiali.ayan_core.util.Queue
import ir.tafreshiali.ayan_core.util.UIComponent
import ir.tafreshiali.compose_commom_ui.toolbar.DefaultCentralizeToolbar


/**
 * Base App Screen [Composable]
 * @param queue when ever an exception is happen with this queue we decided to show error message or not
 * @param bottomSheetState when ever we want to show  a bottom sheet ( loading / mobile operator or etc ) set the state and show related bottom sheet
 * @param onRemoveHeadFromQueue when ever an error happens and user close the bottom sheet ( via touch or pressing back button ) the error message should be remove from our queue
 * @param onRetryButtonClick this lambda function use for resending the cancelled request to the server
 * @param onCancelButtonClick this lambda function use for cancel the posted / sent  request to the server
 * @param onOkButtonClick this lambda function use for hiding the showed Info BottomSheet
 * @param navigationIcon the navigation icon positioned on the right of screen ( like back button )
 * @param actionIcon the action icon positioned on the left of screen ( like history button )
 * @param content main content of each screen
 * @param bottomBar bottom bar of each screen
 * @param serviceName the name that should show on the toolbar
 * @param loadingBottomSheetContent the content of loading bottom sheet
 * @param errorBottomSheetContent the content of error bottom sheet
 * @param infoBottomSheetContent the content of info bottom sheet
 * */


@ExperimentalMaterialApi
@Composable
fun BaseAppScreen(
    queue: Queue<UIComponent> = Queue(mutableListOf()),
    bottomSheetState: ir.tafreshiali.ayan_core.util.BottomSheetState = ir.tafreshiali.ayan_core.util.BottomSheetState.Idle,
    serviceName: String,
    navigationIcon: @Composable () -> Unit,
    actionIcon: @Composable () -> Unit,
    content: @Composable BoxScope.() -> Unit,
    bottomBar: @Composable () -> Unit,
    onRemoveHeadFromQueue: () -> Unit,
    onRetryButtonClick: (StateEvent: Any) -> Unit,
    onCancelButtonClick: () -> Unit,
    onOkButtonClick: () -> Unit,
    loadingBottomSheetContent: @Composable ((
        loadingTitle: String,
        loadingTitleTextStyle: TextStyle,
        cancelTitle: String,
        cancelTitleTextStyle: TextStyle,
        contentPadding: Dp,
        backGroundColor: Color,
        progressIndicatorColor: Color,
        contentVerticalArrangement: Arrangement.HorizontalOrVertical,
        contentHorizontalAlignment: Alignment.Horizontal,
        onButtonClick: () -> Unit
    ) -> Unit)? = null,

    errorBottomSheetContent: @Composable ((
        errorTitle: String,
        errorTitleTextStyle: TextStyle,
        errorDescription: String,
        errorDescriptionTextStyle: TextStyle,
        errorButton: String,
        errorButtonTextStyle: TextStyle,
        backGroundColor: Color,
        horizontalContentPadding: Dp,
        contentVerticalArrangement: Arrangement.HorizontalOrVertical,
        onButtonClick: () -> Unit
    ) -> Unit)? = null,
    infoBottomSheetContent: @Composable ((
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

    BottomSheetScreenWithContent(
        queue = queue,
        onRemoveHeadFromQueue = onRemoveHeadFromQueue,
        onRetryButtonClick = onRetryButtonClick,
        onCancelButtonClick = onCancelButtonClick,
        onOkButtonClick = onOkButtonClick,
        bottomSheetState = bottomSheetState,
        loadingBottomSheetContent = loadingBottomSheetContent,
        infoBottomSheetContent = infoBottomSheetContent,
        errorBottomSheetContent = errorBottomSheetContent

    ) { modalBottomSheetState, scope ->

        DefaultCentralizeToolbar(
            toolbarTitle = serviceName,
            navigationIcon = navigationIcon,
            actionIcon = actionIcon,
            content = content,
            bottomBar = bottomBar
        )
    }
}