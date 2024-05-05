package br.com.stonks.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
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

data class PieChartDataProgress(
    val progress: Float,
    val progressColor: Color,
)

data class PieChartData(
    val title: String,
    val value: Double,
    val dataProgress: List<PieChartDataProgress>,
)

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
    if (pageCount == 0) {
        return
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SpacingToken.xl),
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
    data: PieChartData,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { data.dataProgress.size })

    Column(
        modifier = modifier.wrapContentSize(),
    ) {
        HorizontalPager(
            modifier = Modifier
                .wrapContentSize(),
            state = pagerState,
        ) { _ ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(FractionToken.level7)
                        .aspectRatio(FractionToken.level10),
                    contentAlignment = Alignment.Center,
                ) {
                    data.dataProgress.forEach {
                        PieChartProgress(
                            progress = it.progress,
                            progressColor = it.progressColor,
                        )
                    }
                    PieChartContent(
                        title = data.value.formatCurrency(),
                        subtitle = data.title,
                        progress = 1f,
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
private fun PieChartLayoutPreview() {
    PieChartLayout(
        data = PieChartData(
            title = "Todos os produtos",
            value = 160000.0,
            dataProgress = listOf(
                PieChartDataProgress(
                    progress = 1f,
                    progressColor = ColorToken.HighlightGreen,
                ),
                PieChartDataProgress(
                    progress = 0.7f,
                    progressColor = ColorToken.HighlightBlue,
                ),
                PieChartDataProgress(
                    progress = 0.3f,
                    progressColor = ColorToken.HighlightPurple,
                ),
            ),
        ),
    )
}
