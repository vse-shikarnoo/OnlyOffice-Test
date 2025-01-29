package kv.test.office.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kv.test.office.ui.auth.AuthScreen
import kv.test.office.ui.docs.DocsScreen
import kv.test.office.ui.profile.ProfileScreen

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
            ProfileScreen(
                onNavigate = {
                    navController.navigate(Routes.Auth.route)
                }
            )
        }
    }
}