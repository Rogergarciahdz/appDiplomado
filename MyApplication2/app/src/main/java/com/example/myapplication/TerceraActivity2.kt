package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityTercera2Binding

private lateinit var binding: ActivityTercera2Binding
class TerceraActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTercera2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnregresar.setOnClickListener { onBackPressed() }



    }

    fun matrizI(){

    }

    fun matrizD(){

    }

}