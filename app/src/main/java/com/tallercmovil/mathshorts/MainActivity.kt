package com.tallercmovil.mathshorts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources

class MainActivity : AppCompatActivity() {
//    Fields
    private lateinit var mathSpinner: Spinner
    private lateinit var ivFormula: ImageView
    private lateinit var tvFirstParam: TextView
    private lateinit var tvSecondParam: TextView
    private lateinit var tvThridParam: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mathSpinner = findViewById(R.id.spinnerMathFormulas)
        ivFormula = findViewById(R.id.ivFormula)
        tvFirstParam = findViewById(R.id.tvFirstParam)
        tvSecondParam = findViewById(R.id.tvSecondParam)
        tvThridParam = findViewById(R.id.tvThirdParam)

        mathSpinner.setPopupBackgroundDrawable(AppCompatResources.getDrawable(this@MainActivity,R.color.lavender_purple))

        ArrayAdapter.createFromResource(
            this,
            R.array.math_formulas,
            R.layout.spinner_myitem
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_myitem)
            mathSpinner.adapter = adapter
        }

        mathSpinner.onItemSelectedListener  = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(av: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                when(position)
                {
                    0 -> {
                        ivFormula.setImageDrawable(AppCompatResources.getDrawable(this@MainActivity,R.drawable.ohmlaw))
//                        Setting up hint for inputs
                        tvFirstParam.setHint(R.string.resistance_paremeter)
                        tvSecondParam.setHint(R.string.current_parameter)
                        tvThridParam.visibility = View.INVISIBLE

                    }
                    1 -> {
                        ivFormula.setImageDrawable(AppCompatResources.getDrawable(this@MainActivity,R.drawable.voltagedivisor))
                        tvFirstParam.setHint(R.string.voltage_in_parameter)
                        tvSecondParam.setHint(R.string.resistance_one_parameter)
                        tvThridParam.visibility = View.VISIBLE
                        tvThridParam.setHint(R.string.resistance_two_parameter)

                    }
                    2 -> {
                        ivFormula.setImageDrawable(AppCompatResources.getDrawable(this@MainActivity,R.drawable.powerohm))
                        tvFirstParam.setHint(R.string.voltage_power)
                        tvSecondParam.setHint(R.string.current_parameter)
                        tvThridParam.visibility = View.INVISIBLE
                    }
                }
            }

            override fun onNothingSelected(av: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

}