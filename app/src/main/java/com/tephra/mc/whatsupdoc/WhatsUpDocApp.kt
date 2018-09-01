package com.tephra.mc.whatsupdoc

import android.app.Activity
import android.app.Application
import com.tephra.mc.latestnews.di.components.DaggerAppComponent
import com.tephra.mc.whatsupdoc.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class WhatsUpDocApp: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}