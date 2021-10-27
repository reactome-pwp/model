package org.reactome.web.pwp.model.client.util;

import com.google.gwt.http.client.Response;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {
    private static final Map<Integer, String> codeToText = new HashMap<>();
    static {
        codeToText.put(Response.SC_NOT_FOUND, "Not Found");
        codeToText.put(Response.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        codeToText.put(Response.SC_BAD_REQUEST, "Bad request");
        codeToText.put(Response.SC_FORBIDDEN, "Forbidden");
        codeToText.put(Response.SC_BAD_GATEWAY, "Bad gateway");
        codeToText.put(Response.SC_GATEWAY_TIMEOUT, "Gateway timeout");
        codeToText.put(Response.SC_NOT_ACCEPTABLE, "Not acceptable");
        codeToText.put(Response.SC_NOT_IMPLEMENTED, "Not implemented");
    }
    // Use this method because servers response.getStatusText() return null on server
    public static String getStatusText(int statusCode) {
        return codeToText.get(statusCode);
    }
}
