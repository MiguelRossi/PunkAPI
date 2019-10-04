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
import miguel.rossi.punkapi.repository.Hangover
import miguel.rossi.punkapi.repository.fetchBeers

private const val FIRST_PAGE = 1
private const val CATALOG_VISIBLE_THRESHOLD = 16
private const val CATALOG_MIN_PAGE_SIZE = 25

class CatalogViewModel : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var previousLastVisibleBeer = 0

    @VisibleForTesting(otherwise = PRIVATE)
    var catalogPage = FIRST_PAGE
        private set

    private val _catalog = MutableLiveData<BeerCatalog>()
    val catalog: LiveData<BeerCatalog>
        get() = _catalog

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _hangover = MutableLiveData<Hangover>()
    val hangover: LiveData<Hangover>
        get() = Transformations.map(_hangover) {
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
        if (_loading.value != true && canGetRequestBeers()) {
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
            when (val breweryResponse = fetchBeers(page)) {
                is BeerCatalog -> fillCatalog(breweryResponse)
                is Hangover -> _hangover.postValue(breweryResponse)
            }
            _loading.postValue(false)
        }
    }

    fun hangoverShown() {
        _hangover.value = null
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun fillCatalog(breweryResponse: BeerCatalog) {
        if (breweryResponse.page.size < CATALOG_MIN_PAGE_SIZE)
            noMoreBeers()

        val newCatalog = _catalog.value?.page?.toMutableList() ?: mutableListOf()
        newCatalog.addAll(breweryResponse.page)
        _catalog.postValue(BeerCatalog(newCatalog))
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun noMoreBeers() {
        catalogPage = Int.MIN_VALUE
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun canGetRequestBeers(): Boolean {
        return catalogPage != Int.MIN_VALUE
    }

}
