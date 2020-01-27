package il.co.napps.remotelogger

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import org.jetbrains.kotlinconf.common.BuildConfig

private const val TAG = "RestProvider"

actual class RestProvider {
    actual fun getClient(): HttpClient {
        return HttpClient(Android) {
//            engine {
//                connectTimeout = connectTimeoutMilli
//                socketTimeout = readTimeoutMilli
//            }
            install(JsonFeature)
//            if (enableLogging) {
                install(Logging) {
                    logger = RestLogger()
                    level = if (BuildConfig.DEBUG) LogLevel.BODY else LogLevel.NONE
                }
//            }
        }
    }
}