package googl.sarafan.testbankcard.uikit.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun EditTextWithComments(
    value: String,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    editTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    editTextColor: Color = MaterialTheme.colorScheme.onSurface,
    hintTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    hintTextColor: Color = MaterialTheme.colorScheme.outline,
    labelTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    labelTextColor: Color = MaterialTheme.colorScheme.outline,
    keyboardType: KeyboardType = KeyboardType.NumberPassword,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    Column(modifier = modifier) {

        Surface(
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                value = value,
                onValueChange = onValueChange,
                textStyle = editTextStyle.copy(color = editTextColor),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onSecondaryContainer),
                enabled = enabled,
                readOnly = readOnly,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                visualTransformation = visualTransformation,
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                modifier = Modifier.align(Alignment.CenterStart),
                                text = hint,
                                style = hintTextStyle.copy(color = hintTextColor)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            innerTextField()
                            Spacer(modifier = Modifier.width(4.dp))
                            if (value.isNotBlank()) {
                                IconButton(onClick = { onValueChange("") }) {
                                    Icon(
                                        modifier = Modifier.size(16.dp),
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                },
                singleLine = true,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = label,
            style = labelTextStyle.copy(color = labelTextColor)
        )
    }
}

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText = text.text.filter { it.isDigit() }
        val transformedText = trimmedText.chunked(4).joinToString(" ")

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val numSpacesBeforeOffset = (offset / 3f).roundToInt()
                val transformedOffset = offset + numSpacesBeforeOffset
                return transformedOffset.coerceAtMost(transformedText.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val numSpacesBeforeOffset = (offset / 4f).roundToInt()
                val transformedOffset = offset - numSpacesBeforeOffset
                return transformedOffset.coerceAtMost(transformedText.length)
            }
        }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }
}


@Preview(showBackground = true)
@Composable
fun EditTextWithCommentsPreview() {
    var state by remember { mutableStateOf("") }

    EditTextWithComments(
        modifier = Modifier.size(width = 200.dp, height = 100.dp),
        hint = "0000 0000",
        onValueChange = { text ->
            state = text
        },
        value = state,
        label = "Subscription"
    )
}