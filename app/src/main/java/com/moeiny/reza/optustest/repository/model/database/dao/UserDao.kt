package com.moeiny.reza.optustest.repository.model.database.dao

import androidx.room.*
import com.moeiny.reza.optustest.repository.database.entitiy.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM User ORDER BY user_id ")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM User WHERE user_id = :id ")
    fun findByid(id: Int): UserEntity

    @Query("DELETE FROM User")
    fun deleteAll()

    @Insert
    fun insert(user: UserEntity)

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}




