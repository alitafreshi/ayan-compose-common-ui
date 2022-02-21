package ir.tafreshiali.compose_commom_ui.bottom_sheet

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import ir.tafreshiali.ayan_core.util.BottomSheetState
import ir.tafreshiali.ayan_core.util.Queue
import ir.tafreshiali.ayan_core.util.UIComponent


/**
 * GOAL = To have system that controls / handle over different types of bottom sheets and their states ( show / hide )
 * from now on up streams can customise each bottom sheets views / content
 * @param bottomSheetState for showing different types of bottom sheets Except Error bottom sheets
 * @param queue for handling errors and showing error bottom sheet
 * @param state as type of modal bottoms sheet state that give use controls over different state of bottom sheet ( like show / hide / current value etc)
 * @param onRemoveHeadFromQueue this lambda function use for removing the error messages from [queue]
 * @param onRetryButtonClick this lambda function use for resending the cancelled request to the server
 * @param onCancelButtonClick this lambda function use for cancel the posted / sent  request to the server
 * @param loadingBottomSheetContent the content of loading bottom sheet
 * @param errorBottomSheetContent the content of error bottom sheet
 * @param infoBottomSheetContent the content of info bottom sheet
 * @param confirmBottomSheetContent the content of confirm bottom sheet
 * */


@ExperimentalMaterialApi
@Composable
fun GenericBottomSheet(
    bottomSheetState: BottomSheetState = BottomSheetState.Idle,
    queue: Queue<UIComponent>,
    state: ModalBottomSheetState,
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
    confirmBottomSheetContent: @Composable (() -> Unit)? = null

) {
    if (!queue.isEmpty()) {
        queue.peek()?.let { uiComponent ->

            if (uiComponent is UIComponent.ErrorBottomSheet<*>) {

                ErrorBottomSheet(
                    errorTitle = uiComponent.errorTitle,
                    errorDescription = uiComponent.errorDescription,
                    onButtonClick = {
                        // retry to send the request
                        onRemoveHeadFromQueue()
                        uiComponent.stateEvent?.let { stateEvent ->
                            onRetryButtonClick(stateEvent)
                        }
                    },
                    content = errorBottomSheetContent
                )

                LaunchedEffect(key1 = state.currentValue) {
                    state.animateTo(targetValue = ModalBottomSheetValue.Expanded, anim = tween(500))
                }

            }
            if (uiComponent is UIComponent.InfoBottomSheet) {

                InfoBottomSheet(
                    infoTitle = uiComponent.infoTitle,
                    infoDescription = uiComponent.infoDescription,
                    infoButton = uiComponent.infoButton,
                    onButtonClick = {
                        // retry to send the request
                        onRemoveHeadFromQueue()
                        onOkButtonClick()
                    },
                    content = infoBottomSheetContent
                )

                LaunchedEffect(key1 = state.currentValue) {
                    state.animateTo(targetValue = ModalBottomSheetValue.Expanded, anim = tween(500))
                }

            }
        }
    }

    when (bottomSheetState) {
        is BottomSheetState.Loading -> {
            LoadingBottomSheet(content = loadingBottomSheetContent, onButtonClick = {
                onCancelButtonClick()
            })


            LaunchedEffect(key1 = state.currentValue) {
                state.animateTo(targetValue = ModalBottomSheetValue.Expanded, anim = tween(500))
            }

        }

        is BottomSheetState.Confirm -> {
            confirmBottomSheetContent?.let { content ->
                ConfirmBottomSheet(content = content)

                LaunchedEffect(key1 = state.currentValue) {
                    state.animateTo(targetValue = ModalBottomSheetValue.Expanded, anim = tween(500))
                }
            }
        }

        is BottomSheetState.Idle -> {
            if (queue.isEmpty()) {

                LaunchedEffect(key1 = state.currentValue) {
                    state.animateTo(targetValue = ModalBottomSheetValue.Hidden, anim = tween(500))
                }

            }
        }
    }

    BackHandler(
        enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded || state.currentValue == ModalBottomSheetValue.Expanded),
        onBack = onRemoveHeadFromQueue
    )
}