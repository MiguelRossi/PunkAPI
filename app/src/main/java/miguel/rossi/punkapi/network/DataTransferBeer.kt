package miguel.rossi.punkapi.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import miguel.rossi.punkapi.domain.*

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
    val foodPairing: List<String>?,
    @Json(name = "brewers_tips")
    val brewersTips: String?,
    @Json(name = "contributed_by")
    val contributedBy: String?
)

fun List<NetworkBeer>.asDomainModel(): List<Beer> {
    return this.map {
        Beer(
            id = it.id,
            name = it.name ?: "",
            tagLine = it.tagLine ?: "",
            description = it.description ?: "",
            imageUrl = it.imageUrl ?: "",
            abv = it.abv ?: 0.0,
            foodPairing = it.foodPairing ?: listOf(),
            brewersTips = it.brewersTips ?: "",
            contributedBy = it.contributedBy ?: ""
        )
    }
}
