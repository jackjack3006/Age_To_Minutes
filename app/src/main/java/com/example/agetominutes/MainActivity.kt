package com.example.agetominutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnclickme = findViewById<Button>(R.id.btnDatePicker)
        btnclickme.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }
    

    fun clickDatePicker(view: View)
    {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener
                {
                    view,
                    selectedYear,
                    selectedMonth,
                    selectedDayOfMonth ->

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                    val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                    tvSelectedDate.setText(selectedDate)


                    val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)
                    val selectedDateInMinutes = theDate!!.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateToMinutes = currentDate!!.time / 60000

                    val AgeInMinutes = currentDateToMinutes - selectedDateInMinutes
                    val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                    tvSelectedDateInMinutes.setText(AgeInMinutes.toString())

                }, year, month, day)
        dpd.datePicker.setMaxDate(Date().time)
        dpd.show()
    }
}
