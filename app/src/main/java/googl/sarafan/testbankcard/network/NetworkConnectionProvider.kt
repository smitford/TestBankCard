package googl.sarafan.testbankcard.network

interface NetworkConnectionProvider {
    fun isConnected(): Boolean
}