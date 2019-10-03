package miguel.rossi.punkapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import miguel.rossi.punkapi.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.punkapi.com/v2/"

fun getApiClient(baseUrl: String = BASE_URL): Retrofit =
    Retrofit.Builder().apply {
        baseUrl(baseUrl)
        if (BuildConfig.DEBUG)
            client(getClient())
        addConverterFactory(MoshiConverterFactory.create(moshi))
    }.build()

private fun getClient() = OkHttpClient.Builder().addInterceptor(loginInterceptor()).build()

private fun loginInterceptor(): Interceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
