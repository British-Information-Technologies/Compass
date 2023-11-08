package org.british_information_technologies.compass.signal

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

const val ALPHA = 0.05f // if ALPHA = 1 OR 0, no filter applies.

fun lowPass(input: Float, previous: Float): Float = previous + ALPHA * (input - previous)
fun lowPass(input: List<Float>, previous: List<Float>): List<Float> = previous.zip(input) { p,i ->
	p + ALPHA * (i - p)
}

fun Flow<Float>.lowPassFilter(): Flow<Float> = flow {
	var previous = 0.0f

	this@lowPassFilter.collect {
		previous = lowPass(input = it, previous = previous)
		this.emit(previous)
	}
}

fun Flow<List<Float>>.lowPassVectorFilter(): Flow<List<Float>> = flow {
	var previous = FloatArray(3) {0.0f}.toList()

	this@lowPassVectorFilter.collect {
		previous = lowPass(input = it, previous = previous)
		this.emit(previous)
	}
}

