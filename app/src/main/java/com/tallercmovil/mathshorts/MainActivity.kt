package com.tallercmovil.mathshorts

import android.content.Intent
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
    private var flagSpinner: Int = 0

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
                        flagSpinner = 0
                    }
                    1 -> {
                        ivFormula.setImageDrawable(AppCompatResources.getDrawable(this@MainActivity,R.drawable.voltagedivisor))
                        tvFirstParam.setHint(R.string.voltage_in_parameter)
                        tvSecondParam.setHint(R.string.resistance_one_parameter)
                        tvThridParam.visibility = View.VISIBLE
                        tvThridParam.setHint(R.string.resistance_two_parameter)
                        flagSpinner = 1

                    }
                    2 -> {
                        ivFormula.setImageDrawable(AppCompatResources.getDrawable(this@MainActivity,R.drawable.powerohm))
                        tvFirstParam.setHint(R.string.voltage_power)
                        tvSecondParam.setHint(R.string.current_parameter)
                        tvThridParam.visibility = View.INVISIBLE
                        flagSpinner = 2

                    }
                }
            }

            override fun onNothingSelected(av: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
//    Mathematical functions for Math Shots!
    private fun voltageOhm(current: Int=0, resistance: Int=0) : Int {
        return current * resistance
    }

    private fun voltageDivisor(voltageIn: Int=0, resistance1: Int=0, resistance2: Int=0): Int {
        return (voltageIn) * ((resistance2) / (resistance1 + resistance2))
    }

    private fun potencyOhm(voltage: Int=0, current: Int=0) : Int
    {
        return voltage * current
    }

    fun calculateClick(view: View) {
        val intent = Intent(this,ResultActivity::class.java)
        when(flagSpinner){
            0 -> {
                val ohmRes = voltageOhm(tvFirstParam.text.toString().toInt(), tvSecondParam.text.toString().toInt())
                val ohmResString = "$ohmRes [V]"
                intent.putExtra("res",ohmResString)
            }
            1 -> {
                val divVolRes = voltageDivisor(tvFirstParam.text.toString().toInt(),tvSecondParam.text.toString().toInt(),
                tvThridParam.text.toString().toInt())
                val divVolResString = "$divVolRes [V]"
                intent.putExtra("res",divVolResString)
            }
            2 -> {
                val potencyRes = potencyOhm(tvFirstParam.text.toString().toInt(),tvSecondParam.text.toString().toInt())
                val potencyResString = "$potencyRes [W]"
                intent.putExtra("res",potencyResString)
            }
        }
        startActivity(intent)

    }


}