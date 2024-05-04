package br.com.stonks.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.stonks.designsystem.tokens.ColorToken

@Composable
fun LoadingLayout(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ColorToken.NeutralWhite)
    ) {
        CircularProgressIndicator(
            color = ColorToken.BrandGreenDark,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    LoadingLayout()
}
