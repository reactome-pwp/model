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

    public static <T extends DatabaseObject> void query(Long dbId, ContentClientHandler.ObjectLoaded<T> handler) {
        query(dbId + "", handler);
    }

    public static <T extends DatabaseObject> void query(String identifier, ContentClientHandler.ObjectLoaded<T> handler) {
        final DatabaseObject object = DatabaseObjectFactory.cache.get(identifier);
        if (object != null) {
            object.load(handler);
        } else {
            request("data/query/" + identifier + "/more", handler, body -> {
                JSONObject json = JSONParser.parseStrict(body).isObject();
                DatabaseObjectFactory.cmds.clear();
                @SuppressWarnings("unchecked")
                T databaseObject = (T) DatabaseObjectFactory.create(json);
                DatabaseObjectFactory.fillUpObjectRefs();
                handler.onObjectLoaded(databaseObject);
            });
        }
    }

    public static void query(Collection<?> identifiers, ContentClientHandler.ObjectMapLoaded handler) {
        //In case the call is made without elements to query
        if (identifiers == null || identifiers.isEmpty()) {
            Scheduler.get().scheduleDeferred(() -> handler.onObjectMapLoaded(new HashMap<>()));
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
                handler.onObjectMapLoaded(map);
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

    @SuppressWarnings("unused")
    public static void loadPersonPublications(Person person, ContentClientHandler.ObjectLoaded<Person> handler) {
        request("data/person/" + person.getIdentifier() + "/publications", handler, body -> {
            List<Publication> publications = getDatabaseObjectListOf(body, handler);
            if (publications != null) {
                person.setPublications(publications);
                handler.onObjectLoaded(person);
            }
        });
    }

    //TODO: Check whether this is needed or it could be done differently with the new ContentService
    public static void getPersonPublications(String id, ContentClientHandler.ObjectListLoaded<Publication> handler) {
        request("data/person/" + id + "/publications", handler, body -> {
            List<Publication> publications = getDatabaseObjectListOf(body, handler);
            if (publications != null) handler.onObjectListLoaded(publications);
        });
    }

    public static void getSpeciesList(ContentClientHandler.ObjectListLoaded<Species> handler) {
        request("data/species/main", handler, body -> {
            List<Species> speciesList = getDatabaseObjectListOf(body, handler);
            if (speciesList != null) handler.onObjectListLoaded(speciesList);
        });
    }

    public static void getTopLevelPathways(String species, ContentClientHandler.ObjectListLoaded<TopLevelPathway> handler) {
        request("data/pathways/top/" + species, handler, body -> {
            List<TopLevelPathway> tpls = getDatabaseObjectListOf(body, handler);
            if (tpls != null) handler.onObjectListLoaded(tpls);
        });
    }

    @SuppressWarnings("unused")
    public static void getPathwaysWithDiagramForEntity(PhysicalEntity pe, boolean allForms, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        getPathwaysWithDiagramForEntity(pe.getDbId(), allForms, handler);
    }

    public static void getPathwaysWithDiagramForEntity(Object peId, boolean allForms, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        request("data/pathways/low/diagram/entity/" + peId + ( allForms ? "/allForms" : ""), handler, body -> {
            List<Pathway> pathways = getDatabaseObjectListOf(body, handler);
            if(pathways != null) handler.onObjectListLoaded(pathways);
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
