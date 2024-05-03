package br.com.stonks.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.stonks.designsystem.R
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.navigation.MainNavDestination

@Composable
private fun RowScope.NavigationItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    selected: Boolean,
    onClick: () -> Unit,
) {
    BottomNavigationItem(
        selected = selected,
        icon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
            )
        },
        label = {
            Text(
                text = stringResource(id = label)
            )
        },
        onClick = {
            onClick()
        }
    )
}

@Composable
internal fun BottomAppBarLayout(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        containerColor = ColorToken.BrandGreenDark,
        contentColor = ColorToken.NeutralWhite,
        content = {
            BottomNavigation(
                elevation = SpacingToken.none,
                backgroundColor = ColorToken.BrandGreenDark,
            ) {
                NavigationItem(
                    icon = R.drawable.ic_home,
                    label = br.com.stonks.R.string.main_nav_action_home,
                    selected = true,
                ) {
                    navController.navigate(MainNavDestination.HOME.route)
                }
                NavigationItem(
                    icon = R.drawable.ic_radar,
                    label = br.com.stonks.R.string.main_nav_action_stock_alert,
                    selected = false,
                ) {
                    navController.navigate(MainNavDestination.STOCK.route)
                }
            }
        },
    )
}
