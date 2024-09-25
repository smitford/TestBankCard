package googl.sarafan.testbankcard.features.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import googl.sarafan.testbankcard.features.history.domain.DeleteHistoryUseCase
import googl.sarafan.testbankcard.features.history.domain.GetHistoryUseCase
import googl.sarafan.testbankcard.features.history.presentation.models.HistoryEvent
import googl.sarafan.testbankcard.features.history.presentation.models.HistoryState
import googl.sarafan.testbankcard.features.history.presentation.models.HistoryStateUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val deleteHistoryUseCase: DeleteHistoryUseCase
) : ViewModel() {
    private val _historyState = MutableStateFlow(HistoryState())
    private val historyState: StateFlow<HistoryState> = _historyState

    private val _historyStateUi = MutableStateFlow(HistoryStateUi())
    val historyStateUi: StateFlow<HistoryStateUi> = _historyStateUi

    init {
        viewModelScope.launch {
            getHistory()

            historyState.collect { state ->
                _historyStateUi.update {
                    it.copy(
                        historyValue = state.historyValue,
                        isLoading = state.isLoading
                    )
                }
            }
        }
    }

    fun executeEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.ClearHistory -> {
                clearHistory()
            }
        }
    }

    private fun clearHistory() {
        _historyState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            deleteHistoryUseCase.invoke()
            _historyState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun getHistory() {
        _historyState.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            val history = getHistoryUseCase.invoke()
            _historyState.update { state ->
                state.copy(
                    isLoading = false,
                    historyValue = history
                )
            }
        }
    }

}