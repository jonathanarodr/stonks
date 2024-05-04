package br.com.stonks.feature.stocks.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.stonks.common.db.DEFAULT_PRIMARY_KEY
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
import timber.log.Timber

@Composable
private fun StockAlertHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    HeaderLayout(
        title = title,
        subtitle = subtitle,
    )

    Spacer(modifier = Modifier.height(SpacingToken.lg))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = {
                onClick()
            },
        ) {
            Icon(
                painter = painterResource(id = br.com.stonks.designsystem.R.drawable.ic_alert),
                contentDescription = null,
            )
            Text(text = stringResource(id = R.string.alert_action_create))
        }
    }
}

@Composable
private fun StockAlertContent(
    uiModel: StockAlertUiModel,
    modifier: Modifier = Modifier,
    onSaveItem: (alert: AlertUiModel) -> Unit,
    onDeleteItem: (id: Long) -> Unit,
) {
    var openAlertScreen by remember { mutableStateOf(false) }
    var alarmId by remember { mutableLongStateOf(DEFAULT_PRIMARY_KEY) }

    if (openAlertScreen) {
        AlertFormsScreen(
            uiModel = uiModel.stockAlerts.find { it.id == alarmId },
            onSaveItem = {
                onSaveItem(it)
                alarmId = DEFAULT_PRIMARY_KEY
                openAlertScreen = false
            },
            onDismiss = {
                alarmId = DEFAULT_PRIMARY_KEY
                openAlertScreen = false
            },
        )

        return
    }

    if (uiModel.stockAlerts.isEmpty()) {
        Column(
            modifier = modifier
                .padding(SpacingToken.xl)
                .background(ColorToken.NeutralWhite),
        ) {
            StockAlertHeader(
                title = stringResource(id = R.string.total_alert),
                subtitle = uiModel.totalAssets.formatCurrency(),
                onClick = { openAlertScreen = true },
            )
            EmptyStateLayout(
                icon = br.com.stonks.designsystem.R.drawable.ic_radar,
                message = "Nenhum alerta cadastrado",
            )
        }

        return
    }

    LazyColumn(
        modifier = modifier.background(ColorToken.NeutralWhite),
        contentPadding = PaddingValues(SpacingToken.xl),
        verticalArrangement = Arrangement.spacedBy(
            space = SpacingToken.xl,
        ),
    ) {
        item {
            StockAlertHeader(
                title = stringResource(id = R.string.total_alert),
                subtitle = uiModel.totalAssets.formatCurrency(),
                onClick = { openAlertScreen = true },
            )
        }
        items(uiModel.stockAlerts) { alert ->
            AlertCard(
                uiModel = alert,
                onEditItem = {
                    alarmId = it
                    openAlertScreen = true
                },
                onDeleteItem = {
                    alarmId = DEFAULT_PRIMARY_KEY
                    onDeleteItem(alert.id)
                },
            )
        }
    }
}

@Composable
fun StockAlertScreen(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    viewModel: ViewModelState<*, *> = koinViewModel(
        qualifier = named(STOCK_VM_QUALIFIER),
    ),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.value) {
        is StockUiState.Loading -> {
            LoadingLayout()
        }

        is StockUiState.Success -> {
            with((uiState.value as StockUiState.Success)) {
                StockAlertContent(
                    uiModel = data,
                    modifier = modifier.background(ColorToken.NeutralWhite),
                    onSaveItem = { alert ->
                        (viewModel as StockViewModel).dispatchUiEvent(
                            StockUiEvent.SaveAlert(alert)
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
        onSaveItem = { },
        onDeleteItem = { },
    )
}
