package il.co.napps.remotelogger

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Main