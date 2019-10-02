package miguel.rossi.punkapi.utils

import miguel.rossi.punkapi.domain.Beer
import miguel.rossi.punkapi.network.NetworkBeer

fun getNetworkBeer(id: Int?) = NetworkBeer(
    id ?: 0,
    id?.let { "name_$id" },
    id?.let { "tagLine_$id" },
    id?.let { "description_$id" },
    id?.let { "imageUrl_$id" },
    id?.toDouble(),
    id?.let { listOf("foodPairing_$id", "foodPairing_${id.inc()}") },
    id?.let { "brewersTips_$id" },
    id?.let { "contributedB_$id" }
)

fun getBeer(id: Int?) = Beer(
    id ?: 0,
    id?.let { "name_$id" } ?: "",
    id?.let { "tagLine_$id" } ?: "",
    id?.let { "description_$id" } ?: "",
    id?.let { "imageUrl_$id" } ?: "",
    id?.toDouble() ?: 0.0,
    id?.let { listOf("foodPairing_$id", "foodPairing_${id.inc()}") } ?: emptyList(),
    id?.let { "brewersTips_$id" } ?: "",
    id?.let { "contributedB_$id" } ?: ""

)

fun getNetworkBeerList(from: Int, to: Int): List<NetworkBeer> {
    val mutableList = mutableListOf<NetworkBeer>()
    for (i in from..to) {
        mutableList.add(getNetworkBeer(i))
    }
    return mutableList.toList()
}

fun getBeerList(from: Int, to: Int) =
    mutableListOf<Beer>().apply {
        for (i in from..to) {
            add(getBeer(i))
        }
    }.toList()
