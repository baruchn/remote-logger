package il.co.napps.remotelogger

expect class MessageProvider() {
    fun getMessage(): String
}