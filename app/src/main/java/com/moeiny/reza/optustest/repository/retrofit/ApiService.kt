package com.moeiny.reza.optustest.repository.retrofit


import com.moeiny.reza.optustest.repository.model.Photo
import com.moeiny.reza.optustest.repository.model.user.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users")
    fun getUsersInfo():Call<List<User>>

    @GET("photos")
    fun getPhotosInfo():Call<List<Photo>>
}