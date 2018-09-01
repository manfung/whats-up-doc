package com.tephra.mc.whatsupdoc.ui.consultation

import android.os.Bundle
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.ui.base.BaseActivity

class ConsultationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)
        initViews()
    }

    private fun initViews() {
        setTitle(R.string.consultation_title)
    }
}
