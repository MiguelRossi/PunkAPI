package miguel.rossi.punkapi.network

import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApiService {

    @GET("beers")
    suspend fun getBeerListAsync(@Query("page") page: Int): List<NetworkBeer>

}
