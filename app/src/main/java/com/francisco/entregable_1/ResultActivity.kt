package com.francisco.entregable_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.francisco.entregable_1.databinding.ActivityMainBinding
import com.francisco.entregable_1.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    var result = 0.00F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras
        var position = data?.getInt("position")
        var value_a = data?.getFloat("value_a")?: 0.00F
        var value_b = data?.getFloat("value_b")?: 0.00F
        if(position==1){
            result = value_a * value_b
            binding.textLetter.setText(R.string.result_distance)
            binding.textResult.setText("${result.toString()} [m]")
        }
        if(position==2){
            result = value_a / value_b
            binding.textLetter.setText(R.string.result_resistor)
            binding.textResult.setText("${result.toString()} [ohms]")
        }
        if(position==3){
            result = ((4/3) * 3.1416 * (value_a*value_a*value_a)).toFloat()
            binding.textLetter.setText(R.string.result_volume)
            binding.textResult.setText("${result.toString()} [v]")
        }

    }
}