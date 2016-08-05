package org.reactome.web.pwp.model.client.content;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import org.reactome.web.pwp.model.client.classes.*;
import org.reactome.web.pwp.model.client.common.ContentClientAbstract;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.client.util.Ancestors;
import org.reactome.web.pwp.model.client.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public abstract class ContentClient extends ContentClientAbstract {

    public static void query(Long dbId, ContentClientHandler.ObjectReady handler) {
        query(dbId + "", handler);
    }

    public static void query(String identifier, ContentClientHandler.ObjectReady handler) {
        final DatabaseObject object = DatabaseObjectFactory.cache.get(identifier);
        if (object != null) {
            object.load(handler);
        } else {
            request("data/query/" + identifier + "/more", handler, body -> {
                JSONObject json = JSONParser.parseStrict(body).isObject();
                DatabaseObjectFactory.cmds.clear();
                DatabaseObject databaseObject = DatabaseObjectFactory.create(json);
                DatabaseObjectFactory.fillUpObjectRefs();
                handler.onObjectReady(databaseObject);
            });
        }
    }

    public static void query(Collection<?> identifiers, ContentClientHandler.ObjectListLoaded handler) {
        //In case the call is made without elements to query
        if (identifiers == null || identifiers.isEmpty()) {
            Scheduler.get().scheduleDeferred(() -> handler.onObjectListLoaded(new HashMap<>()));
        } else {
            request("data/query/ids/map", StringUtils.join(identifiers, ","), handler, body -> {
                Map<String, DatabaseObject> map = new HashMap<>();
                JSONObject object = JSONParser.parseStrict(body).isObject();
                DatabaseObjectFactory.cmds.clear();
                for (String key : object.keySet()) {
                    DatabaseObject dbObject = DatabaseObjectFactory.create(object.get(key).isObject());
                    map.put(key, dbObject);
                }
                DatabaseObjectFactory.fillUpObjectRefs();
                handler.onObjectListLoaded(map);
            });
        }
    }

    @SuppressWarnings("unused")
    public static void getAncestors(Event event, ContentClientHandler.AncestorsLoaded handler) {
        getAncestors(event.getDbId() + "", handler);
    }

    public static void getAncestors(String event, ContentClientHandler.AncestorsLoaded handler) {
        request("data/event/" + event + "/ancestors", handler, body -> {
            JSONArray aux = JSONParser.parseStrict(body).isArray();
            Ancestors ancestors = new Ancestors(aux);
            handler.onAncestorsLoaded(ancestors);
        });
    }

    //TODO: loadPathwaysWithDiagramForEntity

    //TODO: Check whether this is needed or it could be done differently with the new ContentService
    public static void loadPersonPublications(Person person, ContentClientHandler.PersonHandler handler) {
        request("data/person/" + person.getIdentifier() + "/publications", handler, body -> {
            List<Publication> publications = getDatabaseObjectListOf(body, handler);
            if (publications != null){
                person.setPublications(publications);
                handler.onLiteratureReferencesLoaded(person);
            }
        });
    }

    //TODO: Check whether this is needed or it could be done differently with the new ContentService
    public static void getPersonPublications(String id, ContentClientHandler.Publications handler) {
        request("data/person/" + id + "/publications", handler, body -> {
            List<Publication> publications = getDatabaseObjectListOf(body, handler);
            if (publications != null) handler.onPublicationsLoaded(publications);
        });
    }

    public static void getSpeciesList(ContentClientHandler.SpeciesList handler){
        request("data/species/main", handler, body -> {
            List<Species> speciesList = getDatabaseObjectListOf(body, handler);
            if(speciesList != null) handler.onSpeciesLoaded(speciesList);
        });
    }

    public static void getTopLevelPathways(String species, ContentClientHandler.TopLevelPathways handler) {
        request("data/pathways/top/" + species, handler, body -> {
            List<Pathway> tpls = getDatabaseObjectListOf(body, handler);
            if (tpls != null) handler.onTopLevelPathwaysLoaded(tpls);
        });
    }

    private static String dbName = null;
    public static void getDatabaseName(ContentClientHandler.DatabaseName handler) {
        if (dbName != null) handler.onDatabaseNameLoaded(dbName);
        request("data/database/name", handler, body -> handler.onDatabaseNameLoaded(dbName = body));
    }

    private static String version = null;
    public static void getDatabaseVersion(ContentClientHandler.Version handler) {
        if (version != null) handler.onVersionLoaded(version);
        else request("data/database/version", handler, body -> handler.onVersionLoaded(version = body));
    }

}
