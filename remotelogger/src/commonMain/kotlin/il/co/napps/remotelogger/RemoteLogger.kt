package il.co.napps.remotelogger

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.stringify

private const val TAG = "RemoteLogger"

@ImplicitReflectionSerializer
object RemoteLogger {

    @UnstableDefault
    @ImplicitReflectionSerializer
    private val restService = RestServiceImpl(RestProvider())

    @UnstableDefault
    fun sendMessage(url: String, message: Map<String, Any>) {
        logd(TAG, "sendMessage() called with: url = [$url], message = [$message]")
        GlobalScope.launch(dispatcher()) {
            val jsonElements = mutableMapOf<String, JsonElement>()
            for (entry in message.entries) {
                when (entry.value) {
                    is String -> {
                        jsonElements[entry.key] = JsonPrimitive(entry.value as String)
                    }
                    is Number -> {
                        jsonElements[entry.key] = JsonPrimitive(entry.value as Number)
                    }
                    is Boolean -> {
                        jsonElements[entry.key] = JsonPrimitive(entry.value as Boolean)
                    }
                    else -> {
                        loge(TAG, "Data of type ${entry.value::class} is not supported")
                    }
                }
            }
            val str = JsonObject(jsonElements)
            restService.sendJsonStringMessage(url, str.toString())
        }
    }
}