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
                T databaseObject = getDatabaseObject(json);
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
                JSONObject object = JSONParser.parseStrict(body).isObject();
                Map<String, DatabaseObject> map = getDatabaseObjectMap(object);
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

    public static <T extends DatabaseObject> void getOrthologous(Object databaseObject, Object species, ContentClientHandler.ObjectLoaded<T> handler) {
        String dbId = databaseObject instanceof DatabaseObject ? ((DatabaseObject) databaseObject).getDbId() + "" : databaseObject.toString();
        Long speciesId = species instanceof Species ? ((Species) species).getDbId() : (Long) species;
        request("/data/orthology/" + dbId + "/species/" + speciesId, handler, body -> {
            JSONObject json = JSONParser.parseStrict(body).isObject();
            T rtn = getDatabaseObject(json);
            handler.onObjectLoaded(rtn);
        });
    }

    public static void getOrthologousMap(List<?> objs, Object species, ContentClientHandler.ObjectMapLoaded handler) {
        Long speciesId = species instanceof Species ? ((Species) species).getDbId() : (Long) species;
        request("/data/orthologies/ids/species/" + speciesId, StringUtils.join(objs, ","), handler, body -> {
            JSONObject object = JSONParser.parseStrict(body).isObject();
            Map<String, DatabaseObject> map = getDatabaseObjectMap(object);
            handler.onObjectMapLoaded(map);
        });
    }

    @SuppressWarnings("unused")
    public static void loadPersonPublications(Person person, ContentClientHandler.ObjectLoaded<Person> handler) {
        request("data/person/" + person.getReactomeIdentifier() + "/publications", handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<Publication> publications = getDatabaseObjectList(list);
            if (publications != null) {
                person.setPublications(publications);
                handler.onObjectLoaded(person);
            } else handler.onContentClientException(ContentClientHandler.Type.RESPONSE_EMPTY_LIST, body);

        });
    }

    //TODO: Check whether this is needed or it could be done differently with the new ContentService
    public static void getPersonPublications(String id, ContentClientHandler.ObjectListLoaded<Publication> handler) {
        request("data/person/" + id + "/publications", handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<Publication> publications = getDatabaseObjectList(list);
            if (publications != null) handler.onObjectListLoaded(publications);
            else handler.onContentClientException(ContentClientHandler.Type.RESPONSE_EMPTY_LIST, body);
        });
    }

    public static void getSpeciesList(ContentClientHandler.ObjectListLoaded<Species> handler) {
        request("data/species/main", handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<Species> speciesList = getDatabaseObjectList(list);
            if (speciesList != null) handler.onObjectListLoaded(speciesList);
            else handler.onContentClientException(ContentClientHandler.Type.RESPONSE_EMPTY_LIST, body);
        });
    }

    public static void getTopLevelPathways(String species, ContentClientHandler.ObjectListLoaded<TopLevelPathway> handler) {
        request("data/pathways/top/" + species, handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<TopLevelPathway> tpls = getDatabaseObjectList(list);
            if (tpls != null) handler.onObjectListLoaded(tpls);
            else handler.onContentClientException(ContentClientHandler.Type.RESPONSE_EMPTY_LIST, body);
        });
    }

    @SuppressWarnings("unused")
    public static void getPathwaysWithDiagramForEntity(PhysicalEntity pe, boolean allForms, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        getPathwaysWithDiagramForEntity(pe.getDbId(), allForms, handler);
    }

    public static void getPathwaysWithDiagramForEntity(Object peId, boolean allForms, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        request("data/pathways/low/diagram/entity/" + peId + (allForms ? "/allForms" : ""), handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<Pathway> pathways = getDatabaseObjectList(list);
            if (pathways != null) handler.onObjectListLoaded(pathways);
            else handler.onContentClientException(ContentClientHandler.Type.RESPONSE_EMPTY_LIST, body);
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