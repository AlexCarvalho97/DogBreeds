package com.alexc.dogbreeds.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BreedEntity::class],
    version = 3
)
abstract class BreedDatabase : RoomDatabase() {
    abstract val dao: BreedDao
}