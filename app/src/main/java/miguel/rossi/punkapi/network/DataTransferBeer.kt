package miguel.rossi.punkapi.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import miguel.rossi.punkapi.database.CellarEntity
import miguel.rossi.punkapi.database.DatabaseBeer
import miguel.rossi.punkapi.database.DatabaseFood

@JsonClass(generateAdapter = true)
data class NetworkBeer(
    val id: Int,
    val name: String?,
    @Json(name = "tagline")
    val tagLine: String?,
    val description: String?,
    @Json(name = "image_url")
    val imageUrl: String?,
    val abv: Double?,
    @Json(name = "food_pairing")
    val foodPairing: List<String?>?,
    @Json(name = "brewers_tips")
    val brewersTips: String?,
    @Json(name = "contributed_by")
    val contributedBy: String?
)

fun List<NetworkBeer>.asEntity(): CellarEntity {
    val listDatabaseBeer = getDatabaseBeerList(this)
    val listDatabaseFood = getDatabaseFoodList(this)
    return CellarEntity(listDatabaseBeer, listDatabaseFood)
}

private fun getDatabaseBeerList(list: List<NetworkBeer>) =
    list.map {
        DatabaseBeer(
            id = it.id,
            name = it.name ?: "",
            tagLine = it.tagLine ?: "",
            description = it.description ?: "",
            imageUrl = it.imageUrl ?: "",
            abv = it.abv ?: 0.0,
            brewersTips = it.brewersTips ?: "",
            contributedBy = it.contributedBy ?: ""
        )
    }

private fun getDatabaseFoodList(list: List<NetworkBeer>): List<DatabaseFood> {
    val databaseFoodList = mutableListOf<DatabaseFood>()
    for (i in list.indices) {
        val loops = list[i].foodPairing?.size ?: 0
        for (j in 0 until loops) {
            val food: String = list[i].foodPairing!![j] ?: ""
            databaseFoodList.add(DatabaseFood(id = j, food = food, beerId = list[i].id))
        }
    }
    return databaseFoodList
}
