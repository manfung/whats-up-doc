package com.tephra.mc.whatsupdoc.di.modules

import dagger.Module
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import androidx.lifecycle.ViewModel
import com.tephra.mc.whatsupdoc.di.ViewModelFactory
import com.tephra.mc.whatsupdoc.di.ViewModelKey
import com.tephra.mc.whatsupdoc.ui.book.BookConsultationViewModel
import com.tephra.mc.whatsupdoc.ui.login.LoginViewModel

import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookConsultationViewModel::class)
    internal abstract fun bookConsultationViewModel(viewModel: BookConsultationViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
