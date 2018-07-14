package org.vontech.blurb.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * A database for the Saying data
 */
@Database(entities = [(Saying::class)], version = 1)
abstract class SayingDatabase: RoomDatabase() {

    abstract fun sayingDao(): SayingDao

    companion object {
        private var INSTANCE: SayingDatabase? = null

        fun getInstance(context: Context): SayingDatabase? {
            if (INSTANCE == null) {
                synchronized(SayingDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            SayingDatabase::class.java, "saying.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}