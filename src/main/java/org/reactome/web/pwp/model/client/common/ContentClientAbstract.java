package org.reactome.web.pwp.model.client.common;

import com.google.gwt.http.client.*;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.content.ContentClientException;
import org.reactome.web.pwp.model.client.content.ContentClientFactory;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * These are the common methods for the ContentClient implementation
 *
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("WeakerAccess")
public abstract class ContentClientAbstract {

    public static String SERVER = "";

    @SuppressWarnings("FieldCanBeLocal")
    public static String CONTENT_SERVICE = "/ContentService/";

    private static final String SERVICE_ERROR_MESSAGE = "There are problems connecting to the service. Please try in a short while.";

    protected enum MimeType {
        APPLICATION_JSON("application/json"),
        TEXT_PLAIN("text/plain");

        final String format;

        MimeType(String format) {
            this.format = format;
        }
    }

    public interface ResponseHandler {
        void on200(String body);
    }

    public static Request request(String url, ContentClientHandler handler, ResponseHandler responseHandler) {
        return request(url, null, MimeType.TEXT_PLAIN, MimeType.APPLICATION_JSON, handler, responseHandler);
    }


    public static Request request(String url, MimeType accept, ContentClientHandler handler, ResponseHandler responseHandler) {
        return request(url, null, MimeType.TEXT_PLAIN, accept, handler, responseHandler);
    }

    public static Request request(String method, String post, ContentClientHandler handler, ResponseHandler responseHandler) {
        return request(method, post, MimeType.TEXT_PLAIN, MimeType.APPLICATION_JSON, handler, responseHandler);
    }

    public static Request request(String method, String post, MimeType contentType, MimeType accept, ContentClientHandler handler, ResponseHandler responseHandler) {
        String url = SERVER + CONTENT_SERVICE + method;
        RequestBuilder requestBuilder = new RequestBuilder(post == null ? RequestBuilder.GET : RequestBuilder.POST, url);
        try {
            requestBuilder.setHeader("Content-Type", contentType.format);
            requestBuilder.setHeader("Accept", accept.format);
            return requestBuilder.sendRequest(post, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    int sc = response.getStatusCode();
                    if (sc == Response.SC_OK) {
                        responseHandler.on200(response.getText());
                    } else {
                        processError(handler, sc, response.getText());
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    handler.onContentClientException(ContentClientHandler.Type.REQUEST_TIMEOUT, SERVICE_ERROR_MESSAGE);
                }
            });
        } catch (RequestException ex) {
            handler.onContentClientException(ContentClientHandler.Type.CONNECTION_ERROR, SERVICE_ERROR_MESSAGE);
        }
        return null;
    }

    static void processError(ContentClientHandler handler, int rc, String json) {
        try {
            handler.onContentClientError(ContentClientFactory.getContentClientError(json));
        } catch (ContentClientException e) {
            String msg;
            ContentClientHandler.Type type;
            switch (rc) {
                case Response.SC_BAD_GATEWAY:
                case Response.SC_INTERNAL_SERVER_ERROR:
                    type = ContentClientHandler.Type.CONNECTION_ERROR;
                    msg = SERVICE_ERROR_MESSAGE;
                    break;
                default:
                    type = ContentClientHandler.Type.WRONG_RESPONSE_FORMAT;
                    msg = json;

            }
            handler.onContentClientException(type, msg);
        }
    }

    protected static <T extends DatabaseObject> T getDatabaseObject(JSONObject json) {
        DatabaseObjectFactory.cmds.clear();
        @SuppressWarnings("unchecked")
        T databaseObject = (T) DatabaseObjectFactory.create(json);
        DatabaseObjectFactory.fillUpObjectRefs();
        return databaseObject;
    }

    protected static <T extends DatabaseObject> List<T> getDatabaseObjectList(JSONArray list) {
        if (list != null) {
            DatabaseObjectFactory.cmds.clear();
            List<T> rtn = new LinkedList<>();
            for (int i = 0; i < list.size(); ++i) {
                JSONObject object = list.get(i).isObject();
                //noinspection unchecked
                rtn.add((T) DatabaseObjectFactory.create(object));
            }
            DatabaseObjectFactory.fillUpObjectRefs();
            return rtn;
        }
        return new LinkedList<>();
    }

    protected static Map<String, DatabaseObject> getDatabaseObjectMap(JSONObject object) {
        Map<String, DatabaseObject> map = new HashMap<>();
        DatabaseObjectFactory.cmds.clear();
        for (String key : object.keySet()) {
            DatabaseObject dbObject = DatabaseObjectFactory.create(object.get(key).isObject());
            map.put(key, dbObject);
        }
        DatabaseObjectFactory.fillUpObjectRefs();
        return map;
    }
}
