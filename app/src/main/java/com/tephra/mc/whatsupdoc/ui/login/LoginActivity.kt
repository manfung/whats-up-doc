package com.tephra.mc.whatsupdoc.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.data.model.User
import com.tephra.mc.whatsupdoc.data.repository.Resource
import com.tephra.mc.whatsupdoc.data.repository.Status
import com.tephra.mc.whatsupdoc.databinding.ActivityLoginBinding
import com.tephra.mc.whatsupdoc.ui.base.BaseActivity
import com.tephra.mc.whatsupdoc.ui.successful.login.SuccessfulLoginActivity

class LoginActivity : BaseActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setUpDataBinding(R.layout.activity_login)
    }

    private fun setupViewModel() {

        loginViewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]

        loginViewModel.loginResponse.observe(this, Observer<Resource<User>> {
            processLoginResponse(it!!)
        })
    }

    private fun setUpDataBinding(layoutId: Int) {
        val activityLoginBinding: ActivityLoginBinding = DataBindingUtil.setContentView(this, layoutId)
        activityLoginBinding.viewModel = loginViewModel

        activityLoginBinding.setLifecycleOwner(this)
    }

    private fun processLoginResponse(resource: Resource<User>) {

        if (resource.status == Status.SUCCESS) {
            startActivity(Intent(this, SuccessfulLoginActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, getString(R.string.login_error_txt) + " " + resource.message, Toast.LENGTH_SHORT).show()
        }

    }

}
