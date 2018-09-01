package com.tephra.mc.whatsupdoc.di.modules

import android.app.Application
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
}