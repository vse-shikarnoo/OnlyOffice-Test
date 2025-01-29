package kv.test.office.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItems(
    val route:String,
    val title:String? = null,
    val icon: ImageVector? = null
) {
    data object DocsScreen: BottomNavigationItems(
        route = "docsScreen",
        title = "Documents",
        Icons.Rounded.Menu
    )
    data object RoomsScreen: BottomNavigationItems(
        route = "roomsScreen",
        title = "Rooms",
        icon = Icons.Outlined.Home
    )
    data object TrashScreen: BottomNavigationItems(
        route = "trashScreen",
        title = "Trash",
        icon = Icons.Outlined.Delete
    )
    data object ProfileScreen: BottomNavigationItems(
        route = "profileScreen",
        title = "Profile",
        icon = Icons.Outlined.AccountCircle
    )
}