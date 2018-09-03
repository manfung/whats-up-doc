package com.tephra.mc.whatsupdoc.data.repository.consultation

import com.tephra.mc.whatsupdoc.data.local.LocalStorage
import com.tephra.mc.whatsupdoc.data.model.Booking
import javax.inject.Inject


class ConsultationRepo @Inject constructor(private val storage: LocalStorage): IConsultationRepo {
    override fun get(): Booking {
        return storage.get()
    }

    override fun save(booking:Booking) {
        storage.save(booking)
    }

}