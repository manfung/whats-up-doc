package com.tephra.mc.whatsupdoc.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tephra.mc.whatsupdoc.data.repository.Resource
import com.tephra.mc.whatsupdoc.data.repository.login.ILoginRepo
import com.tephra.mc.whatsupdoc.data.model.User
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepo: ILoginRepo): ViewModel() {

    val email = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<String>()
    var isLoading: ObservableField<Boolean> = ObservableField()

    val loginResponse: MutableLiveData<Resource<User>> = MutableLiveData()

    fun login() {

        if(isValid()) {
            isLoading.set(true)
            launch(CommonPool) {
                val response = loginRepo.login("", "")
                isLoading.set(false)
                loginResponse.postValue(response)
            }
        }
    }

    fun isValid():Boolean {
        return (isItemValid(email, emailError) and isItemValid(password, passwordError))
    }

    private fun isItemValid(isItemData:MutableLiveData<String>, dataError:MutableLiveData<String>): Boolean {
        return if (isItemData.value == null || isItemData.value == "" ) {
            dataError.value = "Error"
            false
        } else {
            dataError.value = ""
            true
        }
    }

}
