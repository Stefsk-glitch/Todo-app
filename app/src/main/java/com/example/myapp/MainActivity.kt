package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Adapter(mutableListOf())

        val rvItems = findViewById<RecyclerView>(R.id.rvItems)
        val btnAddItem = findViewById<Button>(R.id.btnAddItem)

        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(this)

        btnAddItem.setOnClickListener {
            val datePicker = DataPicker()
            datePicker.show(supportFragmentManager, "DatePicker")
        }
    }

    fun onDateSelected(selectedDate: String) {

        val etText = findViewById<EditText>(R.id.etText)
        val text = etText.text.toString()

        if (text.isNotEmpty()) {
            val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
            val resultText = "$text \n$currentDate - $selectedDate"

            val item = Item(resultText)
            adapter.add(item)
            etText.text.clear()
        }
    }
}