package kv.test.office.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route:String,
    val title:String? = null,
    val icon: ImageVector? = null
) {
    data object DocsScreen: BottomNavigationItems(
        route = "docsScreen",
        title = "DocsScreen",
        icon = Icons.Outlined.Home
    )
    data object ProfileScreen: BottomNavigationItems(
        route = "profileScreen",
        title = "ProfileScreen",
        icon = Icons.Outlined.AccountCircle
    )
}