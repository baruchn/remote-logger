package il.co.napps.remotelogger

import io.ktor.client.HttpClient

expect class RestProvider() {
    fun getClient(): HttpClient
}