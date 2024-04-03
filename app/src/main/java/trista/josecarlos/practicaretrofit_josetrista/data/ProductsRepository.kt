package trista.josecarlos.practicaretrofit_josetrista.data

import kotlinx.coroutines.flow.Flow
import trista.josecarlos.practicaretrofit_josetrista.data.model.Products

interface ProductsRepository{
    suspend fun getProductsList(): Flow<Result<List<Products>>>
}