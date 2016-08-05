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

    public interface ResponseHandler {
        void on200(String body);
    }

    public static void request(String url, ContentClientHandler handler, ResponseHandler responseHandler) {
        request(url, null, handler, responseHandler);
    }

    public static void request(String method, String post, ContentClientHandler handler, ResponseHandler responseHandler) {
        String url = SERVER + CONTENT_SERVICE + method;
        RequestBuilder requestBuilder = new RequestBuilder(post == null ? RequestBuilder.GET : RequestBuilder.POST, url);
        try {
            requestBuilder.sendRequest(post, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    switch (response.getStatusCode()) {
                        case Response.SC_OK:
                            responseHandler.on200(response.getText());
                            break;
                        default:
                            processError(handler, response.getText());
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    handler.onContentClientException(ContentClientHandler.Type.REQUEST_TIMEOUT, exception.getMessage());
                }
            });
        } catch (RequestException ex) {
            handler.onContentClientException(ContentClientHandler.Type.CONNECTION_ERROR, ex.getMessage());
        }
    }

    static void processError(ContentClientHandler handler, String json) {
        try {
            handler.onContentClientError(ContentClientFactory.getContentClientError(json));
        } catch (ContentClientException e) {
            handler.onContentClientException(ContentClientHandler.Type.WRONG_RESPONSE_FORMAT, json);
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
        return null;
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
