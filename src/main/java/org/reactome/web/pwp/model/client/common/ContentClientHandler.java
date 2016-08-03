package org.reactome.web.pwp.model.client.common;


import org.reactome.web.pwp.model.client.classes.*;
import org.reactome.web.pwp.model.client.content.ContentClientError;

import java.util.List;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface ContentClientHandler {

    enum Type {CONNECTION_ERROR, REQUEST_TIMEOUT, WRONG_RESPONSE_FORMAT}


    void onContentClientException(Type type, String message);
    void onContentClientError(ContentClientError error);

    interface ObjectCreated extends ContentClientHandler {
        void onObjectCreated(DatabaseObject databaseObject);
    }

    interface ObjectLoaded extends ContentClientHandler {
        void onObjectLoaded(DatabaseObject databaseObject);
    }

    interface ObjectListLoaded extends ContentClientHandler {
        void onObjectListLoaded(Map<String, DatabaseObject> databaseObjects);
    }

    interface PersonHandler extends ContentClientHandler {
        void onLiteratureReferencesLoaded(Person person);
    }

    interface Publications extends ContentClientHandler {
        void onPublicationsLoaded(List<Publication> publications);
    }

    interface SpeciesList extends ContentClientHandler {
        void onSpeciesLoaded(List<Species> species);
    }

    interface TopLevelPathways extends ContentClientHandler {
        void onTopLevelPathwaysLoaded(List<Pathway> tpls);
    }

    interface DatabaseName extends ContentClientHandler {
        void onDatabaseNameLoaded(String name);
    }

    interface Version extends ContentClientHandler {
        void onVersionLoaded(String version);
    }
}
