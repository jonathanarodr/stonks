package br.com.stonks.feature.home.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.designsystem.components.PieChartData
import br.com.stonks.designsystem.components.PieChartDataProgress
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.home.domain.model.WalletModel
import br.com.stonks.feature.home.utils.getColor

private const val SummarizedItemIndex = 0

internal class WalletModelToChartMapper : Mapper<WalletModel, List<PieChartData>> {

    override fun mapper(input: WalletModel): List<PieChartData> {
        val dataChart = mutableListOf<PieChartData>()
        val dataProgress = mutableListOf<PieChartDataProgress>()
        var incrementalProgress = 0f

        input.portfolio.forEach { portfolio ->
            incrementalProgress += portfolio.allocation

            dataChart.add(
                PieChartData(
                    title = portfolio.portfolioName,
                    value = portfolio.totalInvestment,
                    progress = portfolio.allocation,
                    dataProgress = listOf(
                        PieChartDataProgress(
                            progress = portfolio.allocation,
                            progressColor = portfolio.portfolioType.getColor(),
                            trackColor = ColorToken.Grayscale100,
                        )
                    ),
                )
            )

            dataProgress.add(
                PieChartDataProgress(
                    progress = incrementalProgress,
                    progressColor = portfolio.portfolioType.getColor()
                )
            )
        }

        val assetsChartData = PieChartData(
            title = "Todos os produtos",
            value = input.totalAssets,
            progress = 1f,
            dataProgress = dataProgress.sortedByDescending { it.progress },
        )

        dataChart.add(SummarizedItemIndex, assetsChartData)

        return dataChart
    }
}
