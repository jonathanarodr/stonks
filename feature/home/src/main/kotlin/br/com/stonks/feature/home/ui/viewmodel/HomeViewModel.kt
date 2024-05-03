package br.com.stonks.feature.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.stonks.common.states.ViewModelState
import br.com.stonks.feature.home.domain.usecase.HomeContentUseCase
import br.com.stonks.feature.home.ui.mapper.HomeUiMapper
import br.com.stonks.feature.home.ui.states.HomeUiEvent
import br.com.stonks.feature.home.ui.states.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

internal val HOME_VM_QUALIFIER: String = HomeViewModel::class.java.simpleName

internal class HomeViewModel(
    private val homeContentUseCase: HomeContentUseCase,
    private val homeUiMapper: HomeUiMapper,
) : ViewModelState<HomeUiState, HomeUiEvent>() {

    init {
        fetchHomeContent()
    }

    override val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    override fun dispatchUiEvent(uiEvent: HomeUiEvent) {
        Timber.e("An ui event operation is not implemented.")
    }

    private fun fetchHomeContent() {
        viewModelScope.launch {
            homeContentUseCase.fetchData().catch {
                Timber.e(it, "Failure to fetch the home content")
                uiState.value = HomeUiState.Error(it)
            }.map {
                homeUiMapper.mapper(it)
            }.collectLatest {
                uiState.value = HomeUiState.Success(it)
            }
        }
    }
}
