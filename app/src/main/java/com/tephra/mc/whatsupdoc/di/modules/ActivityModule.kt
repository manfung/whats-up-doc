package com.tephra.mc.whatsupdoc.di.modules

import com.tephra.mc.whatsupdoc.ui.login.LoginActivity
import com.tephra.mc.whatsupdoc.ui.book.BookConsultationActivity
import com.tephra.mc.whatsupdoc.ui.successful.login.SuccessfulLoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun successfulLoginActivity(): SuccessfulLoginActivity

    @ContributesAndroidInjector
    abstract fun bookConsultationActivity(): BookConsultationActivity

}