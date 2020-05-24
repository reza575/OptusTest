package com.moeiny.reza.optustest.repository.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Photos")
class PhotoEntity (@PrimaryKey   var photo_id:Int,
                   @ColumnInfo var album_id: Int,
                   @ColumnInfo var thumbnailUrl: String,
                   @ColumnInfo var title: String,
                   @ColumnInfo var url: String
)

