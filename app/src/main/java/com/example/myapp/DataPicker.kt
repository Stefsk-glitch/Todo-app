package com.example.myapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar

class DataPicker : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val selectedDate = Calendar.getInstance()
        selectedDate.set(year, month, day)

        if (selectedDate.after(Calendar.getInstance())) {
            val formattedDate = SimpleDateFormat("yyyy-MM-dd").format(selectedDate.time)
            (activity as? MainActivity)?.onDateSelected(formattedDate)
        } else {
            Toast.makeText(requireContext(), "Please select a date later than the current date", Toast.LENGTH_SHORT).show()
        }
    }
}