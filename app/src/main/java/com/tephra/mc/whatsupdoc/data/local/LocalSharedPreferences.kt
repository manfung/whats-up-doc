package com.tephra.mc.whatsupdoc.data.local

import android.content.Context
import com.tephra.mc.whatsupdoc.R
import com.tephra.mc.whatsupdoc.data.model.Booking
import javax.inject.Inject

class LocalSharedPreferences @Inject constructor(val context: Context) {

    fun save(booking: Booking) {

        val sharedPref = getSharedPreferences()

        with (sharedPref.edit()) {
            putLong(context.getString(R.string.preference_time_key), booking.time)
            putString(context.getString(R.string.preference_why_key), booking.why!!)
            putString(context.getString(R.string.preference_symptoms_key), booking.symptoms!!)
            commit()
        }
    }

    fun get(): Booking {
        val sharedPref = getSharedPreferences()

        val time = sharedPref.getLong(context.getString(R.string.preference_time_key), 0)
        val why = sharedPref.getString(context.getString(R.string.preference_why_key), "")
        val symptoms = sharedPref.getString(context.getString(R.string.preference_symptoms_key), "")
        return Booking(symptoms, why, time)
    }

    private fun getSharedPreferences() = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)



}