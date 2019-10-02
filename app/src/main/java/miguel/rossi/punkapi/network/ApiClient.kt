package miguel.rossi.punkapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.punkapi.com/v2/"

fun getApiClient(baseUrl: String = BASE_URL): Retrofit = Retrofit.Builder().baseUrl(baseUrl)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
