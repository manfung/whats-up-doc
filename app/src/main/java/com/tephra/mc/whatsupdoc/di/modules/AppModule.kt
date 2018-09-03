package com.tephra.mc.whatsupdoc.di.modules

import android.app.Application
import com.tephra.mc.whatsupdoc.data.local.LocalStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class,
                    ServiceModule::class,
                    ActivityModule::class,
                    RepositoryModule::class])
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Singleton
    @Provides
    internal fun provideStorage(context: Application): LocalStorage {
        return LocalStorage(context)
    }
}