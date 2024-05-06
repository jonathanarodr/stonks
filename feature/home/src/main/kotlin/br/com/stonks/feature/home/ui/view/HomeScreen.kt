package br.com.stonks.feature.home.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.stonks.common.formatters.formatCurrency
import br.com.stonks.common.states.ViewModelState
import br.com.stonks.designsystem.components.HeaderLayout
import br.com.stonks.designsystem.components.LoadingLayout
import br.com.stonks.designsystem.components.PieChartData
import br.com.stonks.designsystem.components.PieChartDataProgress
import br.com.stonks.designsystem.components.PieChartLayout
import br.com.stonks.designsystem.components.SnackbarLayout
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.feature.home.R
import br.com.stonks.feature.home.ui.model.DailyTransactionUiModel
import br.com.stonks.feature.home.ui.model.HomeUiModel
import br.com.stonks.feature.home.ui.model.PortfolioUiModel
import br.com.stonks.feature.home.ui.model.TransactionUiModel
import br.com.stonks.feature.home.ui.states.HomeUiState
import br.com.stonks.feature.home.ui.viewmodel.HOME_VM_QUALIFIER
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
private fun SessionDivider(
    thickness: Dp = SpacingToken.sm,
) {
    Spacer(modifier = Modifier.height(thickness))
}

@Composable
private fun HomeContent(
    uiModel: HomeUiModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.background(ColorToken.NeutralWhite),
        contentPadding = PaddingValues(SpacingToken.xl),
        verticalArrangement = Arrangement.spacedBy(
            space = SpacingToken.xl,
        ),
    ) {
        item {
            HeaderLayout(
                title = stringResource(id = R.string.total_balance),
                subtitle = uiModel.totalAssets.formatCurrency(),
            )
        }
        item {
            SessionDivider()
            Text(
                text = stringResource(id = R.string.wallet_title),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        item {
            SessionDivider()
            PieChartLayout(
                data = uiModel.portfolioChart.toImmutableList(),
            )
        }
        items(uiModel.portfolio) {
            PortfolioCard(
                uiModel = it,
            )
        }
        items(uiModel.dailyTransactions) { group ->
            SessionDivider()
            TransactionGroupLayout(
                title = group.dateGroup,
                subtitle = stringResource(id = R.string.daily_balance, group.dailyBalance.formatCurrency()),
            )
            group.transactions.forEach { transaction ->
                TransactionItemLayout(
                    icon = transaction.icon,
                    description = transaction.description,
                    value = transaction.value.formatCurrency(),
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: ViewModelState<*, *> = koinViewModel(qualifier = named(HOME_VM_QUALIFIER)),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.value) {
        is HomeUiState.Loading -> {
            LoadingLayout()
        }

        is HomeUiState.Success -> {
            HomeContent(
                uiModel = (uiState.value as HomeUiState.Success).data,
                modifier = modifier,
            )
        }

        is HomeUiState.Error -> {
            SnackbarLayout(
                snackbarHostState = snackbarHostState,
                message = "Ops, algo deu errado tente novamente",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeContent(
        uiModel = HomeUiModel(
            totalAssets = 166300.0,
            portfolioChart = listOf(
                PieChartData(
                    title = "Todos os produtos",
                    value = 160000.0,
                    progress = 1f,
                    dataProgress = listOf(
                        PieChartDataProgress(
                            progress = 1f,
                            progressColor = ColorToken.HighlightGreen,
                        ),
                    ),
                ),
            ),
            portfolio = listOf(
                PortfolioUiModel(
                    tagColor = ColorToken.HighlightGreen,
                    portfolioName = "Ações",
                    totalInvestment = 1000.0,
                    allocation = 0.3043f,
                )
            ),
            dailyTransactions = listOf(
                DailyTransactionUiModel(
                    dateGroup = "05 de Maio",
                    dailyBalance = 100.0,
                    transactions = listOf(
                        TransactionUiModel(
                            icon = br.com.stonks.designsystem.R.drawable.ic_income,
                            description = "Resgate",
                            value = 100.0,
                        )
                    ),
                )
            )
        )
    )
}
