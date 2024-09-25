package googl.sarafan.testbankcard.network.models

import java.io.IOException

sealed class NetworkException(
    val errorBody: String,
    val httpStatusCode: Int,
) : IOException(errorBody) {

    class BadRequest(errorMessage: String, httpStatusCode: Int) :
        NetworkException(errorMessage, httpStatusCode)

    class Unauthorized(errorMessage: String, httpStatusCode: Int) :
        NetworkException(errorMessage, httpStatusCode)

    class NotFound(errorMessage: String, httpStatusCode: Int) :
        NetworkException(errorMessage, httpStatusCode)

    class InternalServerError(errorMessage: String, httpStatusCode: Int) :
        NetworkException(errorMessage, httpStatusCode)

    class NoInternetConnection(
        errorMessage: String = NO_INTERNET_CONNECTION,
        httpStatusCode: Int = NO_INTERNET_CONNECTION_CODE
    ) : NetworkException(errorMessage, httpStatusCode)

    class TooMuchRequests(
        errorMessage: String = TOO_MUCH_REQUESTS,
        httpStatusCode: Int = UNDEFINED_CODE
    ): NetworkException(errorMessage, httpStatusCode)

    class Undefined(
        errorMessage: String = UNDEFINED_MESSAGE,
        httpStatusCode: Int = TOO_MUCH_REQUESTS_CODE
    ) : NetworkException(errorMessage, httpStatusCode)

    class ResponseBodyIsNull(
        errorMessage: String = RESPONSE_BODY_IS_NULL,
        httpStatusCode: Int
    ) : NetworkException(errorMessage, httpStatusCode)


    companion object {
        const val NO_INTERNET_CONNECTION = "No internet connection"
        const val RESPONSE_BODY_IS_NULL = "Response body is null"
        const val NO_INTERNET_CONNECTION_CODE = -1
        const val TOO_MUCH_REQUESTS_CODE = 429
        const val TOO_MUCH_REQUESTS = "Too much requests"
        const val UNDEFINED_MESSAGE = "Undefined"
        const val UNDEFINED_CODE = 0
    }

}