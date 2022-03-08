package ir.tafreshiali.compose_commom_ui.toolbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

/**
 * Default centralize top bar / toolbar [Composable]
 * @param toolbarTitle the title of toolbar that brings a modifier for up streams
 * @param navigationIcon the navigation icon positioned on the right of screen ( like back button ) that brings a modifier for up streams
 * @param actionIcon the action icon positioned on the left of screen ( like history button )
 * @param backgroundColor back ground color for top bar / toolbar
 * @param elevation elevation for top bar / toolbar
 * */


@Composable
fun DefaultCentralizeTopBar(
    modifier: Modifier = Modifier,
    toolbarTitle: String,
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation: Dp = MaterialTheme.spacing.extraSmall,
    navigationIcon: @Composable (modifier: Modifier) -> Unit,
    actionIcon: @Composable (modifier: Modifier) -> Unit,
) {

    TopAppBar(
        backgroundColor = backgroundColor,
        contentPadding = PaddingValues(0.dp),
        elevation = elevation
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.topBar)
        ) {
            Text(
                text = toolbarTitle,
                style = MaterialTheme.typography.h3,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = modifier.align(Alignment.Center)
            )

            ConstraintLayout(modifier = modifier.fillMaxSize()) {
                val (actionView, navigationView) = createRefs()

                navigationIcon(modifier = modifier.constrainAs(navigationView) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                })

                actionIcon(
                    modifier = modifier.constrainAs(actionView) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    })
            }
        }
    }
}