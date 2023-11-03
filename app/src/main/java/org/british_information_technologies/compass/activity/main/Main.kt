package org.british_information_technologies.compass.activity.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@Composable
fun Main(vm: MainViewModel) {

	val theme = MaterialTheme.colorScheme

	val heading by vm.heading.observeAsState(initial = 0.0f)

	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
			Canvas(modifier = Modifier.size(500.dp)) {
				drawCircle(
					Color.Black, 500.dp.value
				)
				drawCircle(
					theme.surface, 450.dp.value
				)
				rotate(heading-90.0f) {
					drawArc(color = theme.error, -1.0f, 2.0f, false, style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round))
				}
			}
		}
	}
}