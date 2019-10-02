package miguel.rossi.punkapi.repository

import androidx.annotation.VisibleForTesting
import miguel.rossi.punkapi.network.NetworkBeer
import miguel.rossi.punkapi.network.PunkApiService
import miguel.rossi.punkapi.network.asDomainModel
import miguel.rossi.punkapi.network.getApiClient
import miguel.rossi.punkapi.repository.HangoverType.BRAIN_SURGERY
import miguel.rossi.punkapi.repository.HangoverType.EASY_PEASY
import miguel.rossi.punkapi.util.NetworkUtil
import retrofit2.HttpException
import timber.log.Timber

suspend fun fetchBeers(page: Int): BreweryResponse {
    val apiService = getApiClient().create(PunkApiService::class.java)
    val apiResponse: List<NetworkBeer>

    try {
        apiResponse = apiService.getBeerListAsync(page)
    } catch (e: Exception) {
        return getHangover(e)
    }

    return BeerCatalog(apiResponse.asDomainModel())
}

@VisibleForTesting
fun getHangover(e: Exception, networkUtil: NetworkUtil = NetworkUtil()): Hangover {
    Timber.e(e)
    val (code, message) = when {
        networkUtil.checkNetwork().not() -> Pair(
            EASY_PEASY,
            "Oops! We couldn't find the internet..."
        )
        e is HttpException && e.code() >= 500 ->
            Pair(BRAIN_SURGERY, "Punk API is closed, come back tomorrow")
        e is HttpException && e.code() >= 400 ->
            Pair(BRAIN_SURGERY, "You're too drunk, Punk API didn't get it")
        else -> Pair(BRAIN_SURGERY, "This brewery is falling apart...")
    }
    return Hangover(code, message)
}
