package com.alexc.dogbreeds.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreeds(
        breeds: List<BreedEntity>
    )

    @Query(value = "DELETE FROM breedentity")
    suspend fun clearBreeds()


    @Query("SELECT * FROM breedentity")
    suspend fun getBreeds(): List<BreedEntity>

    @Query("SELECT * FROM breedentity WHERE name LIKE '%' || :keyword || '%'")
    suspend fun getBreeds(keyword: String): List<BreedEntity>

    @Query("SELECT * FROM breedentity WHERE id LIKE :breedId")
    suspend fun getBreed(breedId: Int): BreedEntity?
}
