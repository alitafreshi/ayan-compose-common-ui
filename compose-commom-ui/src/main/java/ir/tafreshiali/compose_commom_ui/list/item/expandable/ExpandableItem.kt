package ir.tafreshiali.compose_commom_ui.list.item.expandable

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 * Expandable Item [Composable]
 * @param modifier
 * @param expandAnimation an animation duration of type [Int]
 * @param [elevation] [cardRoundedCorner] [backGroundColor] [padding] are the item properties
 * @param isExpanded a [Boolean] for checking the stage of item
 * @param collapseContent a lambda function of type [Composable] enable up stream apis implement their own views for collapsed stage
 * @param expandableContent a lambda function of type [Composable] enable up stream apis implement their own views for expanded stage */


@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableItem(
    modifier: Modifier = Modifier,
    expandAnimation: Int = 300,
    elevation: Dp = MaterialTheme.spacing.extraSmall,
    cardRoundedCorner: Shape = MaterialTheme.shapes.medium,
    backGroundColor: Color = MaterialTheme.colors.background,
    padding: PaddingValues = PaddingValues(
        vertical = MaterialTheme.spacing.small,
        horizontal = MaterialTheme.spacing.default
    ),
    isExpanded: Boolean = false,
    collapseContent: @Composable (degrees: Float) -> Unit,
    expandableContent: @Composable (expanded: Boolean) -> Unit
) {

    val transitionState = remember {
        MutableTransitionState(isExpanded).apply {
            targetState = !isExpanded
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "transition")

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = expandAnimation)
    }, label = "rotationDegreeTransition") {
        if (isExpanded) 180f else 0f
    }

    Card(
        backgroundColor = backGroundColor,
        elevation = elevation,
        shape = cardRoundedCorner,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    paddingValues = padding
                )
        ) {
            collapseContent(arrowRotationDegree)
            expandableContent(isExpanded)
        }
    }
}