@file:OptIn(ExperimentalMaterial3Api::class)

package googl.sarafan.testbankcard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import googl.sarafan.testbankcard.history.presentation.compose.HistoryScreen
import googl.sarafan.testbankcard.navigation.BankNavigationDrawer
import googl.sarafan.testbankcard.navigation.NavigationItem
import googl.sarafan.testbankcard.navigation.ScreenA
import googl.sarafan.testbankcard.navigation.ScreenB
import googl.sarafan.testbankcard.search.presentation.compose.SearchScreen
import googl.sarafan.testbankcard.ui.theme.TestBankCardTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestBankCardTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    BankNavigationDrawer(
                        drawerState = drawerState,
                        items = items,
                        navController = navController,
                        scope = scope
                    ) {
                        MainApp(
                            scope = scope,
                            drawerState = drawerState,
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun MainApp(
        scope: CoroutineScope,
        drawerState: DrawerState,
        navController: NavHostController
    ) {

        Scaffold(
            topBar = {
                BasicTopBar(
                    scope = scope,
                    drawerState = drawerState
                )
            }
        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = ScreenA,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<ScreenA> {
                    SearchScreen(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable<ScreenB> {
                    HistoryScreen(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }

    @Composable
    fun BasicTopBar(
        scope: CoroutineScope,
        drawerState: DrawerState
    ) {
        TopAppBar(
            title = {
                Text(text = "Todo App")
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        )
    }

    companion object {
        val items = listOf(
            NavigationItem(
                title = "All",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = ScreenA
            ),
            NavigationItem(
                title = "Urgent",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info,
                badgeCount = 45,
                route = ScreenB
            ),
        )
    }
}



