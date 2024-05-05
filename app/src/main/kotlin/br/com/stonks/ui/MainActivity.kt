package br.com.stonks.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.stonks.designsystem.R
import br.com.stonks.feature.home.ui.view.HomeScreen
import br.com.stonks.feature.stocks.ui.view.StockAlertScreen
import br.com.stonks.navigation.MainNavDestination
import kotlinx.collections.immutable.toImmutableList

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()

            Scaffold(
                containerColor = Color.Transparent,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.fillMaxSize(),
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
                bottomBar = {
                    MainNavigationBarLayout(
                        navController = navController,
                        navItems = listOf(
                            MainNavItem(
                                icon = R.drawable.ic_home,
                                label = br.com.stonks.R.string.main_nav_action_home,
                                route = MainNavDestination.HOME,
                            ),
                            MainNavItem(
                                icon = R.drawable.ic_radar,
                                label = br.com.stonks.R.string.main_nav_action_stock_alert,
                                route = MainNavDestination.STOCK,
                            ),
                        ).toImmutableList(),
                        startDestination = MainNavDestination.HOME,
                    )
                }
            ) { innerPadding ->
                Surface(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = MainNavDestination.HOME.name,
                    ) {
                        composable(route = MainNavDestination.HOME.name) {
                            HomeScreen(
                                snackbarHostState = snackbarHostState,
                            )
                        }
                        composable(route = MainNavDestination.STOCK.name) {
                            StockAlertScreen(
                                snackbarHostState = snackbarHostState,
                            )
                        }
                    }
                }
            }
        }
    }
}
