package br.com.stonks.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.stonks.designsystem.R
import br.com.stonks.feature.home.ui.view.HomeScreen
import br.com.stonks.feature.stocks.ui.view.StockAlertScreen
import br.com.stonks.navigation.MainNavDestination

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val selectedNavIndex = rememberSaveable { mutableIntStateOf(MainNavDestination.HOME.ordinal) }

            Scaffold(
                containerColor = Color.Transparent,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                modifier = Modifier.fillMaxSize(),
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = (selectedNavIndex.intValue == MainNavDestination.HOME.ordinal),
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_home),
                                    contentDescription = null,
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = br.com.stonks.R.string.main_nav_action_home)
                                )
                            },
                            onClick = {
                                selectedNavIndex.intValue = MainNavDestination.HOME.ordinal
                                navController.navigate(MainNavDestination.HOME.route)
                            },
                        )
                        NavigationBarItem(
                            selected = navController.isCurrentDestination(MainNavDestination.STOCK),
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_radar),
                                    contentDescription = null,
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = br.com.stonks.R.string.main_nav_action_stock_alert)
                                )
                            },
                            onClick = {
                                selectedNavIndex.intValue = MainNavDestination.STOCK.ordinal
                                navController.navigate(MainNavDestination.STOCK.route)
                            },
                        )
                    }
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
                        startDestination = MainNavDestination.HOME.route,
                    ) {
                        composable(route = MainNavDestination.HOME.route) {
                            HomeScreen(
                                snackbarHostState = snackbarHostState,
                            )
                        }
                        composable(route = MainNavDestination.STOCK.route) {
                            StockAlertScreen(
                                snackbarHostState = snackbarHostState,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun NavController.isCurrentDestination(destination: MainNavDestination): Boolean {
        return this.currentDestination?.route == destination.route
    }
}

