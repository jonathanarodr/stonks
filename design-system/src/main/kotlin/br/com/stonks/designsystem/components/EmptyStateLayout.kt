package br.com.stonks.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.stonks.designsystem.R
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken

@Composable
fun EmptyStateLayout(
    @DrawableRes icon: Int,
    message: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(SpacingToken.xl),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.FillWidth,
            )
            Spacer(modifier = Modifier.height(SpacingToken.xl))
            Text(
                text = message,
                textAlign = TextAlign.Center,
                color = ColorToken.Grayscale300,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyStateLayoutPreview() {
    EmptyStateLayout(
        icon = R.drawable.ic_broken_image,
        message = "Ops, algo deu errado tente novamente",
    )
}
