package com.example.day11_alarm

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.day11_alarm.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        calendar = Calendar.getInstance()

        binding.datePicker.setOnClickListener(datePickerHandler)
        binding.timePicker.setOnClickListener(timePickerHandler)
        binding.confirmButton.setOnClickListener(confirmButtonHandler)
    }

    private val datePickerHandler = View.OnClickListener {
        DatePickerDialog(
            this, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE)
        ).show()
    }

    private val timePickerHandler = View.OnClickListener {
        TimePickerDialog(
            this, timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    private val confirmButtonHandler = View.OnClickListener {
        showConfirmDialog()
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, date ->
        calendar.set(year, month, date)

        val time = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN)
        binding.datePicker.text = time.format(calendar.time)
    }

    private val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val time = SimpleDateFormat("HH:mm", Locale.TAIWAN)
        binding.timePicker.text = time.format(calendar.time)
    }

    private fun showConfirmDialog() {
        val time = SimpleDateFormat("yyyy:MM:dd HH:mm", Locale.TAIWAN).format(calendar.time)
        // Setup dialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Party time confirm")
        dialogBuilder.setMessage(time)
        // Setup dialog choice buttons and decide the event after pushing them
        dialogBuilder.setPositiveButton("Confirm") { _, _ ->
            Toast.makeText(this, "confirm", Toast.LENGTH_SHORT).show()
        }
        dialogBuilder.setNegativeButton("Cancel") { _, _ ->
            Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show()
        }
        // Create dialog and show it
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}