package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySegunda2Binding
import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.util.*

private lateinit var binding: ActivitySegunda2Binding

class SegundaActivity2 : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySegunda2Binding.inflate(layoutInflater)
        setContentView(binding.root)








        binding.regresar.setOnClickListener { onBackPressed() }
        getAndShowName()
        binding.siguiente.setOnClickListener {

            startActivity(Intent(this,TerceraActivity2::class.java))
        }
    }


    fun getAndShowName(){
        val bundle=intent.extras
        val name=bundle?.get("INTENT_NAME")
        binding.textName.text="Bienvenido $name"
    }




}