package il.co.napps.remotelogger

import android.util.Log

actual fun logd(tag: String, message: String) {
    Log.d(tag, message)
}

actual fun logi(tag: String, message: String) {
    Log.i(tag, message)
}

actual fun logw(tag: String, message: String) {
    Log.w(tag, message)
}

actual fun loge(tag: String, message: String) {
    Log.e(tag, message)
}