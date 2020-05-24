package com.moeiny.reza.optustest.repository.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "User")
class UserEntity (@PrimaryKey   var user_id:Int,
                  @ColumnInfo var name: String,
                  @ColumnInfo var username: String,
                  @ColumnInfo var email: String,
                  @ColumnInfo var street: String,
                  @ColumnInfo var suite: String,
                  @ColumnInfo var city: String,
                  @ColumnInfo var zipcode: String,
                  @ColumnInfo var lat: String,
                  @ColumnInfo var lng: String,
                  @ColumnInfo var phone: String,
                  @ColumnInfo var website: String,
                  @ColumnInfo var companyname: String,
                  @ColumnInfo var companycatchPhrase: String,
                  @ColumnInfo var companybs: String
)

