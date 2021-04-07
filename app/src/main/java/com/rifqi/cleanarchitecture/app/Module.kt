package com.rifqi.cleanarchitecture.app

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rifqi.cleanarchitecture.model.api.UserApi
import com.rifqi.cleanarchitecture.model.repository.UserRepository
import com.rifqi.cleanarchitecture.utils.Constants.BASE_URL
import com.rifqi.cleanarchitecture.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}

val repositoryModule = module {
    single { UserRepository(get()) }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {
    fun provideGson() : Gson{
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient() : OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return  okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory : Gson, client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(),get()) }
}