package googl.sarafan.testbankcard.uikit.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import googl.sarafan.testbankcard.uikit.theme.TestBankCardTheme

@Composable
fun TextLabeledColumn(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    valueStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    labelTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    labelTextColor: Color = MaterialTheme.colorScheme.outline,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = buildString {
                append(label.uppercase())
                append(":")
            },
            style = labelTextStyle.copy(color = labelTextColor)
        )

        Text(
            text = value,
            style = valueStyle.copy(color = valueColor)
        )

        Spacer(Modifier.height(8.dp))
        content()
    }
}

@Composable
fun TextLabeledRow(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    valueStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    labelTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    labelTextColor: Color = MaterialTheme.colorScheme.outline,
    content: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier.wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buildString {
                append(label.uppercase())
                append(": ")
            },
            style = labelTextStyle.copy(color = labelTextColor)
        )

        Text(
            text = value,
            style = valueStyle.copy(color = valueColor),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(4.dp))
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun TextLabeledPreview() {
    TestBankCardTheme {
        TextLabeledColumn(label = "Label", value = "Value")
    }
}