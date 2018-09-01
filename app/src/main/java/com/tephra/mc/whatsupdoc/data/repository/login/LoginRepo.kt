package com.tephra.mc.whatsupdoc.data.repository.login

import com.tephra.mc.whatsupdoc.data.model.User
import com.tephra.mc.whatsupdoc.data.repository.Resource
import com.tephra.mc.whatsupdoc.data.remote.LoginApiService
import javax.inject.Inject

class LoginRpo @Inject constructor(private val loginApiService: LoginApiService): ILoginRepo {

    override suspend fun login(email: String, password: String): Resource<User> {

        return try {
            val response = loginApiService.login(email, password)
            val result = response.await()
            Resource.success(result)
        } catch (e: Throwable) {
            Resource.error(e.message!!, data = null)
        }

    }


}