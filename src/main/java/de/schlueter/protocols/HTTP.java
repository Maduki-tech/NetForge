package de.schlueter.protocols;

import de.schlueter.data.ContentType;
import de.schlueter.data.ResponseBuilder;

/**
 * HTTP
 */
public class HTTP {
    public HTTP() {
    }

    public String handleGet(String path) {
        return ResponseBuilder.buildResponse("{\"message\": \"Hello World\"}", "200 OK", ContentType.JSON);
    }

    public String handlePost(String path) {
        return ResponseBuilder.buildResponse("Hello World", "200 OK", ContentType.JSON);
    }
}
