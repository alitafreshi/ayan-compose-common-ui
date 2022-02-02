package ir.tafreshiali.compose_commom_ui.bottom_sheet

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ir.tafreshiali.ayan_core.util.BottomSheetState
import ir.tafreshiali.ayan_core.util.Queue
import ir.tafreshiali.ayan_core.util.UIComponent


/**
 * GOAL = To have system that controls / handle over different types of bottom sheets and their states ( show / hide )
 * @param bottomSheetState for showing different types of bottom sheets Except Error bottom sheets
 * @param queue for handling errors and showing error bottom sheet
 * @param state as type of modal bottoms sheet state that give use controls over different state of bottom sheet ( like show / hide / current value etc)
 * @param onRemoveHeadFromQueue this lambda function use for removing the error messages from [queue]
 * @param onRetryButtonClick this lambda function use for resending the cancelled request to the server
 * @param onCancelButtonClick this lambda function use for cancel the posted / sent  request to the server
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
                    }
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
                    }
                )

                LaunchedEffect(key1 = state.currentValue) {
                    state.animateTo(targetValue = ModalBottomSheetValue.Expanded, anim = tween(500))
                }

            }
        }
    }

    when (bottomSheetState) {
        is BottomSheetState.Loading -> {
            LoadingBottomSheet(onButtonClick = {
                onCancelButtonClick()
            })


            LaunchedEffect(key1 = state.currentValue) {
                state.animateTo(targetValue = ModalBottomSheetValue.Expanded, anim = tween(500))
            }

        }

        is BottomSheetState.MobileOperator -> {

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