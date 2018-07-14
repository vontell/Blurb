package org.vontech.blurb.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * A simple data class for holding sayings created by a user
 */
@Entity(tableName = "saying")
data class Saying(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "content") var content: String = "") {
    constructor():this(null, "")
}
