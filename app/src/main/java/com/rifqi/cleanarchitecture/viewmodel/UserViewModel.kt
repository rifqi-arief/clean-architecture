package com.rifqi.cleanarchitecture.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rifqi.cleanarchitecture.model.entity.User
import com.rifqi.cleanarchitecture.model.repository.UserRepository
import com.rifqi.cleanarchitecture.utils.LoadingState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repo : UserRepository) : ViewModel() {

    //for loading status
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState : LiveData<LoadingState>
    get() = _loadingState

    //for data
    private val _data = MutableLiveData<List<User>>()
    val data : LiveData<List<User>>
    get() = _data

    init {
        fetchData()
    }

    fun fetchData(){
        _loadingState.postValue(LoadingState.LOADING)
        repo.getAllUsers().enqueue(
            object : Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if(response.isSuccessful){
                        _data.postValue(response.body())
                        _loadingState.postValue(LoadingState.LOADED)
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    _loadingState.postValue(LoadingState.error(t.message))
                }
            }
        )
    }
}