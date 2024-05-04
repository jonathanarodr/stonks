package br.com.stonks.feature.stocks.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import br.com.stonks.common.db.DEFAULT_PRIMARY_KEY
import br.com.stonks.common.formatters.formatCurrency
import br.com.stonks.common.formatters.toCurrency
import br.com.stonks.designsystem.components.HeaderLayout
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.SpacingToken
import br.com.stonks.feature.stocks.R
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import br.com.stonks.feature.stocks.utils.getDescription

@Composable
internal fun AlertFormsScreen(
    uiModel: AlertUiModel? = null,
    modifier: Modifier = Modifier,
    onSaveItem: (alert: AlertUiModel) -> Unit,
    onDismiss: () -> Unit,
) {
    val title by remember {
        mutableIntStateOf(
            if (uiModel == null) {
                R.string.alert_action_create
            } else {
                R.string.alert_action_edit
            }
        )
    }

    val focusManager = LocalFocusManager.current
    var ticket by remember { mutableStateOf(uiModel?.ticket.orEmpty()) }
    var price by remember { mutableDoubleStateOf(uiModel?.alertValue ?: 0.0) }
    var statusAvailable by remember { mutableStateOf(uiModel?.status == StockStatusType.AVAILABLE) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpacingToken.xl)
    ) {
        HeaderLayout(
            title = stringResource(id = title)
        )

        Spacer(modifier = Modifier.height(SpacingToken.lg))

        OutlinedTextField(
            value = ticket,
            onValueChange = { ticket = it },
            label = { Text(text = stringResource(id = R.string.ticket_name)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(SpacingToken.lg))

        OutlinedTextField(
            value = price.formatCurrency(),
            onValueChange = {
                price = it.toCurrency()
                focusManager.moveFocus(FocusDirection.Right)
            },
            label = { Text(stringResource(id = R.string.alert_on)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(SpacingToken.lg))

        FilterChip(
            onClick = { statusAvailable = !statusAvailable },
            label = {
                if (statusAvailable) {
                    Text(stringResource(id = StockStatusType.AVAILABLE.getDescription()))
                } else {
                    Text(stringResource(id = StockStatusType.UNAVAILABLE.getDescription()))
                }
            },
            selected = statusAvailable,
            leadingIcon = if (statusAvailable) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = null,
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = SpacingToken.xl),
            color = ColorToken.Grayscale100,
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onSaveItem(
                    AlertUiModel(
                        id = uiModel?.id ?: DEFAULT_PRIMARY_KEY,
                        ticket = ticket,
                        alertValue = price,
                        status = if (statusAvailable) StockStatusType.AVAILABLE else StockStatusType.UNAVAILABLE
                    )
                )
            }
        ) {
            Text(text = stringResource(id = R.string.alert_action_save))
        }

        Spacer(modifier = Modifier.height(SpacingToken.md))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onDismiss,
        ) {
            Text(text = stringResource(id = R.string.alert_action_cancel))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AlertFormsLayoutPreview() {
    AlertFormsScreen(
        uiModel = AlertUiModel(
            id = 1L,
            ticket = "GOGL34",
            alertValue = 70.93,
            status = StockStatusType.AVAILABLE,
            alert = StockAlertType.HIGH_PRICE,
            tagColor = ColorToken.HighlightGreen,
        ),
        onSaveItem = { },
        onDismiss = { },
    )
}
