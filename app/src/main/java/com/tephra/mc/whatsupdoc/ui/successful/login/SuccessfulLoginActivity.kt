package com.tephra.mc.whatsupdoc.ui.successful.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.ui.base.BaseActivity
import com.tephra.mc.whatsupdoc.ui.consultation.ConsultationActivity
import com.tephra.mc.whatsupdoc.ui.book.BookConsultationActivity
import com.tephra.mc.whatsupdoc.ui.consultation.VideoActivity

class SuccessfulLoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_login)
    }

    fun onBtnClicked(v: View) {

        var intent = when (v.id) {
            R.id.btn_patient_details -> Intent(this, BookConsultationActivity::class.java)
            else -> {
                Intent(this, VideoActivity::class.java)
            }
        }
        startActivity(intent)
    }
}
