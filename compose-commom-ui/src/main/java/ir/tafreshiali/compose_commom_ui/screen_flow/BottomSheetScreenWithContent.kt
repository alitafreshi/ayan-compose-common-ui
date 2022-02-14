package ir.tafreshiali.compose_commom_ui.screen_flow


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import ir.tafreshiali.ayan_core.util.Queue
import ir.tafreshiali.ayan_core.util.UIComponent
import ir.tafreshiali.compose_commom_ui.bottom_sheet.GenericBottomSheet
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing
import kotlinx.coroutines.CoroutineScope


/**
 * GOAL = To have default / general composable for using (centralize toolbar + tabs + view pager) with each other
 * from now on the whole sheet content wraps around inside a [androidx.compose.foundation.layout.BoxWithConstraints] and the sheet content height limited to the middle of the phone screen
 * @param queue when ever an exception is happen with this queue we decided to show error message or not
 * @param bottomSheetState when ever we want to show  a bottom sheet ( loading / mobile operator or etc ) set the state and show related bottom sheet
 * @param onRemoveHeadFromQueue when ever an error happens and user close the bottom sheet ( via touch or pressing back button ) the error message should be remove from our queue
 * @param [sheetBackgroundColor] [sheetElevation] [sheetShape] are properties of modal bottom sheet
 * @param onRetryButtonClick this lambda function use for resending the cancelled request to the server
 * @param onCancelButtonClick this lambda function use for cancel the posted / sent  request to the server
 * @param onOkButtonClick this lambda function use for hiding the showed Info BottomSheet
 * @param mainContent the content that should be show on each screen
 * @param loadingBottomSheetContent the content of loading bottom sheet
 * @param errorBottomSheetContent the content of error bottom sheet
 * @param infoBottomSheetContent the content of info bottom sheet
 * @param confirmBottomSheetContent the content of confirm bottom sheet
 * */


@ExperimentalMaterialApi
@Composable
fun BottomSheetScreenWithContent(
    modifier: Modifier = Modifier,
    queue: Queue<UIComponent> = Queue(mutableListOf()),
    onRemoveHeadFromQueue: () -> Unit,
    bottomSheetState: ir.tafreshiali.ayan_core.util.BottomSheetState = ir.tafreshiali.ayan_core.util.BottomSheetState.Idle,
    sheetBackgroundColor: Color = Color.Transparent,
    sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
    isGestureEnable: Boolean = false,
    sheetShape: RoundedCornerShape = RoundedCornerShape(
        topStart = MaterialTheme.spacing.default,
        topEnd = MaterialTheme.spacing.default
    ),
    onRetryButtonClick: (StateEvent: Any) -> Unit,
    onCancelButtonClick: () -> Unit,
    onOkButtonClick: () -> Unit,
    loadingBottomSheetContent: @Composable ((
        loadingTitle: String,
        loadingTitleTextStyle: TextStyle,
        cancelTitle: String,
        cancelTitleTextStyle: TextStyle,
        contentPadding: Dp,
        progressIndicatorColor: Color,
        onButtonClick: () -> Unit
    ) -> Unit)? = null,

    errorBottomSheetContent: @Composable ((
        errorTitle: String,
        errorTitleTextStyle: TextStyle,
        errorDescription: String,
        errorDescriptionTextStyle: TextStyle,
        errorButton: String,
        errorButtonTextStyle: TextStyle,
        horizontalContentPadding: Dp,
        onButtonClick: () -> Unit
    ) -> Unit)? = null,
    infoBottomSheetContent: @Composable ((
        infoTitle: String,
        infoTitleTextStyle: TextStyle,
        infoDescription: String,
        infoDescriptionTextStyle: TextStyle,
        infoButton: String,
        infoButtonTextStyle: TextStyle,
        horizontalContentPadding: Dp,
        onButtonClick: () -> Unit
    ) -> Unit)? = null,
    confirmBottomSheetContent: @Composable (() -> Unit)? = null,
    mainContent: @Composable (modalBottomSheetState: ModalBottomSheetState, scope: CoroutineScope) -> Unit
) {

    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { isGestureEnable }
    )

    val scope = rememberCoroutineScope()


    ModalBottomSheetLayout(
        sheetBackgroundColor = sheetBackgroundColor,
        sheetElevation = MaterialTheme.spacing.unspecified,
        sheetShape = sheetShape,
        sheetState = state,
        sheetContent = {
            BoxWithConstraints(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent),
                contentAlignment = Alignment.BottomCenter
            ) {

                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .heightIn(max = maxHeight / 2)
                        .shadow(elevation = sheetElevation, shape = sheetShape)
                        .background(color = MaterialTheme.colors.background, shape = sheetShape)

                ) {
                    GenericBottomSheet(
                        queue = queue,
                        bottomSheetState = bottomSheetState,
                        state = state,
                        onRemoveHeadFromQueue = onRemoveHeadFromQueue,
                        onRetryButtonClick = onRetryButtonClick,
                        onCancelButtonClick = onCancelButtonClick,
                        onOkButtonClick = onOkButtonClick,
                        loadingBottomSheetContent = loadingBottomSheetContent,
                        infoBottomSheetContent = infoBottomSheetContent,
                        errorBottomSheetContent = errorBottomSheetContent,
                        confirmBottomSheetContent = confirmBottomSheetContent
                    )
                }
            }
        }
    ) {
        mainContent(state, scope)
    }
}