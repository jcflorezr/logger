package net.learningpath.logger.model.dto

/**
 * See Java versions of this classes
 * @see LogReport
 * @see DetailedError
 */
interface ResponseKT

class SuccessResponseKT : ResponseKT {
    val success = true
    val message = "Message logged successfully."
}

class ErrorResponseKT(cause: Throwable) : ResponseKT {
    val success = false
    val message = cause.message
    val errorType = cause.javaClass.name
    val errorDetails = DetailedError(cause)
}

class DetailedErrorKT(cause: Throwable) {
    val errorCauseType = cause.javaClass.name
    val errorCauseMessage = cause.localizedMessage
    val stackTrace = getTrace(cause)

    private fun getTrace(cause: Throwable) : List<TraceElement> {
        return cause.stackTrace.asSequence()
                .map { element -> TraceElement(element) }
                .toList()
    }

    data class TraceElement(val traceElement: StackTraceElement) {
        val className = traceElement.className
        val methodName = traceElement.methodName
        val lineNumber = traceElement.lineNumber
        val isNativeMethod = traceElement.isNativeMethod
    }

}
