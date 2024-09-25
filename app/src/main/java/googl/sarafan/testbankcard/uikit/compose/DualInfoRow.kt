package googl.sarafan.testbankcard.uikit.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


@Composable
fun DualInfoRow(
    firstLabel: String,
    firstValue: String,
    secondLabel: String,
    secondValue: String,
    modifier: Modifier = Modifier,
    innerSpacer: Char = ':',
    outerSpacer: Char = ';',
    bracketStart: Char = '(',
    bracketEnd: Char = ')',
    textStyle: TextStyle =  MaterialTheme.typography.bodyMedium,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable {
            onClick()
        }
    ) {
        val text = StringBuilder()
        text.append(bracketStart, firstLabel, innerSpacer, " ", firstValue, outerSpacer)
        text.append(" ", secondLabel, innerSpacer, " ", secondValue, bracketEnd)
        Text(
            text = text.toString(),
            modifier = modifier,
            style = textStyle.copy(color = textColor)
        )
    }
}
