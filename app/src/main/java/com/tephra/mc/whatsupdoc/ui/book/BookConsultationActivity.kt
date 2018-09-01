package com.tephra.mc.whatsupdoc.ui.book

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.databinding.ActivityBookConsultationBinding
import com.tephra.mc.whatsupdoc.ui.base.BaseActivity
import com.tephra.mc.whatsupdoc.utils.NotificationUtils
import kotlinx.android.synthetic.main.activity_book_consultation.*
import java.text.SimpleDateFormat
import java.util.*

class BookConsultationActivity : BaseActivity() {

    private lateinit var bookConsultationViewModel: BookConsultationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setupViewModel()
        setUpDataBinding(R.layout.activity_book_consultation)
    }

    private fun setupViewModel() {

        bookConsultationViewModel = ViewModelProviders.of(this, viewModelFactory)[BookConsultationViewModel::class.java]

        bookConsultationViewModel.showTimePickerEvent.observe(this, Observer<Any> {
            showDatePicker()
        })

        bookConsultationViewModel.bookedEvent.observe(this, Observer<Any> {
            consultationBook()
        })

    }


    private fun setUpDataBinding(layoutId: Int) {
        val activityLoginBinding: ActivityBookConsultationBinding = DataBindingUtil.setContentView(this, layoutId)
        activityLoginBinding.viewModel = bookConsultationViewModel
    }

    private fun consultationBook() {
        val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds
        NotificationUtils().setNotification(mNotificationTime, this)
    }

    private fun showDatePicker() {

        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            bookConsultationViewModel.time = cal.timeInMillis
            tv_time.text = SimpleDateFormat("HH:mm").format(cal.time)
        }
        TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    private fun initViews() {
        setTitle(R.string.book_consultation_title)
    }

}
