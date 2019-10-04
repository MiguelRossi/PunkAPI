package miguel.rossi.punkapi.repository

import com.google.common.truth.Truth.assertThat
import miguel.rossi.punkapi.database.CellarEntity
import miguel.rossi.punkapi.database.asDomainModel
import miguel.rossi.punkapi.utils.getBeerList
import miguel.rossi.punkapi.utils.getCellarEntity
import org.junit.Test

class CellarEntityTest {

    @Test
    fun asDomainModel_whenEmptyCellar_thenEmptyFoodList() {
        val emptyCellar = CellarEntity(listOf(), listOf())
        val model = emptyCellar.asDomainModel()

        assertThat(model.size).isEqualTo(0)
    }

    @Test
    fun asDomainModel_whenBeersInCellar_thenFoodList() {
        val cellar = getCellarEntity()
        val model = cellar.asDomainModel()
        val catalog = getBeerList()

        assertThat(model.size).isEqualTo(cellar.databaseBeer.size)
        assertThat(model).containsExactlyElementsIn(catalog)
    }

}
