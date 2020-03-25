package br.com.angelorobson.di

import android.content.Context
import br.com.angelorobson.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


val networkModule = module {

    single {
        val context = get() as Context
        val file = File(context.filesDir, "cache_dir")
        if (file.exists().not()) {
            file.mkdirs()
        }

        file
    }

    single {
        Cache(get<File>(), 10 * 1000 * 1000)
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }

    single {
        val httpClient = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.MINUTES)
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(get<HttpLoggingInterceptor>())
        }

        httpClient.cache(get<Cache>())
        httpClient.build()
    }

    single {
        val builder = GsonBuilder()
            .create()
        builder
    }

    single {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get<OkHttpClient>())
            .build()

        retrofit
    }

}

internal inline fun <reified T> createApi(retrofit: Retrofit) = retrofit.create(T::class.java)

