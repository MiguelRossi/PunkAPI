package miguel.rossi.punkapi.network

import com.google.common.truth.Truth.assertThat
import miguel.rossi.punkapi.database.DatabaseFood
import miguel.rossi.punkapi.utils.getCellarEntity
import miguel.rossi.punkapi.utils.getEmptyDatabaseBeer
import miguel.rossi.punkapi.utils.getEmptyNetworkBeer
import miguel.rossi.punkapi.utils.getNetworkBeerList
import org.junit.Test

class DataTransferBeerTest {

    @Test
    fun asEntity_whenEmptyList_thenAllDefaultValues() {
        val listOfNetworkBeer = listOf<NetworkBeer>()
        val entity = listOfNetworkBeer.asEntity()

        assertThat(entity.databaseBeer.size).isEqualTo(listOfNetworkBeer.size)
    }

    @Test
    fun asEntity_whenNull_thenDefaultValues() {
        val listOfNetworkBeer = getEmptyNetworkBeer()
        val entity = listOfNetworkBeer.asEntity()

        assertThat(listOfNetworkBeer.size).isEqualTo(1)
        assertThat(entity.databaseFood).containsExactlyElementsIn(listOf<DatabaseFood>())
        assertThat(entity.databaseBeer).containsExactlyElementsIn(getEmptyDatabaseBeer())
    }

    @Test
    fun asEntity_whenNoneNull_thenAllGivenValues() {
        val listOfNetworkBeer = getNetworkBeerList()
        val entity = listOfNetworkBeer.asEntity()
        val cellarEntity = getCellarEntity()

        assertThat(listOfNetworkBeer.size).isEqualTo(cellarEntity.databaseBeer.size)
        assertThat(entity.databaseBeer).containsExactlyElementsIn(cellarEntity.databaseBeer)
        assertThat(entity.databaseFood).containsExactlyElementsIn(cellarEntity.databaseFood)
    }

}
