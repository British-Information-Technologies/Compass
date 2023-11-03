package org.british_information_technologies.compass.repository

import android.util.Log
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.atan2
import kotlin.math.sqrt

@Singleton
class HeadingRepository
@Inject constructor(
	 accelerometerRepository: AccelerometerRepository,
	 magnetometerRepository: MagnetometerRepository,
): Repository() {

	private val magneticAccelerometerProducts = accelerometerRepository.vector.combine(magnetometerRepository.vector) { acc, mag ->
		val (Ax,Ay,Az) = acc
		val (Ex,Ey,Ez) = mag

		//cross product of the magnetic field vector and the gravity vector
		val Hx = Ey * Az - Ez * Ay
		val Hy = Ez * Ax - Ex * Az
		val Hz = Ex * Ay - Ey * Ax

		listOf(Hx, Hy, Hz)
	}

	private val magneticNormalisedProducts = magneticAccelerometerProducts.map {
		var Hx = it[0]
		var Hy = it[1]
		var Hz = it[2]

		var invH = 1.0f / Math.sqrt(
			(Hx * Hx + Hy * Hy + Hz * Hz).toDouble()
		).toFloat()

		if (invH.isInfinite()) { invH = 0.0f }

		Log.d(this::class.java.simpleName, "")

		Hx *= invH
		Hy *= invH
		Hz *= invH

		Log.d(this::class.java.simpleName, "$Hx, $Hy, $Hz")

		listOf(Hx, Hy, Hz)
	}

	private val accelerometerNormalised = accelerometerRepository.vector.map { acc ->
		Log.d("TAG", ": sdfsdf")
		var (Ax,Ay,Az) = acc

		val sqrt = sqrt((Ax * Ax + Ay * Ay + Az * Az).toDouble()).toFloat()

		//normalize the values of gravity vector
		var invA = 1.0f / sqrt

		if (invA.isInfinite()) { invA = 0.0f}

		Ax *= invA
		Ay *= invA
		Az *= invA

		listOf(Ax, Ay, Az)
	}.onEach { Log.d(this::class.java.simpleName, "accelerometerNormalised", ) }

	init {
		repositoryScope.launch {
			accelerometerNormalised.collect {
				Log.d("TAG", "$it")
			}
		}
	}

	val radianHeading = accelerometerNormalised.combine(magneticNormalisedProducts) { acc, mag ->
		val (Ax,Ay,Az) = acc
		val (Hx, Hy, Hz) = mag



		//cross product of the gravity vector and the new vector H
		// val Mx: Float = Ay * Hz - Az * Hy
		val My: Float = Az * Hx - Ax * Hz
		// val Mz: Float = Ax * Hy - Ay * Hx

		//arctangent to obtain heading in radians
		atan2(Hy.toDouble(), My.toDouble()).toFloat()

	}

	val degreeHeading = radianHeading.map(this::convertRadtoDeg).map {-it}

	private fun convertRadtoDeg(rad: Float): Float {
		return (rad / Math.PI).toFloat() * 180
	}
}