package miguel.rossi.punkapi.network

import com.google.common.truth.Truth.assertThat
import miguel.rossi.punkapi.utils.getBeer
import miguel.rossi.punkapi.utils.getNetworkBeer
import org.junit.Test

class DataTransferBeerTest {

    @Test
    fun asDomainModel_whenAllNull_thenAllDefaultValues() {
        val listOfNetworkBeer = listOf(getNetworkBeer(null))

        assertThat(listOfNetworkBeer.size).isEqualTo(1)
        assertThat(listOfNetworkBeer.asDomainModel()).isEqualTo(listOf(getBeer(null)))
    }

    @Test
    fun asDomainModel_whenNoneNull_thenAllGivenValues() {
        val listOfNetworkBeer = listOf(getNetworkBeer(10))

        assertThat(listOfNetworkBeer.size).isEqualTo(1)
        assertThat(listOfNetworkBeer.asDomainModel()).isEqualTo(listOf(getBeer(10)))
    }

}
