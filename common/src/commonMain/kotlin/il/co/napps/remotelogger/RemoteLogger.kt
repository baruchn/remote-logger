package il.co.napps.remotelogger

object RemoteLogger {
    private val messageProvider = MessageProvider()

    fun getMessage(): String {
        return messageProvider.getMessage()
    }
}