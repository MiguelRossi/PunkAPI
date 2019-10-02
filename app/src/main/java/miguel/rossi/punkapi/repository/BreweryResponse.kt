package miguel.rossi.punkapi.repository

import miguel.rossi.punkapi.domain.Beer

sealed class BreweryResponse

data class BeerCatalog(val page: List<Beer>) : BreweryResponse()

data class Hangover(val type: HangoverType, val message: String) : BreweryResponse()

enum class HangoverType { EASY_PEASY, BRAIN_SURGERY }
