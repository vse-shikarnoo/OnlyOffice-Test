package kv.test.office.ui.navigation

sealed class Routes(val route: String) {
    data object Auth: Routes("authScreen")
}