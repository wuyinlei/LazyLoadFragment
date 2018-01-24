package ruolan.com.lazyloadfragment.data.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
/**
 * Created by wuyinlei on 2018/1/24.
 *
 * @function   retrofit工厂类
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit

    init {
        interceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content_Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl("https://easy-mock.com/mock/5a683c4a3d63972d717dc58b/")
                .client(initClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun initClient(): OkHttpClient? {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logInterceptor())
//                .connectTimeout(10000, TimeUnit.MILLISECONDS)
//                .readTimeout(10000, TimeUnit.MILLISECONDS)
//                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .build()

    }

    private fun logInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}