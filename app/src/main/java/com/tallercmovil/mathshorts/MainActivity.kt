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
    private var flagContinue : Boolean = false


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
    private fun voltageOhm(current: Float, resistance: Float) : Float {
        return current * resistance
    }

    private fun voltageDivisor(voltageIn: Float, resistance1: Float, resistance2: Float): Float {
        return ((voltageIn * resistance2) / (resistance1 + resistance2))
    }

    private fun potencyOhm(voltage: Float, current: Float) : Float
    {
        return voltage * current
    }

    fun calculateClick(view: View) {
        val intent = Intent(this,ResultActivity::class.java)
        when(flagSpinner){
            0 -> {
                if (tvFirstParam.text.toString().isEmpty() || tvSecondParam.text.toString().isEmpty())
                {
                    Toast.makeText(this@MainActivity,getString(R.string.waning_empty_param),Toast.LENGTH_SHORT).show()
                    tvFirstParam.error = getString(R.string.error_param)
                    tvFirstParam.requestFocus()
                    tvSecondParam.error = getString(R.string.error_param)
                    tvSecondParam.requestFocus()
                } else {
                    val ohmRes = voltageOhm(
                        tvFirstParam.text.toString().toFloat(),
                        tvSecondParam.text.toString().toFloat()
                    )
                    val ohmResRound: Float = String.format("%.4f", ohmRes).toFloat()
                    val ohmResString = "$ohmResRound [V]"
                    intent.putExtra("res", ohmResString)
                    flagContinue = true
                }
            }
            1 -> {
//                condition for empty values on input parameters
                if(tvSecondParam.text.isEmpty() || tvThridParam.text.isEmpty() || (tvFirstParam.text.isEmpty())){
                    tvFirstParam.error = getString(R.string.error_param)
                    tvFirstParam.requestFocus()
                    tvSecondParam.error = getString(R.string.error_param)
                    tvSecondParam.requestFocus()
                    tvThridParam.error = getString((R.string.error_param))
                    tvThridParam.requestFocus()
                } else if ((tvSecondParam.text.toString().toFloat() == 0F) || (tvThridParam.text.toString().toFloat() == 0F)){
                    tvSecondParam.error = getString(R.string.error_resistance1_divVolt)
                    tvSecondParam.requestFocus()
                    tvThridParam.error = getString((R.string.error_resistance2_divVolt))
                    tvThridParam.requestFocus()
                    Toast.makeText(this@MainActivity, getString(R.string.error_denominator_voltagedivisor),Toast.LENGTH_LONG).show()
                }
                else {
                    val divVolRes = voltageDivisor(
                        tvFirstParam.text.toString().toFloat(),
                        tvSecondParam.text.toString().toFloat(),
                        tvThridParam.text.toString().toFloat()
                    )
                    val divVolResRound: Float = String.format("%.4f", divVolRes).toFloat()
                    val divVolResString = "$divVolResRound [V]"
                    intent.putExtra("res", divVolResString)
                    flagContinue = true
                }
//
            }
            2 -> {
                if(tvFirstParam.text.toString().isEmpty() or tvSecondParam.text.toString().isEmpty()){
                    tvFirstParam.error = getString(R.string.error_param)
                    tvFirstParam.requestFocus()
                    tvSecondParam.error = getString(R.string.error_param)
                    tvSecondParam.requestFocus()
                } else if((tvFirstParam.text.toString().toFloat() == 0F) ||(tvSecondParam.text.toString().toFloat() == 0F)){
                    tvFirstParam.error = getString(R.string.error_voltage_zero)
                    tvFirstParam.requestFocus()
                    tvSecondParam.error = getString(R.string.error_current_zero)
                    tvSecondParam.requestFocus()
                }
                else {
                    val potencyRes = potencyOhm(
                        tvFirstParam.text.toString().toFloat(),
                        tvSecondParam.text.toString().toFloat()
                    )
                    val potencyResRound: Float = String.format("%.4f", potencyRes).toFloat()
                    val potencyResString = "$potencyResRound [W]"
                    intent.putExtra("res", potencyResString)
                    flagContinue = true
                }
            }
        }
        if(flagContinue)
            startActivity(intent)

    }


}