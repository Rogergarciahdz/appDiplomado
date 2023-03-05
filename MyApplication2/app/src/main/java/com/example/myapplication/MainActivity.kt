package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.botonNext.setOnClickListener {
            checkValue()
        }
    }

    fun checkValue(){
        if(binding.etname.text.isNotEmpty()){
            //vamos a la nueva pantalla
            val intent = Intent(this,SegundaActivity2::class.java)
            intent.putExtra("INTENT_NAME",binding.etname.text)
            startActivity(intent)

        }else{
            showErrorname()
        }
    }

    fun showErrorname(){
        Toast.makeText(this, "ingresa un nombre valido", Toast.LENGTH_SHORT).show()
    }
}