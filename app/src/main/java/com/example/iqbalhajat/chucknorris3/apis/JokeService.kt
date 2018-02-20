package com.example.iqbalhajat.chucknorris3.apis

/**
 * Created by IqbalHajat on 18/02/2018.
 */
import com.example.iqbalhajat.chucknorris3.models.Model
import com.example.iqbalhajat.chucknorris3.models.Joke
import com.example.iqbalhajat.chucknorris3.models.Response
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeService {

    @GET("jokes/random")
    fun getJoke(@Query("firstName") firstName: String,
                @Query("lastName") lastName: String): Observable<Model.Result>


    @GET("/jokes/random/20")
    fun getJokes(): Observable<Response<List<Joke>>>

    @GET("/jokes/random/20")
    fun getJokesNonReactive(): Call<Response<List<Joke>>>

    companion object {
        fun create(): JokeService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.icndb.com/")
                    .build()

            return retrofit.create(JokeService::class.java)
        }
    }
}
