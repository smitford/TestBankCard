package googl.sarafan.testbankcard.uikit

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun getScreenWidth(): Int {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    return screenWidth.value.toInt()
}

@Composable
fun getScreenHeight(): Int {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenHeightDp.dp
    return screenWidth.value.toInt()
}