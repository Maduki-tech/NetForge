package de.schlueter.data;

/**
 * ResponseBuilder
 */
public class ResponseBuilder {
    public ResponseBuilder() {
    }

    public static String buildResponse(String message, String status, ContentType contentType) {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("HTTP/1.1 " + status + "\r\n");
        responseBuilder.append(handleContentType(contentType));
        responseBuilder.append("Content-Length: " + message.length() + "\r\n");
        responseBuilder.append("\r\n");
        responseBuilder.append(message);

        return responseBuilder.toString();
    }

    private static String handleContentType(ContentType contentType) {
        switch (contentType) {
            case HTML:
                return "Content-Type: text/html\r\n";
            case JSON:
                return "Content-Type: application/json\r\n";
            case XML:
                return "Content-Type: application/xml\r\n";
            default:
                return "Content-Type: text/plain\r\n";
        }
    }

}
