// network/WebSocketManager.kt
import okhttp3.*
import okio.ByteString

class WebSocketManager {

    private val client = OkHttpClient()
    private var webSocket: WebSocket? = null

    fun startWebSocket() {
        val request = Request.Builder().url("wss://api.example.com/realtime").build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                // Handle on open
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // Handle incoming messages
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // Handle binary messages
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, null)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                // Handle failure
            }
        })
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun closeWebSocket() {
        webSocket?.close(1000, "Closing")
    }
}
