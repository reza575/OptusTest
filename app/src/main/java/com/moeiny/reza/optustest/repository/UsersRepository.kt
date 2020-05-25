package com.moeiny.reza.optustest.repository

import android.app.Application
import android.os.AsyncTask
import android.view.View
import com.moeiny.reza.optustest.repository.database.entitiy.PhotoEntity
import com.moeiny.reza.optustest.repository.database.entitiy.UserEntity
import com.moeiny.reza.optustest.repository.model.Photo
import com.moeiny.reza.optustest.repository.model.database.AppDatabase
import com.moeiny.reza.optustest.repository.model.database.dao.PhotoDao
import com.moeiny.reza.optustest.repository.model.database.dao.UserDao
import com.moeiny.reza.optustest.repository.model.user.User
import com.moeiny.reza.optustest.repository.retrofit.ApiClient
import com.moeiny.reza.optustest.repository.retrofit.ApiService
import com.moeiny.reza.optustest.view.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepository(application: Application){

    private  var userDao: UserDao
    private  var photoDao: PhotoDao

    private  var allUsersData:List<UserEntity>
    private  var allPhotosData:List<PhotoEntity>

    init {
        val db: AppDatabase = AppDatabase.getInstance(
            application.applicationContext )!!

        userDao = db.UserDao()
        photoDao = db.PhotoDao()

        allUsersData = userDao.getAll()
        allPhotosData = photoDao.getAll()

    }

    //////////////// implementing Function to Access User Details on Database//////////

    fun insertUser(userEntity: UserEntity){
        UserInsert(userDao).execute(userEntity)
    }

    fun updateUser(userEntity: UserEntity){
        UserUpdate(userDao).execute(userEntity)
    }

    fun deleteUser(userEntity: UserEntity){
        UserDelete(userDao).execute(userEntity)
    }

    fun deleteAllUsers(){
        userDao.deleteAll()
    }

    fun getAllUsers():List<UserEntity>{
        return allUsersData
    }

    fun getUserById(id:Int):UserEntity{
        return userDao.findByid(id)
    }

    private class UserInsert(userDao: UserDao): AsyncTask<UserEntity, Void, Void>(){

        private var userDao: UserDao
        init{
            this.userDao=userDao
        }

        override fun doInBackground(vararg p0: UserEntity): Void? {
            userDao.insert(p0[0])
            return null
        }

    }

    private class UserUpdate(userDao: UserDao): AsyncTask<UserEntity, Void, Void>(){

        private var userDao:UserDao
        init{
            this.userDao=userDao
        }

        override fun doInBackground(vararg p0: UserEntity): Void? {
            userDao.update(p0[0])
            return null
        }
    }

    private class UserDelete(userDao: UserDao): AsyncTask<UserEntity, Void, Void>(){

        private var userDao:UserDao
        init{
            this.userDao=userDao
        }

        override fun doInBackground(vararg p0: UserEntity): Void? {
            userDao.delete(p0[0])
            return null
        }
    }

    //////////////// implementing Function to Access Photos Details on Database//////////

    fun insertPhoto(photoEntity: PhotoEntity){
        photoInsert(photoDao).execute(photoEntity)
    }

    fun updatePhoto(photoEntity: PhotoEntity){
        PhotoUpdate(photoDao).execute(photoEntity)
    }

    fun deletePhoto(photoEntity: PhotoEntity){
        PhotoDelete(photoDao).execute(photoEntity)
    }

    fun deleteAllPhotos(){
        photoDao.deleteAll()
    }

    fun getAllPhotos():List<PhotoEntity>{
        return allPhotosData
    }

    fun getPhotobyId(id:Int):PhotoEntity{
        return photoDao.findPhotoByid(id)
    }

    fun getPhotobyAlbumId(albumId:Int):List<PhotoEntity>{
        return photoDao.findPhotoByAlbumid(albumId)
    }

    private class photoInsert(photoDao: PhotoDao): AsyncTask<PhotoEntity, Void, Void>(){

        private var photoDao: PhotoDao
        init{
            this.photoDao=photoDao
        }

        override fun doInBackground(vararg p0: PhotoEntity): Void? {
            photoDao.insert(p0[0])
            return null
        }

    }

    private class PhotoUpdate(photoDao: PhotoDao): AsyncTask<PhotoEntity, Void, Void>(){

        private var photoDao:PhotoDao
        init{
            this.photoDao=photoDao
        }

        override fun doInBackground(vararg p0: PhotoEntity): Void? {
            photoDao.update(p0[0])
            return null
        }
    }

    private class PhotoDelete(photoDao: PhotoDao): AsyncTask<PhotoEntity, Void, Void>(){

        private var photoDao:PhotoDao
        init{
            this.photoDao=photoDao
        }

        override fun doInBackground(vararg p0: PhotoEntity): Void? {
            photoDao.delete(p0[0])
            return null
        }
    }

    //////////////Implementing API methods to get data from API and save in database//////////////////////////////

    fun getUsersInfo(){

        var apiClient= ApiClient()
        var call: Call<List<User>> =apiClient.getClient().create(ApiService::class.java).getUsersInfo()

        call.enqueue(object : Callback<List<User>> {

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                //   Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                var users:List<User>?=response!!.body()
                var resultList = ArrayList<User>()
                resultList=users!! as ArrayList<User>
                /*
                * In this section :
                * 1 - All the Movie details fetch from Api
                * 2- Create a filmEntity model for each film and save in the Database
                */
                for (i in 0..resultList.size-1) {
                    var user = UserEntity(resultList[i].id, resultList[i].name,
                        resultList[i].username, resultList[i].email, resultList[i].address.street,
                        resultList[i].address.suite, resultList[i].address.city, resultList[i].address.zipcode,
                        resultList[i].address.geo.lat, resultList[i].address.geo.lng,
                        resultList[i].phone, resultList[i].website,
                        resultList[i].company.name, resultList[i].company.catchPhrase,resultList[i].company.bs)

                    if (getUserById(resultList[i].id) == null)
                        insertUser(user)
                }

                var view= HomeActivity.getView()
                view.visibility= View.INVISIBLE
                HomeActivity.move()
            }

        })
    }


    fun getPhotosInfo(){

        var apiClient= ApiClient()
        var call: Call<List<Photo>> =apiClient.getClient().create(ApiService::class.java).getPhotosInfo()

        call.enqueue(object : Callback<List<Photo>> {

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                //  Toast.makeText(context,t.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                var photos: List<Photo>?=response!!.body()

                /*
                * In this section :
                * 1 - All the characters details fetch from Api
                * 2- Create a characterEntity model for each character and save in the Database
                */

                photos?.forEachIndexed { index, photo ->
                    var photo = PhotoEntity(
                        photos[index].id, photos[index].albumId, photos[index].thumbnailUrl,
                        photos[index].title, photos[index].url)

                    if (getPhotobyId(photos[index].id) == null)
                        insertPhoto(photo)
                }
            }
        })
    }
}




