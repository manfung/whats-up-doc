package com.tephra.mc.whatsupdoc.di.modules

import com.tephra.mc.whatsupdoc.data.local.LocalStorage
import com.tephra.mc.whatsupdoc.data.repository.consultation.ConsultationRepo
import com.tephra.mc.whatsupdoc.data.repository.consultation.IConsultationRepo
import com.tephra.mc.whatsupdoc.data.repository.login.ILoginRepo
import com.tephra.mc.whatsupdoc.data.repository.login.LoginRpo
import com.tephra.mc.whatsupdoc.data.remote.LoginApiService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    internal fun provideLoginRepo(loginApiService: LoginApiService): ILoginRepo {
        return LoginRpo(loginApiService)
    }

    @Provides
    internal fun provideConsultationRepo(LocalSharedPreferences: LocalStorage): IConsultationRepo {
        return ConsultationRepo(LocalSharedPreferences)
    }



}