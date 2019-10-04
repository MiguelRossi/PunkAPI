package miguel.rossi.punkapi.database

import android.content.Context
import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import miguel.rossi.punkapi.utils.getCellarEntity
import miguel.rossi.punkapi.utils.getCellarEntity_pageTwo
import miguel.rossi.punkapi.utils.getDatabaseBeerList
import miguel.rossi.punkapi.utils.getDatabaseFoodList
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CellarDaoTest {

    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var cellarDao: CellarDao
    private lateinit var cellar: Cellar

    /**
     * Set TransactionExecutor explicitly when testing @Transaction's
     *
     * Apparently @Transaction uses runBlocking and causes a deadlock when used with [InstantTaskExecutorRule]
     */
    private fun createDb(transaction: Boolean = false) {
        val context = ApplicationProvider.getApplicationContext<Context>()

        cellar = Room.inMemoryDatabaseBuilder(context, Cellar::class.java).apply {
            if (transaction)
                setTransactionExecutor(Executors.newSingleThreadExecutor())
        }.build()

        cellarDao = cellar.cellarDao
    }

    @After
    @Throws(IOException::class)
    fun closeCellar() {
        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
        cellar.close()
    }

    @Test
    fun insert_andReadEmptyBeerList() = runBlockingTest {
        createDb()

        val beerList = emptyList<DatabaseBeer>()
        cellarDao.storeBeer(beerList)

        val newBeerList = cellarDao.getBeer()
        assertThat(newBeerList).isEmpty()
    }

    @Test
    fun insert_andReadBeerList() = runBlockingTest {
        createDb()

        val beerList = getDatabaseBeerList()
        cellarDao.storeBeer(beerList)

        val newBeerList = cellarDao.getBeer()
        assertThat(newBeerList).containsExactlyElementsIn(beerList)
    }

    @Test
    fun insert_andDeleteBeerList() = runBlockingTest {
        createDb()

        val beerList = getDatabaseBeerList()
        cellarDao.storeBeer(beerList)

        cellarDao.emptyBeers()

        val newBeerList = cellarDao.getBeer()
        assertThat(newBeerList).isEmpty()
    }

    @Test
    fun insert_andReadEmptyFoodList() = runBlockingTest {
        createDb()

        val foodList = emptyList<DatabaseFood>()
        cellarDao.storeFood(foodList)

        val newFoodList = cellarDao.getFood()
        assertThat(newFoodList).isEmpty()
    }

    @Test
    fun insert_andReadFoodList() = runBlockingTest {
        createDb()

        val beerList = getDatabaseBeerList()
        val foodList = getDatabaseFoodList()
        cellarDao.storeBeer(beerList)
        cellarDao.storeFood(foodList)

        val newFoodList = cellarDao.getFood()
        assertThat(newFoodList).containsExactlyElementsIn(foodList)
    }

    @Test
    fun insert_andEmptyFood() = runBlockingTest {
        createDb()

        val beerList = getDatabaseBeerList()
        val foodList = getDatabaseFoodList()
        cellarDao.storeBeer(beerList)
        cellarDao.storeFood(foodList)

        cellarDao.emptyFood()

        val newFoodList = cellarDao.getFood()
        assertThat(newFoodList).isEmpty()
    }

    @Test
    fun insert_andEmptyBeers_thenFoodListEmpty() = runBlockingTest {
        createDb()

        val beerList = getDatabaseBeerList()
        val foodList = getDatabaseFoodList()
        cellarDao.storeBeer(beerList)
        cellarDao.storeFood(foodList)

        cellarDao.emptyBeers()

        val newFoodList = cellarDao.getFood()
        assertThat(newFoodList).isEmpty()
    }

    @Test
    fun store_andGetCellar() = runBlocking {
        createDb(true)

        val cellarEntity = getCellarEntity()
        cellarDao.storeCellar(cellarEntity)

        val newCellarEntity = cellarDao.getCellar()
        assertThat(newCellarEntity.databaseBeer).containsExactlyElementsIn(cellarEntity.databaseBeer)
        assertThat(newCellarEntity.databaseFood).containsExactlyElementsIn(cellarEntity.databaseFood)

        return@runBlocking
    }

    @Test
    fun store_andEmptyCellar() = runBlocking {
        createDb(true)

        val cellarEntity = getCellarEntity()
        cellarDao.storeCellar(cellarEntity)

        cellarDao.emptyCellar()

        val newCellarEntity = cellarDao.getCellar()
        assertThat(newCellarEntity.databaseBeer).isEmpty()
        assertThat(newCellarEntity.databaseFood).isEmpty()

        return@runBlocking
    }

    @Test
    fun store_andGetCellar_whenTwoPages() = runBlocking {
        createDb(true)

        val cellarEntity = getCellarEntity()
        cellarDao.storeCellar(cellarEntity)

        val newCellarEntity = cellarDao.getCellar()
        assertThat(newCellarEntity.databaseBeer).containsExactlyElementsIn(cellarEntity.databaseBeer)
        assertThat(newCellarEntity.databaseFood).containsExactlyElementsIn(cellarEntity.databaseFood)

        val secondCellarEntity = getCellarEntity_pageTwo()
        cellarDao.storeCellar(secondCellarEntity)
        val fullDatabaseBeer =
            listOf(cellarEntity.databaseBeer, secondCellarEntity.databaseBeer).flatten()
        val fullDatabaseFood =
            listOf(cellarEntity.databaseFood, secondCellarEntity.databaseFood).flatten()
        val fullCellarEntity = CellarEntity(fullDatabaseBeer, fullDatabaseFood)

        val secondNewCellarEntity = cellarDao.getCellar()
        assertThat(secondNewCellarEntity.databaseBeer).containsExactlyElementsIn(fullCellarEntity.databaseBeer)
        assertThat(secondNewCellarEntity.databaseFood).containsExactlyElementsIn(fullCellarEntity.databaseFood)

        return@runBlocking
    }

    @Test
    fun store_andGetCellar_whenTwoPages_andRepeatedValues_thenContainsNoDuplicates() = runBlocking {
        createDb(true)

        val cellarEntity = getCellarEntity()
        cellarDao.storeCellar(cellarEntity)

        val newCellarEntity = cellarDao.getCellar()
        assertThat(newCellarEntity.databaseBeer).containsExactlyElementsIn(cellarEntity.databaseBeer)
        assertThat(newCellarEntity.databaseFood).containsExactlyElementsIn(cellarEntity.databaseFood)

        val secondCellarEntity = getCellarEntity()
        cellarDao.storeCellar(secondCellarEntity)

        val secondNewCellarEntity = cellarDao.getCellar()
        assertThat(secondNewCellarEntity.databaseBeer).containsExactlyElementsIn(cellarEntity.databaseBeer)
        assertThat(secondNewCellarEntity.databaseFood).containsExactlyElementsIn(cellarEntity.databaseFood)

        return@runBlocking
    }

}
