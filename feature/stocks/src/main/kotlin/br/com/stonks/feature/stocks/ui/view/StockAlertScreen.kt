package br.com.stonks.feature.stocks.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.stonks.common.formatters.formatCurrency
import br.com.stonks.common.states.ViewModelState
import br.com.stonks.designsystem.components.EmptyStateLayout
import br.com.stonks.designsystem.components.HeaderLayout
import br.com.stonks.designsystem.components.LoadingLayout
import br.com.stonks.designsystem.components.SnackbarLayout
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.feature.stocks.R
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import br.com.stonks.feature.stocks.ui.model.StockAlertUiModel
import br.com.stonks.feature.stocks.ui.states.StockUiEvent
import br.com.stonks.feature.stocks.ui.states.StockUiState
import br.com.stonks.feature.stocks.ui.viewmodel.STOCK_VM_QUALIFIER
import br.com.stonks.feature.stocks.ui.viewmodel.StockViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
private fun StockAlertContent(
    uiModel: StockAlertUiModel,
    onEditItem: (data: AlertUiModel) -> Unit,
    modifier: Modifier = Modifier,
    onDeleteItem: (id: Long) -> Unit,
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
                title = stringResource(id = R.string.total_alert),
                subtitle = uiModel.totalAssets.formatCurrency(),
            )
        }
        items(uiModel.stockAlerts) { alert ->
            AlertCard(
                uiModel = alert,
                onEditItem = { onEditItem(alert) },
                onDeleteItem = { onDeleteItem(alert.id) },
            )
        }
    }
}

@Composable
fun StockAlertScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: ViewModelState<*, *> = koinViewModel(
        qualifier = named(STOCK_VM_QUALIFIER)
    ),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.value) {
        is StockUiState.Loading -> {
            LoadingLayout()
        }

        is StockUiState.Success -> {
            with((uiState.value as StockUiState.Success)) {
                if (data.stockAlerts.isEmpty()) {
                    EmptyStateLayout(
                        icon = br.com.stonks.designsystem.R.drawable.ic_radar,
                        message = "Nenhum alerta cadastrado",
                    )
                } else {
                    StockAlertContent(
                        uiModel = data,
                        modifier = modifier,
                        onEditItem = { data ->
                            (viewModel as StockViewModel).dispatchUiEvent(
                                StockUiEvent.RegisterAlert(data)
                            )
                        },
                        onDeleteItem = { id ->
                            (viewModel as StockViewModel).dispatchUiEvent(
                                StockUiEvent.RemoveAlert(id)
                            )
                        },
                    )
                }
            }
        }

        is StockUiState.Error -> {
            SnackbarLayout(
                snackbarHostState = snackbarHostState,
                message = "Ops, algo deu errado tente novamente",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StockAlertScreenPreview() {
    StockAlertContent(
        uiModel = StockAlertUiModel(
            totalAssets = 60000.0,
            stockAlerts = listOf(
                AlertUiModel(
                    id = 1L,
                    ticket = "GOGL34",
                    alertValue = 70.93,
                    status = StockStatusType.AVAILABLE,
                    alert = StockAlertType.HIGH_PRICE,
                    tagColor = ColorToken.HighlightGreen,
                )
            )
        ),
        onEditItem = { },
        onDeleteItem = { },
    )
}
