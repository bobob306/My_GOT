package com.bensdevelops.myGOT.network.service

import com.bensdevelops.myGOT.network.model.BookModel
import com.bensdevelops.myGOT.network.model.CharacterModel
import com.bensdevelops.myGOT.network.model.HouseModel
import com.bensdevelops.myGOT.network.repository.FlashCardDto
import com.bensdevelops.myGOT.network.repository.FlashCardRepository
import com.bensdevelops.myGOT.network.repository.FlashCardRepositoryImpl
import com.bensdevelops.myGOT.network.repository.Repository
import com.bensdevelops.myGOT.network.repository.RepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestoreCollection() : CollectionReference {
        val cr = FirebaseFirestore.getInstance().collection("FlashCardList")
        return cr
    }

    @Provides
    fun provideFlashCardRepository(cr: CollectionReference): FlashCardRepository {
        return FlashCardRepositoryImpl(cr)
    }

    // All requests will append this base url
    private const val BASE_URL = "https://anapioficeandfire.com/api/"

    // Provide the client that makes the call and logs the call in the logcat
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return OkHttpClient.Builder().addInterceptor(logger).build()
    }

    // adds the call converter so that the json becomes usable
    // adds the call adapter so the response is wrapped in a Result class
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    fun provideRepository(retrofitApi: RetrofitApi): Repository {
        return RepositoryImpl(retrofitApi)
    }

}

// different calls made throughout the app all return something wrapped in the result class
interface RetrofitApi {
    @GET("books/{index}")
    suspend fun getBook(@Path("index") index: String): Result<BookModel>

    @GET("characters/{index}")
    suspend fun getCharacter(@Path("index") index: String): Result<CharacterModel>

    @GET("houses/{index}")
    suspend fun getHouse(@Path("index") index: String): Result<HouseModel>

    @GET("books/")
    suspend fun getBooks(): Result<List<BookModel>>

    @GET("houses/")
    suspend fun getHouses(): Result<List<HouseModel>>

    @GET("characters/")
    suspend fun getCharacters(): Result<List<CharacterModel>>
}

class ResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType) {
            return null
        }
        val upperBound = getParameterUpperBound(0, returnType)

        return if (upperBound is ParameterizedType && upperBound.rawType == Result::class.java) {
            object : CallAdapter<Any, Call<Result<*>>> {
                override fun responseType(): Type = getParameterUpperBound(0, upperBound)

                override fun adapt(call: Call<Any>): Call<Result<*>> =
                    ResultCall(call) as Call<Result<*>>
            }
        } else {
            null
        }
    }
}

// this wraps the response inside a response and either as success or catches errors in Result.failure()
class ResultCall<T>(val delegate: Call<T>) :
    Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                response.code(),
                                Result.success(response.body()!!)
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                Result.failure(
                                    HttpException(response)
                                )
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    val errorMessage = when (t) {
                        is IOException -> "No internet connection"
                        is HttpException -> "Something went wrong!"
                        else -> t.localizedMessage
                    }
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(Result.failure(RuntimeException(errorMessage, t)))
                    )
                }
            }
        )
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun execute(): Response<Result<T>> {
        return Response.success(Result.success(delegate.execute().body()!!))
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun clone(): Call<Result<T>> {
        return ResultCall(delegate.clone())
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }
}



