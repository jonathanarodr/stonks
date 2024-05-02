package br.com.stonks.feature.home.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import br.com.stonks.common.formatters.formatCurrency
import br.com.stonks.common.formatters.formatPercent
import br.com.stonks.designsystem.components.TagLayout
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.feature.home.R
import br.com.stonks.feature.home.ui.model.PortfolioUiModel

@Composable
internal fun PortfolioCard(
    uiModel: PortfolioUiModel,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Box(
            modifier = Modifier
                .background(ColorToken.NeutralWhite)
                .padding(SpacingToken.xl)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TagLayout(
                        modifier = Modifier
                            .padding(end = SpacingToken.md),
                        containerColor = uiModel.tagColor,
                        contentColor = ColorToken.NeutralWhite,
                        label = uiModel.allocation.formatPercent(),
                    )
                    Text(
                        text = uiModel.portfolioName,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = SpacingToken.lg),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(id = R.string.total_balance),
                        style = MaterialTheme.typography.titleSmall,
                        color = ColorToken.Grayscale300,
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = uiModel.totalInvestment.formatCurrency(),
                        style = MaterialTheme.typography.titleSmall,
                        color = ColorToken.Grayscale300,
                        textAlign = TextAlign.End,
                    )
                }
                TextButton(
                    modifier = Modifier.wrapContentSize(),
                    contentPadding = PaddingValues(top = SpacingToken.lg),
                    shape = RoundedCornerShape(SpacingToken.none),
                    onClick = {}
                ) {
                    Text(
                        text = stringResource(id = R.string.navigate_to_produtcts),
                    )
                    Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PortfolioLayoutPreview() {
    Column(
        modifier = Modifier.padding(SpacingToken.xl)
    ) {
        PortfolioCard(
            uiModel = PortfolioUiModel(
                tagColor = ColorToken.HighlightGreen,
                portfolioName = "Ações",
                totalInvestment = 1000.0,
                allocation = 0.3043f,
            )
        )
    }
}
