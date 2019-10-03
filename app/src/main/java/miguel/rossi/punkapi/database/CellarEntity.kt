package miguel.rossi.punkapi.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

const val CELLAR_TABLE_NAME = "cellar_table_name"
const val FOOD_TABLE_NAME = "food_table_name"

private const val FOOD_PARENT_COLUMN = "id"
private const val FOOD_CHILD_COLUMN = "beerId"

@Entity(tableName = CELLAR_TABLE_NAME)
data class DatabaseBeer(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagLine: String,
    val description: String,
    val imageUrl: String,
    val abv: Double,
    val brewersTips: String,
    val contributedBy: String
)

@Entity(
    tableName = FOOD_TABLE_NAME,
    primaryKeys = ["id", "beerId"],
    foreignKeys = [ForeignKey(
        entity = DatabaseBeer::class,
        parentColumns = [FOOD_PARENT_COLUMN],
        childColumns = [FOOD_CHILD_COLUMN],
        onDelete = ForeignKey.CASCADE
    )]
)
data class DatabaseFood(
    val id: Int,
    val food: String,
    val beerId: Int
)

class CellarEntity(
    val databaseBeer: List<DatabaseBeer>,
    val databaseFood: List<DatabaseFood>
)
