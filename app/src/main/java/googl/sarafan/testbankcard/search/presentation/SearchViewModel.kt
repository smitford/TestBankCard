package googl.sarafan.testbankcard.search.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import googl.sarafan.testbankcard.search.presentation.models.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    val _searchState = MutableStateFlow(SearchState())
}