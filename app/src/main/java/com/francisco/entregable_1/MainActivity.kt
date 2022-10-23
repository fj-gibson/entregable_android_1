package com.francisco.entregable_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isVisible
import com.francisco.entregable_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var position:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = findViewById<Spinner>(R.id.spinnerValues)
        val values = listOf(getString(R.string.select_option),getString(R.string.calculate_distances),getString(R.string.calculate_resistor),getString(
                    R.string.calculate_volume))
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,values)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                position = p2
//                if(p2>0){
//                    Toast.makeText(this@MainActivity,values[p2],Toast.LENGTH_LONG).show()
//                }
                if(p2==1){
                    binding.imageDinamic.setImageResource(R.drawable.distancia)
                    binding.messageBox.setText(R.string.alert_velocity)
                    binding.editTextNumberDecimal5.isVisible = true
                }
                if(p2==2){
                    binding.imageDinamic.setImageResource(R.drawable.resistor)
                    binding.messageBox.setText(R.string.calculate_volume)
                    binding.editTextNumberDecimal5.isVisible = true
                }
                if(p2==3){
                    binding.imageDinamic.setImageResource(R.drawable.volumen)
                    binding.messageBox.setText(R.string.alert_radio)
                    binding.editTextNumberDecimal5.isVisible = false
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.buttonCalculate.setOnClickListener(){
            var value_a = 1.00F
            var value_b = 1.00F
            var error = 0
            if(position==0){
                Toast.makeText(this@MainActivity,R.string.please_select_option,Toast.LENGTH_SHORT).show()
            }else{
                if(binding.editTextNumberDecimal4.text.toString()==""){
                    error +=1
                    Toast.makeText(this@MainActivity,R.string.please_value_a,Toast.LENGTH_SHORT).show()
                    binding.editTextNumberDecimal4.setText("1")
                }else{
                    value_a = binding.editTextNumberDecimal4.text.toString().toFloat()
                }
                if(binding.editTextNumberDecimal5.text.toString()==""){
                    Toast.makeText(this@MainActivity,R.string.please_value_b,Toast.LENGTH_SHORT).show()
                    binding.editTextNumberDecimal5.setText("1")
                }
                value_b = binding.editTextNumberDecimal5.text.toString().toFloat()
                if(position<3 && value_a<=0 && value_b<=0 ){
                    error +=1
                    Toast.makeText(this@MainActivity,R.string.please_value_a_b,Toast.LENGTH_SHORT).show()
                }
                if(position==3 && value_a<=0){
                    error +=1
                    Toast.makeText(this@MainActivity,R.string.please_a_zero,Toast.LENGTH_SHORT).show()
                }
                if(error==0){
                    intent = Intent(this,ResultActivity::class.java)
                    intent.putExtra("position",position)
                    intent.putExtra("value_a",value_a)
                    intent.putExtra("value_b",value_b)
                    startActivity(intent)
                }
            }
        }
    }
}