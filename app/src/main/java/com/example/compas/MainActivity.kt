package com.example.compas

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.example.compas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {

    var manager: SensorManager? = null
    var currentDegree: Int = 0
    lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        manager?.registerListener(this, manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        manager?.unregisterListener(this)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        val degree = p0?.values?.get(0)?.toInt()!!
        b.tvDegree.text = degree.toString()
        val rotationAnim = RotateAnimation(currentDegree.toFloat(),(-degree).toFloat(), Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotationAnim.duration = 210
        rotationAnim.fillAfter = true
        currentDegree = -degree
        b.imgDinamic.startAnimation(rotationAnim)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}