package org.british_information_technologies.compass

import android.content.Context
import android.hardware.SensorManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppProvider {

	@Provides
	@Singleton
	fun provideSensorManager(
		@ApplicationContext context: Context,
	): SensorManager {
		return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
	}
}