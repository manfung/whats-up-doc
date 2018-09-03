package com.tephra.mc.whatsupdoc.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tephra.mc.whatsupdoc.data.model.User
import com.tephra.mc.whatsupdoc.data.repository.Resource
import com.tephra.mc.whatsupdoc.data.repository.login.LoginRpo
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var loginViewModel: LoginViewModel

    @Mock
    lateinit var loginRpo: LoginRpo

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(loginRpo)

    }

    @Test
    fun testIsValidSuccessful() {

        loginViewModel.email.value = "email"
        loginViewModel.password.value = "xxx"
        assertTrue(loginViewModel.isValid())
    }

    @Test
    fun testEmailErrorMessage() {

        loginViewModel.email.value = ""

        // create mock Observer class and add it as view models LiveData observer
        val observerEmailError = mock<Observer<String>>()
        loginViewModel.emailError.observeForever(observerEmailError)

        loginViewModel.isValid()

        // verify on observer object if transformation in view model matches the expected result
        verify(observerEmailError).onChanged("Error")

    }

    @Test
    fun testLogin() {

        loginViewModel.email.value = "email"
        loginViewModel.password.value = "xxx"

        // create a success response
        val userSuccess = User(
                id = 1,
                userId = 1,
                title = "title",
                completed = true
        )
        val resource = Resource.success(userSuccess)

        // create mock Observer class and add it as view models LiveData observer
        val observer = mock<Observer<Resource<User>>>()
        loginViewModel.loginResponse.observeForever(observer)

        // mock the LoginRepository response to the success object defined earlier
        Mockito.`when`(runBlocking { loginRpo.login("","") }).thenReturn(Resource.success(userSuccess))
        loginViewModel.login()

        var liveData =  MutableLiveData<Resource<User>> ()
        liveData.value = resource

        // verify on observer object if transformation in view model matches the success response
        verify(observer).onChanged(resource)
    }

}