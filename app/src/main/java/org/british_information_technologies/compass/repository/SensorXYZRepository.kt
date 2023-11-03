package org.british_information_technologies.compass.repository

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.math.round

abstract class SensorXYZRepository constructor(
	private val sensorManager: SensorManager,
	private val sensorType: Int,
): Repository(), SensorEventListener {

	private val sensor: Sensor get() = sensorManager.getDefaultSensor(sensorType)!!

	private val _vector = MutableStateFlow(listOf(0.0f,0.0f,0.0f))

	var vector = _vector

	var x = _vector.map { it[0] }
	var y = _vector.map { it[1] }
	var z = _vector.map { it[2] }

	fun registerListener() {
		Log.d(this::class.java.simpleName, "registerListener")
		sensorManager.registerListener(
			this,
			sensor,
			SensorManager.SENSOR_DELAY_UI,
			SensorManager.SENSOR_DELAY_NORMAL
		)
	}

	fun unregisterListener() {
		Log.d(this::class.java.simpleName, "unregisterListener")
		sensorManager.unregisterListener(
			this,
		)
	}

	// MARK: - Sensor Update functions
	override fun onSensorChanged(event: SensorEvent?) {
		val values = event!!.values!!
		repositoryScope.launch {
			_vector.emit(values.toList().map { round(it * 100) / 100 })
			val a = values.toList()
			val b= a
		}
	}

	override fun onAccuracyChanged(sensor: Sensor?, value: Int) {
		Log.d(this::class.java.simpleName, "[onAccuracyChanged] sensor: $sensor, value: $value")
	}
}


