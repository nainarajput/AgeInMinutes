package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinute)


        btnDatePicker.setOnClickListener {
           clickDatePicker()
        }
    }


    fun clickDatePicker () {     // the function of clickDatePicker is using by OnClickListener(which will work after the button is clicked)

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { view, year, month, dayOfMonth ->

                Toast.makeText(this,
                    "Year was $year, month was ${month+1}" +
                          ", day of month was $dayOfMonth",
                            Toast.LENGTH_LONG).show()

                val selectedDate = "$dayOfMonth/${month+1}/$year"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time /60000

                val currentDate = sdf.parse(sdf.format (System.currentTimeMillis()))
                val currentDateInMinutes = currentDate.time/ 6000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvAgeInMinutes?.text = differenceInMinutes.toString()


            },
            year,
            month,
            day
        ).show()

    }
}