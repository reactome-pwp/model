package org.reactome.web.pwp.model.client.common;


import org.reactome.web.pwp.model.client.classes.DBInfo;
import org.reactome.web.pwp.model.client.classes.DatabaseObject;
import org.reactome.web.pwp.model.client.content.ContentClientError;
import org.reactome.web.pwp.model.client.util.Ancestors;

import java.util.List;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface ContentClientHandler {

    enum Type {WRONG_REQUEST, CONNECTION_ERROR, REQUEST_TIMEOUT, WRONG_RESPONSE_FORMAT}

    void onContentClientException(Type type, String message);
    void onContentClientError(ContentClientError error);

    interface ObjectLoaded<T extends DatabaseObject> extends ContentClientHandler {
        void onObjectLoaded(T databaseObject);
    }

    interface AttributesLoaded extends ContentClientHandler {
        void onAttributesLoaded(List<String[]> attributes);
    }

    interface ObjectListLoaded<T extends DatabaseObject> extends ContentClientHandler {
        void onObjectListLoaded(List<T> list);
    }

    interface ObjectMapLoaded extends ContentClientHandler {
        void onObjectMapLoaded(Map<String, ? extends DatabaseObject> map);
    }

    interface AncestorsLoaded extends ContentClientHandler {
        void onAncestorsLoaded(Ancestors ancestors);
    }

    interface DatabaseInfo extends ContentClientHandler {
        void onDatabaseInfoLoaded(DBInfo dbInfo);
    }
}
