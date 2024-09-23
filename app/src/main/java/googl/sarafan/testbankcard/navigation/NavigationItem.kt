package googl.sarafan.testbankcard.navigation


import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: Any
)


@Serializable
object ScreenA

@Serializable
object ScreenB