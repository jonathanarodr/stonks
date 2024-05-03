package br.com.stonks.feature.stocks.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.stonks.common.formatters.formatCurrency
import br.com.stonks.designsystem.components.TagLayout
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.feature.stocks.R
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import br.com.stonks.feature.stocks.utils.getDescription
import kotlinx.coroutines.launch

@Composable
private fun AlertForms(
    uiModel: AlertUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(SpacingToken.xl)
    ) {
        OutlinedTextField(
            value = uiModel.ticket,
            onValueChange = { },
            enabled = false,
            label = { Text("Ticket") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(SpacingToken.lg))

        OutlinedTextField(
            value = uiModel.alertValue.formatCurrency(),
            onValueChange = {  },
            enabled = false,
            label = { Text("Alertar em") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AlertActions(
    uiModel: AlertUiModel,
    onEditItem: (data: AlertUiModel) -> Unit,
    onDeleteItem: (id: Long) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    IconButton(
        onClick = { onDeleteItem(uiModel.id) },
    ) {
        Icon(
            painter = painterResource(
                id = br.com.stonks.designsystem.R.drawable.ic_trash
            ),
            contentDescription = stringResource(id = R.string.alert_action_delete),
        )
    }
    IconButton(
        onClick = { showBottomSheet = true },
    ) {
        Icon(
            painter = painterResource(
                id = br.com.stonks.designsystem.R.drawable.ic_edit
            ),
            contentDescription = stringResource(id = R.string.alert_action_edit),
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier,
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false },
        ) {
            AlertForms(
                uiModel = uiModel,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpacingToken.xl),
                horizontalArrangement = Arrangement.End,
            ) {
                OutlinedButton(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onEditItem(uiModel)
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text(stringResource(id = R.string.alert_action_close))
                }
            }
        }
    }
}

@Composable
private fun AlertLayoutTitle(
    uiModel: AlertUiModel,
    modifier: Modifier = Modifier,
    onEditItem: (data: AlertUiModel) -> Unit,
    onDeleteItem: (id: Long) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = uiModel.ticket,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            TagLayout(
                modifier = Modifier.padding(start = SpacingToken.md),
                containerColor = uiModel.tagColor,
                contentColor = ColorToken.NeutralWhite,
                label = stringResource(id = uiModel.status.getDescription()),
            )
        }
        AlertActions(
            uiModel = uiModel,
            onEditItem = { onEditItem(uiModel) },
            onDeleteItem = { onDeleteItem(uiModel.id) },
        )
    }
}

@Composable
private fun AlertLayoutContent(
    alertValue: Double,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = br.com.stonks.designsystem.R.drawable.ic_broken_image),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier.padding(start = SpacingToken.lg),
        ) {
            Text(
                text = stringResource(id = R.string.alert_price),
                style = MaterialTheme.typography.titleSmall,
                color = ColorToken.Grayscale300,
            )
            Spacer(modifier = Modifier.height(SpacingToken.xs))
            Text(
                text = alertValue.formatCurrency(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
            )
        }
    }
}

@Composable
internal fun AlertCard(
    uiModel: AlertUiModel,
    modifier: Modifier = Modifier,
    onEditItem: (data: AlertUiModel) -> Unit,
    onDeleteItem: (id: Long) -> Unit,
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier
                .background(ColorToken.NeutralWhite)
                .padding(SpacingToken.xl)
        ) {
            AlertLayoutTitle(
                uiModel = uiModel,
                onEditItem = { onEditItem(uiModel) },
                onDeleteItem = { onDeleteItem(uiModel.id) },
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = SpacingToken.lg),
                color = ColorToken.Grayscale100,
            )
            AlertLayoutContent(
                alertValue = uiModel.alertValue,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlertLayoutPreview() {
    Column(
        modifier = Modifier.padding(SpacingToken.xl)
    ) {
        AlertCard(
            uiModel = AlertUiModel(
                id = 1L,
                ticket = "GOGL34",
                alertValue = 71.0,
                status = StockStatusType.AVAILABLE,
                alert = StockAlertType.HIGH_PRICE,
                tagColor = ColorToken.HighlightGreen,
            ),
            onEditItem = { },
            onDeleteItem = { },
        )
    }
}
