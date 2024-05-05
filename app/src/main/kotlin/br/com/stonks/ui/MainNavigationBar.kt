package br.com.stonks.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.stonks.navigation.MainNavDestination
import kotlinx.collections.immutable.ImmutableList

@Composable
private fun NavController.isCurrentDestination(destination: MainNavDestination): Boolean {
    return currentBackStackEntryAsState().value?.destination?.route == destination.name
}

@Composable
@Stable
internal fun MainNavigationBarLayout(
    navController: NavHostController,
    navItems: ImmutableList<MainNavItem>,
    startDestination: MainNavDestination,
    modifier: Modifier = Modifier,
) {
    val selectedNavIndex = rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    NavigationBar(
        modifier = modifier,
    ) {
        navItems.forEach { navItem ->
            NavigationBarItem(
                selected = navController.isCurrentDestination(navItem.route),
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = navItem.label)
                    )
                },
                onClick = {
                    selectedNavIndex.intValue = navItem.route.ordinal
                    navController.navigate(navItem.route.name)
                },
            )
        }
    }
}

internal data class MainNavItem(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
    val route: MainNavDestination,
)
