package trista.josecarlos.practicaretrofit_josetrista.Presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import trista.josecarlos.practicaretrofit_josetrista.data.Result
import trista.josecarlos.practicaretrofit_josetrista.data.ProductsRepository
import trista.josecarlos.practicaretrofit_josetrista.data.model.Products

class ProductsViewModel(private val productsRepository: ProductsRepository
) :ViewModel(){
    private val _products = MutableStateFlow<List<Products>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            productsRepository.getProductsList().collectLatest { result ->
                when(result) {
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { products ->
                            _products.update { products }
                        }
                    }
                }
            }
        }
    }
}