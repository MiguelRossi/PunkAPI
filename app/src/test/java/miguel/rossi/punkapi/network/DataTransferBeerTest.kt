package miguel.rossi.punkapi.network

import com.google.common.truth.Truth.assertThat
import miguel.rossi.punkapi.utils.getBeerList
import miguel.rossi.punkapi.utils.getEmptyBeerList
import miguel.rossi.punkapi.utils.getEmptyNetworkBeerList
import miguel.rossi.punkapi.utils.getNetworkBeerList
import org.junit.Test

class DataTransferBeerTest {

    @Test
    fun asDomainModel_whenAllNull_thenAllDefaultValues() {
        val listOfNetworkBeer = getEmptyNetworkBeerList()

        assertThat(listOfNetworkBeer.asDomainModel()).isEqualTo(getEmptyBeerList())
    }

    @Test
    fun asDomainModel_whenNoneNull_thenAllGivenValues() {
        val listOfNetworkBeer = getNetworkBeerList()

        assertThat(listOfNetworkBeer.asDomainModel()).isEqualTo(getBeerList())
    }

}
