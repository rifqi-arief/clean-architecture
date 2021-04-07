package com.rifqi.cleanarchitecture.model.api

import com.rifqi.cleanarchitecture.model.entity.User
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getAllUsers() : Call<List<User>>

}