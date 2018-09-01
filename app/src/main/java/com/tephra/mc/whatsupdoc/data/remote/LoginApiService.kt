package com.tephra.mc.whatsupdoc.data.remote

import com.tephra.mc.whatsupdoc.data.model.User
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.*

interface LoginApiService {

    /**
     * Retrieve a list of articles. Should be using a POST but the endpoint only accepts GET
     */
    @GET("/todos/4")
    fun login(@Query("email") email: String,
              @Query("password") password: String ): Deferred<User>

}