package kv.test.office.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kv.test.office.ui.AuthScreen
import kv.test.office.ui.DocsScreen
import kv.test.office.ui.ProfileScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    onBottomBarVisibilityChanged: (Boolean) -> Unit
) {
    NavHost(navController, startDestination = Routes.Auth.route) {
        composable(Routes.Auth.route) {
            onBottomBarVisibilityChanged(false)
            AuthScreen(onNavigate = {
                navController.navigate(BottomNavigationItems.DocsScreen.route)
            })
        }
        composable(BottomNavigationItems.DocsScreen.route) {
            onBottomBarVisibilityChanged(true)
            DocsScreen()
        }
        composable(BottomNavigationItems.ProfileScreen.route) {
            onBottomBarVisibilityChanged(true)
            ProfileScreen()
        }
    }
}