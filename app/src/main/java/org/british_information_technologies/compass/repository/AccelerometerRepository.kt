package org.british_information_technologies.compass.repository

import android.hardware.Sensor
import android.hardware.SensorManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccelerometerRepository @Inject constructor(
	private val sensorManager: SensorManager,
): SensorXYZRepository(sensorManager, Sensor.TYPE_ACCELEROMETER)