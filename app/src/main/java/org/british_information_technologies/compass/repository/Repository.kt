package org.british_information_technologies.compass.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

abstract class Repository(
	protected val repositoryScope: CoroutineScope = GlobalScope
)