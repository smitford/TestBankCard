package googl.sarafan.testbankcard.uikit.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import googl.sarafan.testbankcard.R
import googl.sarafan.testbankcard.uikit.getScreenHeight
import googl.sarafan.testbankcard.uikit.getScreenWidth
import googl.sarafan.testbankcard.uikit.theme.TestBankCardTheme

@Composable
fun DropDownTableItem(
    value: List<String>,
    description: List<String>,
    descriptionTitle: List<String>,
    modifier: Modifier = Modifier,
    ) {
    var isDescriptionVisible by remember { mutableStateOf(false) }
    val iconSize = 24
    val valueWidth = (getScreenWidth() - iconSize) / (if (value.size > 5) 5 else value.size)
    val descriptionHeight = getScreenHeight() / 5

    Column(
        modifier = modifier.scrollable(
            state = rememberScrollState(),
            enabled = true,
            orientation = Orientation.Horizontal
        )
    ) {
        Row(
            modifier = Modifier.clickable {
                isDescriptionVisible = !isDescriptionVisible
            }
        ) {
            Icon(
                imageVector =
                if (isDescriptionVisible)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.width(iconSize.dp)
            )
            value.forEach {
                Text(
                    text = it,
                    modifier = Modifier.width(valueWidth.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        AnimatedVisibility(visible = isDescriptionVisible, enter = slideInVertically()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .height(descriptionHeight.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
            ) {
                itemsIndexed(description) { index, item ->
                    TextLabeledRow(
                        label = descriptionTitle[index],
                        value = item,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownTableItemPreview() {
    TestBankCardTheme {
        val descriptionTitles = listOf(
            stringResource(R.string.card_type),
            stringResource(R.string.bank_url),
            stringResource(R.string.bank_phone),
            stringResource(R.string.city),
            stringResource(R.string.latitude),
            stringResource(R.string.longitude)
        )
        DropDownTableItem(
            value = listOf("2345 3456", "visa", "SBER", "Russia"),
            description = listOf("debit", "bankUrl", "8 912 374 85 34", "bankCity", "49", "32"),
            descriptionTitle = descriptionTitles,
        )
    }
}
