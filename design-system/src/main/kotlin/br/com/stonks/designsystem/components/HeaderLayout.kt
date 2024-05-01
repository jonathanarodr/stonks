package br.com.stonks.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.stonks.designsystem.tokens.SpacingToken

@Composable
fun HeaderLayout(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
        HorizontalDivider(
            thickness = SpacingToken.sm,
            color = Color.Transparent,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderLayoutPreview() {
    HeaderLayout(
        title = "Saldo total",
        subtitle = "R$ 160.000,00"
    )
}
