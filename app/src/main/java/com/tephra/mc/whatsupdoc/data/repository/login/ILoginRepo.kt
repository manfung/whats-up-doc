package com.tephra.mc.whatsupdoc.data.repository.login

import com.tephra.mc.whatsupdoc.data.model.User
import com.tephra.mc.whatsupdoc.data.repository.Resource

interface ILoginRepo {

    suspend fun login(email: String, password: String): Resource<User>

}