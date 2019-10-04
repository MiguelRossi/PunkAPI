package miguel.rossi.punkapi.utils

import miguel.rossi.punkapi.database.CellarEntity
import miguel.rossi.punkapi.database.DatabaseBeer
import miguel.rossi.punkapi.database.DatabaseFood
import miguel.rossi.punkapi.domain.Beer
import miguel.rossi.punkapi.network.NetworkBeer

fun getBeerList() = listOf(
    Beer(0, "0", "0", "0", "0", 0.0, listOf("foodPairing_0", "foodPairing_1"), "0", "0"),
    Beer(1, "1", "1", "1", "1", 1.0, listOf("foodPairing_1", "foodPairing_2"), "1", "1"),
    Beer(2, "2", "2", "2", "2", 2.0, listOf("", "foodPairing_3"), "2", "2"),
    Beer(3, "3", "3", "3", "3", 3.0, listOf("foodPairing_3", "foodPairing_4"), "3", "3"),
    Beer(4, "4", "4", "4", "4", 4.0, listOf("foodPairing_4", ""), "4", "4"),
    Beer(5, "5", "5", "5", "5", 5.0, listOf("", ""), "5", "5"),
    Beer(6, "6", "6", "6", "6", 6.0, listOf("foodPairing_6", "foodPairing_7"), "6", "6"),
    Beer(7, "7", "7", "7", "7", 7.0, listOf("foodPairing_7", "foodPairing_8"), "7", "7"),
    Beer(8, "8", "8", "8", "8", 8.0, listOf("", ""), "8", "8"),
    Beer(9, "9", "9", "9", "9", 9.0, listOf("foodPairing_9", "foodPairing_0"), "9", "9")
)

fun getEmptyNetworkBeer() = listOf(NetworkBeer(0, null, null, null, null, null, null, null, null))

fun getNetworkBeerList() = listOf(
    NetworkBeer(0, "0", "0", "0", "0", 0.0, listOf("foodPairing_0", "foodPairing_1"), "0", "0"),
    NetworkBeer(1, "1", "1", "1", "1", 1.0, listOf("foodPairing_1", "foodPairing_2"), "1", "1"),
    NetworkBeer(2, "2", "2", "2", "2", 2.0, listOf("", "foodPairing_3"), "2", "2"),
    NetworkBeer(3, "3", "3", "3", "3", 3.0, listOf("foodPairing_3", "foodPairing_4"), "3", "3"),
    NetworkBeer(4, "4", "4", "4", "4", 4.0, listOf("foodPairing_4", ""), "4", "4"),
    NetworkBeer(5, "5", "5", "5", "5", 5.0, listOf("", ""), "5", "5"),
    NetworkBeer(6, "6", "6", "6", "6", 6.0, listOf("foodPairing_6", "foodPairing_7"), "6", "6"),
    NetworkBeer(7, "7", "7", "7", "7", 7.0, listOf("foodPairing_7", "foodPairing_8"), "7", "7"),
    NetworkBeer(8, "8", "8", "8", "8", 8.0, listOf("", ""), "8", "8"),
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
    DatabaseFood(0, "", 2),
    DatabaseFood(1, "foodPairing_3", 2),
    DatabaseFood(0, "foodPairing_3", 3),
    DatabaseFood(1, "foodPairing_4", 3),
    DatabaseFood(0, "foodPairing_4", 4),
    DatabaseFood(1, "", 4),
    DatabaseFood(0, "", 5),
    DatabaseFood(1, "", 5),
    DatabaseFood(0, "foodPairing_6", 6),
    DatabaseFood(1, "foodPairing_7", 6),
    DatabaseFood(0, "foodPairing_7", 7),
    DatabaseFood(1, "foodPairing_8", 7),
    DatabaseFood(0, "", 8),
    DatabaseFood(1, "", 8),
    DatabaseFood(0, "foodPairing_9", 9),
    DatabaseFood(1, "foodPairing_0", 9)
)

fun getCellarEntity_pageTwo(): CellarEntity {
    val listDatabaseBeer = getDatabaseBeerList_pageTwo()
    val listDatabaseFood = getDatabaseFoodList_pageTwo()
    return CellarEntity(listDatabaseBeer, listDatabaseFood)
}

fun getDatabaseBeerList_pageTwo() = listOf(
    DatabaseBeer(10, "10", "10", "10", "10", 10.0, "10", "10"),
    DatabaseBeer(11, "11", "11", "11", "11", 11.0, "11", "11"),
    DatabaseBeer(12, "12", "12", "12", "12", 12.0, "12", "12"),
    DatabaseBeer(13, "13", "13", "13", "13", 13.0, "13", "13"),
    DatabaseBeer(14, "14", "14", "14", "14", 14.0, "14", "14"),
    DatabaseBeer(15, "15", "15", "15", "15", 15.0, "15", "15"),
    DatabaseBeer(16, "16", "16", "16", "16", 16.0, "16", "16"),
    DatabaseBeer(17, "17", "17", "17", "17", 17.0, "17", "17"),
    DatabaseBeer(18, "18", "18", "18", "18", 18.0, "18", "18"),
    DatabaseBeer(19, "19", "19", "19", "19", 19.0, "19", "19")
)

fun getDatabaseFoodList_pageTwo() = listOf(
    DatabaseFood(0, "foodPairing_0", 10),
    DatabaseFood(1, "foodPairing_1", 10),
    DatabaseFood(0, "foodPairing_1", 11),
    DatabaseFood(1, "foodPairing_2", 11),
    DatabaseFood(0, "", 12),
    DatabaseFood(1, "foodPairing_3", 12),
    DatabaseFood(0, "foodPairing_3", 13),
    DatabaseFood(1, "foodPairing_4", 13),
    DatabaseFood(0, "foodPairing_4", 14),
    DatabaseFood(1, "", 14),
    DatabaseFood(0, "", 15),
    DatabaseFood(1, "", 15),
    DatabaseFood(0, "foodPairing_6", 16),
    DatabaseFood(1, "foodPairing_7", 16),
    DatabaseFood(0, "foodPairing_7", 17),
    DatabaseFood(1, "foodPairing_8", 17),
    DatabaseFood(0, "", 18),
    DatabaseFood(1, "", 18),
    DatabaseFood(0, "foodPairing_9", 19),
    DatabaseFood(1, "foodPairing_0", 19)
)
