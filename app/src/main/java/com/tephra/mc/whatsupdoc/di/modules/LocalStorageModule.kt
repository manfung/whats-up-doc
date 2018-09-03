package com.tephra.mc.whatsupdoc.di.modules

import android.app.Application
import com.tephra.mc.whatsupdoc.data.local.LocalSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Singleton
    @Provides
    internal fun provideStorage(context: Application): LocalSharedPreferences {
        return LocalSharedPreferences(context)
    }
}