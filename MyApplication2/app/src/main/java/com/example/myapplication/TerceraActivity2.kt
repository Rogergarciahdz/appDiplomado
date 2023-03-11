package com.example.myapplication


import android.Manifest
import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityTercera2Binding
import com.ingenieriajhr.blujhr.BluJhr


private lateinit var binding: ActivityTercera2Binding



class TerceraActivity2 : AppCompatActivity() {

    lateinit var blue: BluJhr
    var devicesBluetooth=ArrayList<String>()
    //nos indica si estamos recibiendo datos o no
    var initGraph = false
    //nos almacena el estado actual de la conexion bluetooth
    var stateConn = BluJhr.Connected.False
    var temp:Float=0.0F
    var cont:Float =0.0F
    var temp1:Float=0.0F
    var cont1:Float =0.0F
    var temp2:Float=0.0F
    var cont2:Float =0.0F
    companion object{
        const val MY_CHANNEL_ID="myChannel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTercera2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        blue = BluJhr(this)
        blue.onBluetooth()
        binding.btnViewDevice.setOnClickListener {
            when (binding.containerGraph.isVisible) {
                false -> invisibleListDevice()
                true -> visibleListDevice()
            }
        }


        binding.listDeviceBluetooth.setOnItemClickListener { adapterView, view, i, l ->
            if (devicesBluetooth.isNotEmpty()) {
                blue.connect(devicesBluetooth[i])


                blue.setDataLoadFinishedListener(object : BluJhr.ConnectedBluetooth {
                    override fun onConnectState(state: BluJhr.Connected) {
                        stateConn = state
                        when (state) {
                            BluJhr.Connected.True -> {
                                Toast.makeText(applicationContext,"True",Toast.LENGTH_SHORT).show()
                                invisibleListDevice()
                                rxReceived()
                            }

                            BluJhr.Connected.Pending -> {
                                Toast.makeText(applicationContext,"Pending",Toast.LENGTH_SHORT).show()
                            }

                            BluJhr.Connected.False -> {
                                Toast.makeText(applicationContext,"False",Toast.LENGTH_SHORT).show()
                            }

                            BluJhr.Connected.Disconnect -> {
                                Toast.makeText(applicationContext,"Disconnect",Toast.LENGTH_SHORT).show()
                                visibleListDevice()
                            }

                        }
                    }
                })
            }
        }




        binding.btnInitStop.setOnClickListener {
            if (stateConn == BluJhr.Connected.True){
                initGraph = when(initGraph){
                    true->{
                        blue.bluTx("0")
                        binding.btnInitStop.text = "START"
                        false
                    }
                    false->{
                        blue.bluTx("1")
                        binding.btnInitStop.text = "STOP"
                        true
                    }
                }
            }
        }



}


    fun rxReceived() {
        blue.loadDateRx(object:BluJhr.ReceivedData{
            override fun rxDate(rx: String) {
               // println("------------------- RX $rx --------------------")

                if (rx.contains("Correctamente sentado")){
                    temp++
                    val res:Float=temp%60
                    if (res==0.0F){

                        cont++
                        binding.textView.text="tu tiempo bien sentado es : $cont minutos"

                    }

                }else if (rx.contains("Incorrectamente sentado")){

                    temp1++
                    val res1:Float=temp1%60
                    if (res1==0.0F){

                        cont1++
                        binding.textView5.text="tu tiempo mal sentado sentado es : $cont1 minutos"
                        val rescont1=cont1%5
                        if (rescont1==0.0F){
                            createNotificacion()
                        }

                    }

                    }else if (rx.contains("Posición parcialmente incorrecta ")){
                    temp2++
                    val res2:Float=temp2%60
                    if (res2==0.0F){

                        cont2++
                        binding.textView4.text="tu tiempo parcialmente bien sentado es : $cont2 minutos"

                    }

                }


            }
        })
    }

    /**
     * invisible listDevice
     */
     fun invisibleListDevice() {
        binding.containerGraph.visibility = View.VISIBLE
        binding.containerDevice.visibility = View.GONE

        binding.btnViewDevice.text = "DEVICE"
    }

    /**
     * visible list device
     */
    private fun visibleListDevice() {
        binding.containerGraph.visibility = View.GONE
        binding.containerDevice.visibility = View.VISIBLE

        binding.btnViewDevice.text = "GraphView"

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!blue.stateBluetoooth() && requestCode == 100){
            blue.initializeBluetooth()
        }else{
            if (requestCode == 100){
                devicesBluetooth = blue.deviceBluetooth()
                if (devicesBluetooth.isNotEmpty()){
                    val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,devicesBluetooth)
                    binding.listDeviceBluetooth.adapter = adapter
                }else{
                    Toast.makeText(this, "No tienes vinculados dispositivos", Toast.LENGTH_SHORT).show()
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (blue.checkPermissions(requestCode,grantResults)){
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show()
            blue.initializeBluetooth()
        }else{
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
                blue.initializeBluetooth()
            }else{
                Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }



    fun createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(MY_CHANNEL_ID,"MySuperChannel", NotificationManager.IMPORTANCE_DEFAULT).apply {

            }
            val notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotificacion(){

        val intent = Intent(this,TerceraActivity2::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag=if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent:PendingIntent=PendingIntent.getActivity(this,0,intent,flag)

        var builder= NotificationCompat.Builder(this,MY_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle("Alerta postura incorrecta ")
            .setContentText("Cuidado llevas 5 min con mala postura intenta corregirla")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            if (ActivityCompat.checkSelfPermission(
                    this@TerceraActivity2,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                notify(1,builder.build())
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }

        }
    }
}//class