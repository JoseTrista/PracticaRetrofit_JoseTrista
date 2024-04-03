package trista.josecarlos.practicaretrofit_josetrista.data

import retrofit2.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import trista.josecarlos.practicaretrofit_josetrista.data.model.Products
import okio.IOException

class ProductsRepositoryImpl (private val api: Api): ProductsRepository {
    override suspend fun getProductsList(): Flow<Result<List<Products>>> {
        return flow {
            val productsFromApi = try {
                api.getProductsList()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading products"))
                return@flow
            }
            emit(Result.Success(productsFromApi.products))
        }
    }
}