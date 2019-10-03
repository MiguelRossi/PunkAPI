package miguel.rossi.punkapi.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import miguel.rossi.punkapi.PunkAPIApp

private const val CELLAR_DATABASE_NAME = "cellar_database_name"

@Database(entities = [DatabaseBeer::class, DatabaseFood::class], version = 1)
abstract class Cellar : RoomDatabase() {
    abstract val cellarDao: CellarDao
}

private lateinit var instance: Cellar

fun getCellar(): Cellar {
    synchronized(Cellar::class.java) {
        if (!::instance.isInitialized) {
            instance = Room
                .databaseBuilder(PunkAPIApp.instance, Cellar::class.java, CELLAR_DATABASE_NAME)
                .build()
        }
    }
    return instance
}
