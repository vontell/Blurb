package org.vontech.blurb.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * A Data Access Object for the Saying data class
 */
@Dao
interface SayingDao {

    @Query("SELECT * FROM saying")
    fun getAll(): List<Saying>

    @Insert(onConflict = REPLACE)
    fun insert(saying: Saying)

    @Delete()
    fun delete(saying: Saying)

}