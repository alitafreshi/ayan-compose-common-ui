package ir.tafreshiali.compose_commom_ui.toolbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing

/**
 * Default centralize top bar / toolbar [Composable]
 * @param toolbarTitle the title of toolbar
 * @param navigationIcon the navigation icon positioned on the right of screen ( like back button )
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
    navigationIcon: @Composable () -> Unit,
    actionIcon: @Composable () -> Unit,
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

            Row(
                modifier = modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                actionIcon()
                navigationIcon()
            }
        }
    }
}