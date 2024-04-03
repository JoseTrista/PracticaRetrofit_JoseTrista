package trista.josecarlos.practicaretrofit_josetrista.data

import retrofit2.http.GET
import trista.josecarlos.practicaretrofit_josetrista.data.model.Product

interface Api {

    @GET("products")
    suspend fun getProductsList(): Product

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }
}