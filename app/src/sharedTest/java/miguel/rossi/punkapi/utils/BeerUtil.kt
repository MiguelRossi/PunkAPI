package miguel.rossi.punkapi.utils

import miguel.rossi.punkapi.database.CellarEntity
import miguel.rossi.punkapi.database.DatabaseBeer
import miguel.rossi.punkapi.database.DatabaseFood
import miguel.rossi.punkapi.domain.Beer
import miguel.rossi.punkapi.network.NetworkBeer

fun getBeerList() = listOf(
    Beer(0, "0", "0", "0", "0", 0.0, listOf("foodPairing_0", "foodPairing_1"), "0", "0"),
    Beer(1, "1", "1", "1", "1", 1.0, listOf("foodPairing_1", "foodPairing_2"), "1", "1"),
    Beer(2, "2", "2", "2", "2", 2.0, listOf("foodPairing_2", "foodPairing_3"), "2", "2"),
    Beer(3, "3", "3", "3", "3", 3.0, listOf("foodPairing_3", "foodPairing_4"), "3", "3"),
    Beer(4, "4", "4", "4", "4", 4.0, listOf("foodPairing_4", "foodPairing_5"), "4", "4"),
    Beer(5, "5", "5", "5", "5", 5.0, listOf("foodPairing_5", "foodPairing_6"), "5", "5"),
    Beer(6, "6", "6", "6", "6", 6.0, listOf("foodPairing_6", "foodPairing_7"), "6", "6"),
    Beer(7, "7", "7", "7", "7", 7.0, listOf("foodPairing_7", "foodPairing_8"), "7", "7"),
    Beer(8, "8", "8", "8", "8", 8.0, listOf("foodPairing_8", "foodPairing_9"), "8", "8"),
    Beer(9, "9", "9", "9", "9", 9.0, listOf("foodPairing_9", "foodPairing_0"), "9", "9")
)

fun getEmptyNetworkBeer() = listOf(NetworkBeer(0, null, null, null, null, null, null, null, null))

fun getNetworkBeerList() = listOf(
    NetworkBeer(0, "0", "0", "0", "0", 0.0, listOf("foodPairing_0", "foodPairing_1"), "0", "0"),
    NetworkBeer(1, "1", "1", "1", "1", 1.0, listOf("foodPairing_1", "foodPairing_2"), "1", "1"),
    NetworkBeer(2, "2", "2", "2", "2", 2.0, listOf("foodPairing_2", "foodPairing_3"), "2", "2"),
    NetworkBeer(3, "3", "3", "3", "3", 3.0, listOf("foodPairing_3", "foodPairing_4"), "3", "3"),
    NetworkBeer(4, "4", "4", "4", "4", 4.0, listOf("foodPairing_4", "foodPairing_5"), "4", "4"),
    NetworkBeer(5, "5", "5", "5", "5", 5.0, listOf("foodPairing_5", "foodPairing_6"), "5", "5"),
    NetworkBeer(6, "6", "6", "6", "6", 6.0, listOf("foodPairing_6", "foodPairing_7"), "6", "6"),
    NetworkBeer(7, "7", "7", "7", "7", 7.0, listOf("foodPairing_7", "foodPairing_8"), "7", "7"),
    NetworkBeer(8, "8", "8", "8", "8", 8.0, listOf("foodPairing_8", "foodPairing_9"), "8", "8"),
    NetworkBeer(9, "9", "9", "9", "9", 9.0, listOf("foodPairing_9", "foodPairing_0"), "9", "9")
)

fun getEmptyDatabaseBeer() = listOf(DatabaseBeer(0, "", "", "", "", 0.0, "", ""))

fun getCellarEntity(): CellarEntity {
    val listDatabaseBeer = getDatabaseBeerList()
    val listDatabaseFood = getDatabaseFoodList()
    return CellarEntity(listDatabaseBeer, listDatabaseFood)
}

fun getDatabaseBeerList() = listOf(
    DatabaseBeer(0, "0", "0", "0", "0", 0.0, "0", "0"),
    DatabaseBeer(1, "1", "1", "1", "1", 1.0, "1", "1"),
    DatabaseBeer(2, "2", "2", "2", "2", 2.0, "2", "2"),
    DatabaseBeer(3, "3", "3", "3", "3", 3.0, "3", "3"),
    DatabaseBeer(4, "4", "4", "4", "4", 4.0, "4", "4"),
    DatabaseBeer(5, "5", "5", "5", "5", 5.0, "5", "5"),
    DatabaseBeer(6, "6", "6", "6", "6", 6.0, "6", "6"),
    DatabaseBeer(7, "7", "7", "7", "7", 7.0, "7", "7"),
    DatabaseBeer(8, "8", "8", "8", "8", 8.0, "8", "8"),
    DatabaseBeer(9, "9", "9", "9", "9", 9.0, "9", "9")
)

fun getDatabaseFoodList() = listOf(
    DatabaseFood(0, "foodPairing_0", 0),
    DatabaseFood(1, "foodPairing_1", 0),
    DatabaseFood(0, "foodPairing_1", 1),
    DatabaseFood(1, "foodPairing_2", 1),
    DatabaseFood(0, "foodPairing_2", 2),
    DatabaseFood(1, "foodPairing_3", 2),
    DatabaseFood(0, "foodPairing_3", 3),
    DatabaseFood(1, "foodPairing_4", 3),
    DatabaseFood(0, "foodPairing_4", 4),
    DatabaseFood(1, "foodPairing_5", 4),
    DatabaseFood(0, "foodPairing_5", 5),
    DatabaseFood(1, "foodPairing_6", 5),
    DatabaseFood(0, "foodPairing_6", 6),
    DatabaseFood(1, "foodPairing_7", 6),
    DatabaseFood(0, "foodPairing_7", 7),
    DatabaseFood(1, "foodPairing_8", 7),
    DatabaseFood(0, "foodPairing_8", 8),
    DatabaseFood(1, "foodPairing_9", 8),
    DatabaseFood(0, "foodPairing_9", 9),
    DatabaseFood(1, "foodPairing_0", 9)
)
