package org.british_information_technologies.compass.activity.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.british_information_technologies.compass.repository.AccelerometerRepository
import org.british_information_technologies.compass.repository.HeadingRepository
import org.british_information_technologies.compass.repository.MagnetometerRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	@ApplicationContext context: Context,
 	val accelerometerRepository: AccelerometerRepository,
 	val magnetometerRepository: MagnetometerRepository,
	headingRepository: HeadingRepository,
): ViewModel() {
	val heading = headingRepository.degreeHeading.asLiveData()

	init {
		accelerometerRepository.registerListener()
		magnetometerRepository.registerListener()
	}

	override fun onCleared() {
		accelerometerRepository.unregisterListener()
		magnetometerRepository.unregisterListener()
	}
}
