package com.tephra.mc.whatsupdoc.data.repository.consultation

import com.tephra.mc.whatsupdoc.data.model.Booking

interface IConsultationRepo {

    fun save(booking: Booking)

    fun get(): Booking
}