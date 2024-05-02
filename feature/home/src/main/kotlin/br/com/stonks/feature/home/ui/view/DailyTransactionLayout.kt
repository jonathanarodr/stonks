package br.com.stonks.feature.home.ui.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.feature.home.R

@Composable
internal fun TransactionGroupLayout(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = SpacingToken.none),
    ) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.ExtraBold,
            ),
        )
        Text(
            text = subtitle,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall,
            color = ColorToken.Grayscale300,
        )
    }
}

@Composable
internal fun TransactionItemLayout(
    @DrawableRes icon: Int,
    description: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = SpacingToken.xl),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.padding(end = SpacingToken.md),
            tint = ColorToken.Grayscale300,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.titleSmall,
            color = ColorToken.Grayscale300,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = value,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = ColorToken.Grayscale300,
            textAlign = TextAlign.End,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyTransactionLayoutPreview() {
    Column(
        modifier = Modifier.padding(SpacingToken.xl)
    ) {
        TransactionGroupLayout(
            title = "05 de janeiro",
            subtitle = stringResource(id = R.string.daily_balance, "R$ 160.000,00"),
        )
        TransactionItemLayout(
            icon = br.com.stonks.designsystem.R.drawable.ic_income,
            description = "DIVIDENDOS ITSA4",
            value = "R$ 2.030,01",
        )
        HorizontalDivider(
            color = ColorToken.Grayscale100,
        )
        TransactionItemLayout(
            icon = br.com.stonks.designsystem.R.drawable.ic_expensive,
            description = "RESGATE APLICAÇÃO",
            value = "R$ 2.000,00",
        )
        HorizontalDivider(
            color = ColorToken.Grayscale100,
        )
    }
}
