package com.example.temperatureconverter

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var selectedUnit: String
    private val decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedUnit = "Fahrenheit"

        val editInput: EditText = findViewById(R.id.editInput)
        val textResult: TextView = findViewById(R.id.textResult)
        val selectType: LinearLayout = findViewById(R.id.selectType)
        val textType: TextView = findViewById(R.id.textType)

        selectType.setOnClickListener {
            showAlertDialog(textType)
        }

        editInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val inputVal = editInput.text.toString()
                if (inputVal.isNotEmpty()) {
                    val doubleInput = inputVal.toDouble()
                    val resultText: String = if (selectedUnit == "Fahrenheit") {
                        decimalFormat.format((doubleInput - 32) * 5 / 9)
                    } else {
                        decimalFormat.format((doubleInput * 9 / 5) + 32)
                    }
                    textResult.text = resultText
                }
            }
        })
    }

    private fun showAlertDialog(textType: TextView) {
        val alertDialog: Builder = Builder(this)
        alertDialog.setTitle("Select Unit") //Setting title for alertBox
        val items = arrayOf("Fahrenheit", "Celsius")
        val checkedItem = if (selectedUnit == "Fahrenheit") 0 else 1
        alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->
            selectedUnit = items[which]
            textType.text = items[which]
            dialog.dismiss()
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}
