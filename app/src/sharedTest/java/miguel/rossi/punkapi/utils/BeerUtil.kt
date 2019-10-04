package miguel.rossi.punkapi.utils

import miguel.rossi.punkapi.domain.Beer
import miguel.rossi.punkapi.network.NetworkBeer

fun getEmptyBeerList() = listOf(Beer(0, "", "", "", "", 0.0, listOf(), "", ""))

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

fun getBeerList_pageTwo() = listOf(
    Beer(10, "10", "10", "10", "10", 10.0, listOf("foodPairing_0", "foodPairing_1"), "0", "10"),
    Beer(11, "11", "11", "11", "11", 11.0, listOf("foodPairing_1", "foodPairing_2"), "1", "11"),
    Beer(12, "12", "12", "12", "12", 12.0, listOf("", "foodPairing_3"), "2", "12"),
    Beer(13, "13", "13", "13", "13", 13.0, listOf("foodPairing_3", "foodPairing_4"), "3", "13"),
    Beer(14, "14", "14", "14", "14", 14.0, listOf("foodPairing_4", ""), "4", "14"),
    Beer(15, "15", "15", "15", "15", 15.0, listOf("", ""), "5", "15"),
    Beer(16, "16", "16", "16", "16", 16.0, listOf("foodPairing_6", "foodPairing_7"), "6", "16"),
    Beer(17, "17", "17", "17", "17", 17.0, listOf("foodPairing_7", "foodPairing_8"), "7", "17"),
    Beer(18, "18", "18", "18", "18", 18.0, listOf("", ""), "8", "18"),
    Beer(19, "19", "19", "19", "19", 19.0, listOf("foodPairing_9", "foodPairing_0"), "9", "19")
)

fun getEmptyNetworkBeerList() =
    listOf(NetworkBeer(0, null, null, null, null, null, null, null, null))

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
