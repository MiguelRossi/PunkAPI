package miguel.rossi.punkapi.catalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import miguel.rossi.punkapi.domain.Beer
import miguel.rossi.punkapi.repository.BeerCatalog
import miguel.rossi.punkapi.utils.getBeerList
import miguel.rossi.punkapi.utils.getBeerList_pageTwo
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.spy

class CatalogViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun catalogScrolled_whenCatalogVisibleThresholdNotReached_noOrderBeer() {
        var moreBeer = false
        val catalogViewModel = spy(CatalogViewModel::class.java)
        doAnswer { moreBeer = true; Any() }.`when`(catalogViewModel).beerPlease(anyInt())

        catalogViewModel.catalogScrolled(8, 25)
        assertThat(moreBeer).isFalse()
    }

    @Test
    fun catalogScrolled_whenCatalogVisibleThresholdReached_andMoreBeerAvailable_orderBeer() {
        var moreBeer = false
        val catalogViewModel = spy(CatalogViewModel::class.java)
        doAnswer { moreBeer = true; Any() }.`when`(catalogViewModel).beerPlease(anyInt())

        catalogViewModel.catalogScrolled(9, 25)
        assertThat(moreBeer).isTrue()
    }

    @Test
    fun catalogScrolled_whenCatalogVisibleThresholdReached_andNoMoreBeerAvailable_noOrderBeer() {
        var moreBeer = false
        val catalogViewModel = spy(CatalogViewModel::class.java)
        doAnswer { moreBeer = true; Any() }.`when`(catalogViewModel).beerPlease(anyInt())

        catalogViewModel.noMoreBeers()

        catalogViewModel.catalogScrolled(25, 25)
        assertThat(moreBeer).isFalse()
    }

    @Test
    fun fillCatalog_whenMoreItems_thenUpdateCatalog() {
        val catalogViewModel = CatalogViewModel()

        assertThat(catalogViewModel.catalog.value).isNull()

        val pageOne = getBeerList()
        catalogViewModel.fillCatalog(BeerCatalog(pageOne))
        assertThat(catalogViewModel.catalog.value).isEqualTo(BeerCatalog(pageOne))

        val pageTwo = getBeerList_pageTwo()
        catalogViewModel.fillCatalog(BeerCatalog(pageTwo))
        val newPages = mutableListOf<Beer>().apply {
            addAll(pageOne)
            addAll(pageTwo)
        }
        assertThat(catalogViewModel.catalog.value!!.page.size).isEqualTo(20)
        assertThat(catalogViewModel.catalog.value).isEqualTo(BeerCatalog(newPages))
    }

    @Test
    fun fillCatalog_whenNewPageSmallerThanCatalogMinPageSize_thenSetToNotRequestMoreBeers() {
        val catalogViewModel = CatalogViewModel()

        assertThat(catalogViewModel.canGetRequestBeers()).isTrue()

        val pageOne = getBeerList()
        catalogViewModel.fillCatalog(BeerCatalog(pageOne))
        assertThat(catalogViewModel.catalog.value).isEqualTo(BeerCatalog(pageOne))

        assertThat(catalogViewModel.canGetRequestBeers()).isFalse()
    }


}
