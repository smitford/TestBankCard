@file:OptIn(ExperimentalMaterial3Api::class)

package googl.sarafan.testbankcard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import googl.sarafan.testbankcard.features.history.presentation.compose.HistoryScreen
import googl.sarafan.testbankcard.features.search.presentation.compose.SearchScreen
import googl.sarafan.testbankcard.navigation.BankNavigationDrawer
import googl.sarafan.testbankcard.navigation.HistoryScreen
import googl.sarafan.testbankcard.navigation.NavigationItem
import googl.sarafan.testbankcard.navigation.SearchScreen
import googl.sarafan.testbankcard.uikit.theme.TestBankCardTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestBankCardTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedItemIndex by rememberSaveable {
                        mutableIntStateOf(0)
                    }

                    BankNavigationDrawer(
                        drawerState = drawerState,
                        items = items,
                        navController = navController,
                        scope = scope,
                        selectedItemIndex = selectedItemIndex
                    ) {
                        MainApp(
                            scope = scope,
                            drawerState = drawerState,
                            navController = navController,
                            changeSelectedItemIndex = { index ->
                                selectedItemIndex = index
                            }
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
        navController: NavHostController,
        changeSelectedItemIndex: (Int) -> Unit
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
                startDestination = SearchScreen,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<SearchScreen> {
                    SearchScreen(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    changeSelectedItemIndex(0)
                }

                composable<HistoryScreen> {
                    HistoryScreen(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    changeSelectedItemIndex(1)
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
                route = SearchScreen
            ),
            NavigationItem(
                title = "Urgent",
                selectedIcon = Icons.Filled.Info,
                unselectedIcon = Icons.Outlined.Info,
                badgeCount = 45,
                route = HistoryScreen
            ),
        )
    }
}



