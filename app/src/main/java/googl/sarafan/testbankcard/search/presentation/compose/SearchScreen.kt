package googl.sarafan.testbankcard.search.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import googl.sarafan.testbankcard.ui.theme.TestBankCardTheme

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {

    }

}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    TestBankCardTheme {
        SearchScreen()
    }
}