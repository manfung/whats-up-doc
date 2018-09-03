package com.tephra.mc.whatsupdoc.ui.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tephra.mc.whatsupdoc.data.model.Booking
import com.tephra.mc.whatsupdoc.data.repository.consultation.IConsultationRepo
import javax.inject.Inject

class BookConsultationViewModel @Inject constructor(private val consultationRepo: IConsultationRepo): ViewModel() {

    val showTimePickerEvent = MutableLiveData<Any>()
    val bookedEvent = MutableLiveData<Any>()

    val symptoms = MutableLiveData<String>()
    val why = MutableLiveData<String>()
    var time: Long = 0

    init {
        val booking = consultationRepo.get()
        symptoms.value = booking.symptoms
        why.value = booking.why
    }

    fun selectTime() {
        showTimePickerEvent.value = ""
    }

    fun book() {

        val booking = Booking(symptoms.value!!, why.value!!, time)
        consultationRepo.save(booking)
        bookedEvent.value = ""
    }
}

