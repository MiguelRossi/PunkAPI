package miguel.rossi.punkapi.repository

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import miguel.rossi.punkapi.database.CellarEntity
import miguel.rossi.punkapi.database.asDomainModel
import miguel.rossi.punkapi.database.getCellar
import miguel.rossi.punkapi.network.PunkApiService
import miguel.rossi.punkapi.network.asEntity
import miguel.rossi.punkapi.network.getApiClient
import miguel.rossi.punkapi.repository.HangoverType.BRAIN_SURGERY
import miguel.rossi.punkapi.repository.HangoverType.EASY_PEASY
import miguel.rossi.punkapi.util.NetworkUtil
import retrofit2.HttpException
import timber.log.Timber

private const val CATALOG_MIN_PAGE_SIZE = 25

class Brewery {

    private val apiService = getApiClient().create(PunkApiService::class.java)

    private val cellar = getCellar()

    private val _catalog = MutableLiveData<BeerCatalog>()
    val catalog: LiveData<BeerCatalog>
        get() = _catalog

    private val _hangover = MutableLiveData<Hangover>()
    val hangover: LiveData<Hangover>
        get() = _hangover

    var lastPageReached = false
        private set

    fun cleanHangover() {
        _hangover.value = null
    }

    suspend fun fetchBeers(page: Int) {
        withContext(Dispatchers.IO) {
            try {
                val apiResponseEntity = getMoreBeers(page)
                cellar.cellarDao.storeCellar(apiResponseEntity)

                val cellarEntity = cellar.cellarDao.getCellar()
                _catalog.postValue(BeerCatalog(cellarEntity.asDomainModel()))
            } catch (e: Exception) {
                _hangover.postValue(getHangover(e))
            }
        }
    }

    suspend fun emptyCellar() {
        withContext(Dispatchers.IO) { cellar.cellarDao.emptyCellar() }
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

    private suspend fun getMoreBeers(page: Int): CellarEntity {
        val apiResponse = apiService.getBeerListAsync(page)
        if (apiResponse.size < CATALOG_MIN_PAGE_SIZE)
            lastPageReached = true
        return apiResponse.asEntity()
    }
}
