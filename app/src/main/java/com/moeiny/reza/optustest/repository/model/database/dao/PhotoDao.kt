package com.moeiny.reza.optustest.repository.model.database.dao

import androidx.room.*
import com.moeiny.reza.optustest.repository.database.entitiy.PhotoEntity


@Dao
interface PhotoDao {

    @Query("SELECT * FROM Photos ORDER BY photo_id ")
    fun getAll(): List<PhotoEntity>

    @Query("SELECT * FROM Photos WHERE photo_id = :id ")
    fun findPhotoByid(id: Int): PhotoEntity

    @Query("SELECT * FROM Photos WHERE album_id = :albumId ")
    fun findPhotoByAlbumid(albumId: Int): List<PhotoEntity>

    @Query("DELETE FROM Photos")
    fun deleteAll()

    @Insert
    fun insert(photo: PhotoEntity)

    @Update
    fun update(photo: PhotoEntity)

    @Delete
    fun delete(photo: PhotoEntity)
}




