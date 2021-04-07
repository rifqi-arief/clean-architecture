package com.rifqi.cleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.rifqi.cleanarchitecture.utils.Constants.TAG
import com.rifqi.cleanarchitecture.utils.LoadingState
import com.rifqi.cleanarchitecture.viewmodel.UserViewModel
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {

    private val userViewModel = get<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.data.observe(this, Observer { it ->
            it.forEach(){
                Log.i(TAG,"${it.id} ${it.login}")
            }
        })

        userViewModel.loadingState.observe(this, Observer {
        })

    }
}