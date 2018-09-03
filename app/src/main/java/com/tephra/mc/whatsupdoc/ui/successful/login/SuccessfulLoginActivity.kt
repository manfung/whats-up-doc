package com.tephra.mc.whatsupdoc.ui.successful.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.ui.base.BaseActivity
import com.tephra.mc.whatsupdoc.ui.book.BookConsultationActivity
import com.tephra.mc.whatsupdoc.ui.consultation.VideoActivity
import kotlinx.android.synthetic.main.activity_successful_login.*

class SuccessfulLoginActivity : BaseActivity() {

    // Quick Hack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_login)
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val bookedTime = sharedPref.getLong(getString(R.string.preference_time_key), 0)
        if  (bookedTime > 0) {
            btn_consultation.visibility = View.VISIBLE
        }
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
