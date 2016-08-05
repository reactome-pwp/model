package org.reactome.web.pwp.model.client.common;


import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.content.ContentClientError;
import org.reactome.web.pwp.model.client.util.Ancestors;

import java.util.List;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface ContentClientHandler {

    enum Type {CONNECTION_ERROR, REQUEST_TIMEOUT, WRONG_RESPONSE_FORMAT, RESPONSE_EMPTY_LIST}

    void onContentClientException(Type type, String message);
    void onContentClientError(ContentClientError error);

    interface ObjectLoaded<T extends DatabaseObject> extends ContentClientHandler {
        void onObjectLoaded(T databaseObject);
    }

    interface ObjectListLoaded<T extends DatabaseObject> extends ContentClientHandler {
        void onObjectListLoaded(List<T> list);
    }

    interface ObjectMapLoaded extends ContentClientHandler {
        void onObjectMapLoaded(Map<String, DatabaseObject> map);
    }

    interface AncestorsLoaded extends ContentClientHandler {
        void onAncestorsLoaded(Ancestors ancestors);
    }

    interface DatabaseName extends ContentClientHandler {
        void onDatabaseNameLoaded(String name);
    }

    interface Version extends ContentClientHandler {
        void onVersionLoaded(String version);
    }
}
