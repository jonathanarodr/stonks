package br.com.stonks.feature.home.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.home.domain.model.PortfolioModel
import br.com.stonks.feature.home.domain.model.WalletModel
import br.com.stonks.feature.home.domain.types.PortfolioType
import br.com.stonks.feature.home.repository.remote.response.PortfolioResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse

internal class WalletMapper : Mapper<WalletResponse, WalletModel> {

    override fun mapper(input: WalletResponse) = WalletModel(
        availableBalance = input.availableBalance,
        investedBalance = input.investedBalance,
        totalAssets = input.totalAssets,
        portfolio = input.portfolio.map(::mapperPortfolio),
    )

    private fun mapperPortfolio(input: PortfolioResponse) = PortfolioModel(
        portfolioName = input.portfolioName,
        portfolioType = PortfolioType.fromString(input.portfolioType),
        totalInvestment = input.totalInvestment,
        allocation = input.allocation,
    )
}
