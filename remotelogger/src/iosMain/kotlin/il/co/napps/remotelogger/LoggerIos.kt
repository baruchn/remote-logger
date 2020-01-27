package il.co.napps.remotelogger

import platform.Foundation.NSLog

actual fun logd(tag: String, message: String) {
    NSLog("D: ${createMessage(tag, message)}")
}

actual fun logi(tag: String, message: String) {
    NSLog("I: ${createMessage(tag, message)}")
}

actual fun logw(tag: String, message: String) {
    NSLog("W: ${createMessage(tag, message)}")
}

actual fun loge(tag: String, message: String) {
    NSLog("E: ${createMessage(tag, message)}")
}

private fun createMessage(tag: String, message: String): String {
    return "$tag: $message"
}