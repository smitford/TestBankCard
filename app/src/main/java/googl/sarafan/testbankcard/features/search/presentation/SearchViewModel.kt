package googl.sarafan.testbankcard.features.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import googl.sarafan.testbankcard.features.search.domain.use_case.CallNumberUseCase
import googl.sarafan.testbankcard.features.search.domain.use_case.OpenLinkUseCase
import googl.sarafan.testbankcard.features.search.domain.use_case.SaveCardSearchUseCase
import googl.sarafan.testbankcard.features.search.domain.use_case.SearchUseCase
import googl.sarafan.testbankcard.features.search.presentation.models.CardValue
import googl.sarafan.testbankcard.features.search.presentation.models.SearchEvent
import googl.sarafan.testbankcard.features.search.presentation.models.SearchState
import googl.sarafan.testbankcard.features.search.presentation.models.SearchUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val saveCardSpecificationUseCase: SaveCardSearchUseCase,
    private val openLinkUseCase: OpenLinkUseCase,
    private val callNumberUseCase: CallNumberUseCase
) : ViewModel() {
    private val _stateScreen = MutableStateFlow(SearchState())
    private val stateScreen: StateFlow<SearchState> = _stateScreen.asStateFlow()
    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    init {
        viewModelScope.launch {
            stateScreen.collect { state ->
                _searchUiState.update {
                    it.copy(
                        isLoading = state.isLoading,
                        cardValue = state.cardValue
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun executeEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> {
                searchCard()
            }

            is SearchEvent.OpenUrl -> {
                openUrl()
            }

            is SearchEvent.CallBank -> {
                callBank()
            }

            is SearchEvent.OnCardNumberChanged -> {
                changeCardNumber(event.cardNumber)
            }
        }
    }

    private fun changeCardNumber(cardNumber: String) {
        _searchUiState.update {
            it.copy(
                cardNumber = cardNumber
            )
        }
    }

    private fun searchCard() {
        _stateScreen.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.invoke(_searchUiState.value.cardNumber)
                .onSuccess {
                    _stateScreen.update { state ->
                        state.copy(
                            isLoading = false,
                            cardValue = it
                        )
                    }
                    saveCardSpecificationUseCase.invoke(
                        cardNumber = _searchUiState.value.cardNumber,
                        cardValue = _stateScreen.value.cardValue
                    )

                }
                .onFailure {
                    _stateScreen.update { state ->
                        state.copy(
                            isLoading = false,
                            cardValue = CardValue()
                        )
                    }
                }
        }
    }

    private fun saveCardSpecification() {

    }

    private fun openUrl() {
        val url = _stateScreen.value.cardValue.bank.url
        if (url != "-" && url.isNotBlank())
            openLinkUseCase.invoke(url)
    }

    private fun callBank() {
        val phoneNumber = _stateScreen.value.cardValue.bank.phone
        if (phoneNumber != "-" && phoneNumber.isNotBlank())
            callNumberUseCase.invoke(phoneNumber)
    }

}