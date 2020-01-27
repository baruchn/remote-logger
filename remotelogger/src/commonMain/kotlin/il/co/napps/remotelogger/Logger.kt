package il.co.napps.remotelogger

expect fun logd(tag: String, message: String)

expect fun logi(tag: String, message: String)

expect fun logw(tag: String, message: String)

expect fun loge(tag: String, message: String)