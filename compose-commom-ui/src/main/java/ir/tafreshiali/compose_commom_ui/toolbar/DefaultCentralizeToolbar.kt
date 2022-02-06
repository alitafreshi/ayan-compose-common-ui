package ir.tafreshiali.compose_commom_ui.toolbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.tafreshiali.compose_commom_ui.responsiveness.spacing


/**
 *The default toolbar with centralize title
 * @param toolbarTitle the title of toolbar that brings a modifier for up streams
 * @param navigationIcon the navigation icon positioned on the right of screen ( like back button ) that brings a modifier for up streams
 * @param actionIcon the action icon positioned on the left of screen ( like history button )
 * @param content the main screen / app  UI ELEMENTS ( like lists / images and etc)
 */
@Composable
fun DefaultCentralizeToolbar(
    modifier: Modifier = Modifier,
    toolbarTitle: String,
    topBarContent: @Composable (() -> Unit)? = null,
    navigationIcon: @Composable (modifier: Modifier) -> Unit,
    actionIcon: @Composable (modifier: Modifier) -> Unit,
    content: @Composable BoxScope.() -> Unit,
    bottomBar: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            if (topBarContent != null) {
                topBarContent()
            } else {

                DefaultCentralizeTopBar(
                    toolbarTitle = toolbarTitle,
                    navigationIcon = navigationIcon,
                    actionIcon = actionIcon
                )
            }
        },
        bottomBar = bottomBar
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    start = MaterialTheme.spacing.default,
                    end = MaterialTheme.spacing.default,
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()

                )
        ) {
            content()
        }
    }
}