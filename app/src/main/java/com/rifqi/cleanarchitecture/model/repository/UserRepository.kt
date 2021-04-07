package com.rifqi.cleanarchitecture.model.repository

import com.rifqi.cleanarchitecture.model.api.UserApi

class UserRepository(private val api : UserApi) {
    fun getAllUsers() = api.getAllUsers()
}