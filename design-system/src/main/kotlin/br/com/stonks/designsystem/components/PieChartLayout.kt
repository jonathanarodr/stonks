package br.com.stonks.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.stonks.common.formatters.formatCurrency
import br.com.stonks.common.formatters.formatPercent
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.FractionToken
import br.com.stonks.designsystem.tokens.SpacingToken

data class PieChartData(
    val title: String,
    val value: Double,
    val progress: Float,
    val progressColor: Color,
)

data class PieChartDataProgress(
    val progress: Float,
    val progressColor: Color,
)

data class PieChartDataContent(
    val title: String,
    val value: Double,
    val progress: Float,
    val progressColor: Color,
    val dataProgress: List<PieChartDataProgress> = emptyList(),
)

class PieChartDataProcess {

    operator fun invoke(entry: List<PieChartData>): List<PieChartDataContent> {
        val dataProgress = mutableListOf<PieChartDataProgress>()
        var incrementalProgress = 0f
        var incrementalValue = 0.0

        val dataSet = entry.map {
            incrementalProgress += it.progress
            incrementalValue += it.value

            dataProgress.add(
                PieChartDataProgress(
                    progress = incrementalProgress,
                    progressColor = it.progressColor,
                )
            )

            PieChartDataContent(
                title = it.title,
                value = it.value,
                progress = it.progress,
                progressColor = it.progressColor,
            )
        }

        dataSet.toMutableList().add(
            index = 0,
            element = PieChartDataContent(
                title = "Todos os produtos",
                value = incrementalValue,
                progress = incrementalProgress,
                progressColor = ColorToken.BrandGreenLight,
                dataProgress = dataProgress,
            )
        )

        return dataSet
    }
}

@Composable
private fun PieChartContent(
    title: String,
    subtitle: String,
    progress: Float,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpacingToken.xl),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = progress.formatPercent(),
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleSmall,
            color = ColorToken.Grayscale300,
        )
        Spacer(modifier = Modifier.height(SpacingToken.md))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
private fun PieChartProgress(
    progress: Float,
    progressColor: Color,
) {
    CircularProgressIndicator(
        progress = { progress },
        modifier = Modifier.fillMaxSize(),
        strokeWidth = 20.dp,
        color = progressColor,
        trackColor = Color.Transparent,
        strokeCap = StrokeCap.Round,
    )
}

@Composable
private fun PieChartSubtitle(
    currentPage: Int,
    pageCount: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpacingToken.xl),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "1",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
        )
        LinearProgressIndicator(
            progress = { (currentPage.toFloat() + 1) / pageCount },
            modifier = Modifier
                .width(40.dp)
                .padding(horizontal = SpacingToken.xs),
            color = ColorToken.Grayscale300,
            trackColor = ColorToken.Grayscale100,
            strokeCap = StrokeCap.Round,
        )
        Text(
            text = pageCount.toString(),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PieChartLayout(
    data: List<PieChartDataContent>,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { data.size })

    Column(
        modifier = modifier.wrapContentSize(),
    ) {
        HorizontalPager(
            modifier = modifier
                .wrapContentSize()
                .background(ColorToken.Grayscale100),
            state = pagerState,
        ) { pageIndex ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(FractionToken.level7)
                        .aspectRatio(1f)
                        .border(1.dp, Color.Blue),
                    contentAlignment = Alignment.Center,
                ) {
                    data[pageIndex].dataProgress.forEach {
                        PieChartProgress(
                            progress = data[pageIndex].progress,
                            progressColor = data[pageIndex].progressColor,
                        )
                    }
                    PieChartContent(
                        progress = data[pageIndex].progress,
                        title = data[pageIndex].title,
                        subtitle = data[pageIndex].value.formatCurrency(),
                    )
                }
            }
        }
        PieChartSubtitle(
            currentPage = pagerState.currentPage,
            pageCount = pagerState.pageCount,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartLayoutPreview() {
    val data = PieChartDataProcess().invoke(listOf(
        PieChartData(
            title = "Conta Investimento",
            value = 6300.0,
            progress = 0.0379f,
            progressColor = ColorToken.HighlightGreen,
        ),
        PieChartData(
            title = "Conta Investimento",
            value = 6300.0,
            progress = 0.0379f,
            progressColor = ColorToken.HighlightGreen,
        ),
        PieChartData(
            title = "Fundos de Investimentos",
            value = 96000.0,
            progress = 0.5773f,
            progressColor = ColorToken.HighlightBlue,
        ),
        PieChartData(
            title = "Tesouro Direto e Títulos Públicos",
            value = 32000.0,
            progress = 0.1924f,
            progressColor = ColorToken.HighlightPurple,
        ),
        PieChartData(
            title = "Ações",
            value = 32000.0,
            progress = 0.1924f,
            progressColor = ColorToken.HighlightRed,
        ),
    ))

    PieChartLayout(
        data = data,
    )
}
