package br.com.stonks.designsystem.components

import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.stonks.designsystem.tokens.ColorToken

@Composable
private fun TagLayout(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    label: String,
) {
    Badge(
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
    ) {
        Text(
            text = label,
        )
    }
}

@Preview
@Composable
private fun TagLayoutPreview() {
    TagLayout(
        containerColor = ColorToken.HighlightGreen,
        contentColor = ColorToken.NeutralWhite,
        label = "Ações",
    )
}
