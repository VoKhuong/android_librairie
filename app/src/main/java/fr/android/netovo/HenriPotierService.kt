package fr.android.netovo

import retrofit2.Call
import retrofit2.http.GET

interface HenriPotierService {
    @GET("books")
    fun getBookList(): Call<Array<Book>>
}
