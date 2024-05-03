package br.com.stonks.feature.stocks.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.stonks.common.formatters.formatCurrency
import br.com.stonks.common.states.ViewModelState
import br.com.stonks.designsystem.components.HeaderLayout
import br.com.stonks.designsystem.components.SnackbarLayout
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.feature.stocks.R
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import br.com.stonks.feature.stocks.ui.model.StockAlertUiModel
import br.com.stonks.feature.stocks.ui.states.StockUiState
import br.com.stonks.feature.stocks.ui.viewmodel.STOCK_VM_QUALIFIER
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named
import timber.log.Timber

@Composable
private fun StockAlertContent(
    uiModel: StockAlertUiModel,
    modifier: Modifier = Modifier,
    onEditItem: () -> Unit,
    onDeleteItem: () -> Unit,
) {
    LazyColumn(
        modifier = modifier,
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
                onEditItem = { onEditItem() },
                onDeleteItem = { onDeleteItem() },
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
    var selected by remember { mutableStateOf(false) }

    when (uiState.value) {
        is StockUiState.Loading -> {
            Timber.i("Loading stock alert screen...")
        }

        is StockUiState.Success -> {
            StockAlertContent(
                uiModel = (uiState.value as StockUiState.Success).data,
                modifier = modifier,
                onEditItem = { },
                onDeleteItem = { },
            )
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
