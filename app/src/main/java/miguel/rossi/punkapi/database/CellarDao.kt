package miguel.rossi.punkapi.database

import androidx.room.*

@Dao
interface CellarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeBeer(catalog: List<DatabaseBeer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeFood(catalog: List<DatabaseFood>)

    @Transaction
    suspend fun storeCellar(cellarEntity: CellarEntity) {
        storeBeer(cellarEntity.databaseBeer)
        storeFood(cellarEntity.databaseFood)
    }

    @Query("SELECT * FROM $CELLAR_TABLE_NAME")
    suspend fun getBeer(): List<DatabaseBeer>

    @Query("SELECT * FROM $FOOD_TABLE_NAME")
    suspend fun getFood(): List<DatabaseFood>

    @Transaction
    suspend fun getCellar(): CellarEntity {
        val databaseBeer = getBeer()
        val databaseFood = getFood()
        return CellarEntity(databaseBeer, databaseFood)
    }

    @Query("DELETE FROM $CELLAR_TABLE_NAME")
    suspend fun emptyBeers()

    @Query("DELETE FROM $FOOD_TABLE_NAME")
    suspend fun emptyFood()

    @Transaction
    suspend fun emptyCellar() {
        emptyBeers()
    }
}
