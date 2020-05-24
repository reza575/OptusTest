package com.moeiny.reza.optustest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.moeiny.reza.optustest.repository.UsersRepository
import com.moeiny.reza.optustest.repository.database.entitiy.PhotoEntity
import com.moeiny.reza.optustest.repository.database.entitiy.UserEntity
import com.moeiny.reza.optustest.repository.model.Photo

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private  var usersRepository: UsersRepository
    private  var allUsersData:List<UserEntity>
    private  var allPhotosData:List<PhotoEntity>
    var photoLists = ArrayList<Photo>()

    init {
        usersRepository= UsersRepository(application)
        allUsersData=usersRepository.getAllUsers()
        allPhotosData=usersRepository.getAllPhotos()
    }


     fun setPhotoList(photoList:List<Photo>){
        photoLists= photoList as ArrayList<Photo>
    }

     fun getPhotoList():List<Photo>{
        return(photoLists)
    }

    //////////////// implementing Function to Access Movie Details on Database//////////

    fun insertUser(userEntity: UserEntity){
        usersRepository.insertUser(userEntity)
    }

    fun updateUser(userEntity: UserEntity){
        usersRepository.updateUser(userEntity)
    }

    fun deleteUser(userEntity: UserEntity){
        usersRepository.deleteUser(userEntity)
    }

    fun deleteAllFilms(){
        usersRepository.deleteAllUsers()
    }

    fun getuserById(id: Int):UserEntity{
        return usersRepository.getUserById(id)
    }

    fun getAllUsers():List<UserEntity>{
        return allUsersData
    }

    //////////////// implementing Function to Access Character Details on Database//////////

fun insertPhoto(photoEntity: PhotoEntity){
    usersRepository.insertPhoto(photoEntity)
}

    fun updatePhoto(photoEntity: PhotoEntity){
        usersRepository.updatePhoto(photoEntity)
    }

    fun deletePhoto(photoEntity: PhotoEntity){
        usersRepository.deletePhoto(photoEntity)
    }

    fun deleteAllPhotos(){
        usersRepository.deleteAllPhotos()
    }

    fun getPhotoById(id: Int):PhotoEntity{
        return usersRepository.getPhotobyId(id)
    }

    fun getPhotoByAlbumId(albumId: Int):List<PhotoEntity>{
        return usersRepository.getPhotobyAlbumId(albumId)
    }

    fun getAllPhotos():List<PhotoEntity>{
        return allPhotosData
    }

//////////////Calling API methods//////////////////////////////

    fun getUsersInfo() {
        usersRepository.getUsersInfo()
    }

    fun getPhotosInfo() {
        usersRepository.getPhotosInfo()
    }
 }