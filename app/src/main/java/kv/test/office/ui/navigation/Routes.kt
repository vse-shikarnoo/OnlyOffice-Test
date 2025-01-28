package kv.test.office.ui.navigation

sealed class Routes(val route: String) {
    object Auth: Routes("authScreen")
}