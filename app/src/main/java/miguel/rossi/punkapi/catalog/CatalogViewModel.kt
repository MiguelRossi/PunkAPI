package miguel.rossi.punkapi.catalog

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import miguel.rossi.punkapi.domain.Beer
import miguel.rossi.punkapi.repository.BeerCatalog
import miguel.rossi.punkapi.repository.Brewery
import miguel.rossi.punkapi.repository.Hangover

private const val FIRST_PAGE = 1
private const val CATALOG_VISIBLE_THRESHOLD = 16

class CatalogViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val brewery by lazy { Brewery() }

    private var previousLastVisibleBeer = 0

    @VisibleForTesting(otherwise = PRIVATE)
    var catalogPage = FIRST_PAGE
        private set

    val catalog: LiveData<BeerCatalog>
        get() = brewery.catalog

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val hangover: LiveData<Hangover>
        get() = Transformations.map(brewery.hangover) {
            if (it != null)
                _errorMessage.value = it
            it
        }

    private val _errorMessage = MutableLiveData<Hangover>()
    val errorMessage: LiveData<String>
        get() = Transformations.map(_errorMessage) { it.message }

    private val _selectedBeer = MutableLiveData<Beer>()
    val selectedBeer: LiveData<Beer>
        get() = _selectedBeer

    companion object {
        fun get(fragment: Fragment) =
            ViewModelProviders.of(fragment).get(CatalogViewModel::class.java)
    }

    init {
        cleanCellar()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun beerSelected(beer: Beer) {
        _selectedBeer.value = beer
    }

    fun beerSelectionFinished() {
        _selectedBeer.value = null
    }

    fun catalogScrolled(lastVisibleBeer: Int, beerInCatalog: Int) {
        if (_loading.value != true && brewery.lastPageReached.not()) {
            val scrollingDown = previousLastVisibleBeer < lastVisibleBeer
            val nearTheBottom = lastVisibleBeer + CATALOG_VISIBLE_THRESHOLD >= beerInCatalog

            if (scrollingDown && nearTheBottom) {
                beerPlease(++catalogPage)
                previousLastVisibleBeer = lastVisibleBeer
            }
        }
    }

    fun beerPlease(page: Int = catalogPage) {
        _loading.value = true
        uiScope.launch(Dispatchers.Main) {
            brewery.fetchBeers(page)
            _loading.postValue(false)
        }
    }

    fun hangoverShown() {
        brewery.cleanHangover()
    }

    private fun cleanCellar() {
        uiScope.launch { brewery.emptyCellar() }
    }

}
