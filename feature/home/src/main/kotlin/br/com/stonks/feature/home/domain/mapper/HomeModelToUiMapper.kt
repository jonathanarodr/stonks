package br.com.stonks.feature.home.domain.mapper

import br.com.stonks.common.formatters.DatePattern
import br.com.stonks.common.formatters.formatTo
import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.home.domain.model.DailyTransactionModel
import br.com.stonks.feature.home.domain.model.HomeContentModel
import br.com.stonks.feature.home.domain.model.PortfolioModel
import br.com.stonks.feature.home.domain.model.TransactionModel
import br.com.stonks.feature.home.ui.model.DailyTransactionUiModel
import br.com.stonks.feature.home.ui.model.HomeUiModel
import br.com.stonks.feature.home.ui.model.PortfolioUiModel
import br.com.stonks.feature.home.ui.model.TransactionUiModel
import br.com.stonks.feature.home.utils.getColor
import br.com.stonks.feature.home.utils.getIcon

internal class HomeModelToUiMapper(
    private val walletChartMapper: WalletModelToChartMapper,
) : Mapper<HomeContentModel, HomeUiModel> {

    override fun mapper(input: HomeContentModel) = HomeUiModel(
        totalAssets = input.wallet.totalAssets,
        portfolioChart = walletChartMapper.mapper(input.wallet),
        portfolio = input.wallet.portfolio.map(::mapperPortfolio),
        dailyTransactions = input.dailyTransactions.map(::mapperDailyGroup),
    )

    private fun mapperPortfolio(input: PortfolioModel) = PortfolioUiModel(
        tagColor = input.portfolioType.getColor(),
        portfolioName = input.portfolioName,
        totalInvestment = input.totalInvestment,
        allocation = input.allocation,
    )

    private fun mapperDailyGroup(input: DailyTransactionModel) = DailyTransactionUiModel(
        dateGroup = input.date.formatTo(DatePattern.DATE_PATTERN_DD_MMMM_BR),
        dailyBalance = input.dailyBalance,
        transactions = input.transactions.map(::mapperTransaction),
    )

    private fun mapperTransaction(input: TransactionModel) = TransactionUiModel(
        icon = input.type.getIcon(),
        description = input.description,
        value = input.value,
    )
}
