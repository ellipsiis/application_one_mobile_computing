package com.tallercmovil.mathshorts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    private lateinit var mathSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mathSpinner = findViewById(R.id.spinnerMathFormulas)
        mathSpinner.setPopupBackgroundDrawable(getDrawable(R.color.lavender_purple))
        ArrayAdapter.createFromResource(
            this,
            R.array.math_formulas,
            R.layout.spinner_myitem
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_myitem)
            mathSpinner.adapter = adapter
        }
    }

}