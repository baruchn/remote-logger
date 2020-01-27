package il.co.napps.remotelogger

actual class MessageProvider {
    actual fun getMessage(): String {
        return "Hello Android"
    }
}