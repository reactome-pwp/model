package org.reactome.web.pwp.model.client.content;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.http.client.Request;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import org.reactome.web.pwp.model.client.classes.*;
import org.reactome.web.pwp.model.client.common.ContentClientAbstract;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.client.util.Ancestors;
import org.reactome.web.pwp.model.client.util.StringUtils;

import java.util.*;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedReturnValue")
public abstract class ContentClient extends ContentClientAbstract {

    public static <T extends DatabaseObject> Request query(Long dbId, ContentClientHandler.ObjectLoaded<T> handler) {
        return query(dbId + "", handler);
    }

    public static <T extends DatabaseObject> Request query(String identifier, ContentClientHandler.ObjectLoaded<T> handler) {
        final DatabaseObject object = DatabaseObjectFactory.cache.get(identifier);
        if (object != null) {
            object.load(handler);
            return null;
        } else {
            return request("data/query/enhanced/" + identifier , handler, body -> {
                JSONObject json = JSONParser.parseStrict(body).isObject();
                T databaseObject = getDatabaseObject(json);
                handler.onObjectLoaded(databaseObject);
            });
        }
    }

    public static Request query(String identifier, String attribute, ContentClientHandler.AttributesLoaded handler) {
        return request("data/query/" + identifier + "/" + attribute, Accept.TEXT_PLAIN, handler, body -> {
            List<String[]> attributes = new ArrayList<>();
            String[] lines = body.split("\n");
            for (String line : lines) attributes.add(line.split("\t"));
            handler.onAttributesLoaded(attributes);
        });
    }

    public static Request query(Collection<?> identifiers, ContentClientHandler.ObjectMapLoaded handler) {
        //In case the call is made without elements to query
        if (identifiers == null || identifiers.isEmpty()) {
            Scheduler.get().scheduleDeferred(() -> handler.onObjectMapLoaded(new HashMap<>()));
            return null;
        } else {
            return request("data/query/ids/map", StringUtils.join(identifiers, ","), handler, body -> {
                JSONObject object = JSONParser.parseStrict(body).isObject();
                Map<String, DatabaseObject> map = getDatabaseObjectMap(object);
                handler.onObjectMapLoaded(map);
            });
        }
    }

    @SuppressWarnings("unused")
    public static Request getAncestors(Event event, ContentClientHandler.AncestorsLoaded handler) {
        return getAncestors(event.getDbId() + "", handler);
    }

    public static Request getAncestors(String event, ContentClientHandler.AncestorsLoaded handler) {
        return request("data/event/" + event + "/ancestors", handler, body -> {
            JSONArray aux = JSONParser.parseStrict(body).isArray();
            Ancestors ancestors = new Ancestors(aux);
            handler.onAncestorsLoaded(ancestors);
        });
    }

    public static <T extends DatabaseObject> void getOrthologous(Object databaseObject, Object species, ContentClientHandler.ObjectLoaded<T> handler) {
        String dbId = databaseObject instanceof DatabaseObject ? ((DatabaseObject) databaseObject).getDbId() + "" : databaseObject.toString();
        Long speciesId = species instanceof Species ? ((Species) species).getDbId() : (Long) species;
        request("data/orthology/" + dbId + "/species/" + speciesId, handler, body -> {
            JSONObject json = JSONParser.parseStrict(body).isObject();
            T rtn = getDatabaseObject(json);
            handler.onObjectLoaded(rtn);
        });
    }

    public static void getOrthologousMap(List<?> identifiers, Object species, ContentClientHandler.ObjectMapLoaded handler) {
        Long speciesId = species instanceof Species ? ((Species) species).getDbId() : (Long) species;
        request("data/orthologies/ids/species/" + speciesId, StringUtils.join(identifiers, ","), handler, body -> {
            JSONObject object = JSONParser.parseStrict(body).isObject();
            Map<String, ? extends DatabaseObject> map = getDatabaseObjectMap(object);
            handler.onObjectMapLoaded(map);
        });
    }

    @SuppressWarnings("unused")
    public static void loadPersonPublications(Person person, ContentClientHandler.ObjectLoaded<Person> handler) {
        request("data/person/" + person.getReactomeIdentifier() + "/publications", handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            person.setPublications(getDatabaseObjectList(list));
            handler.onObjectLoaded(person);
        });
    }

    //TODO: Check whether this is needed or it could be done differently with the new ContentService
    public static void getPersonPublications(String id, ContentClientHandler.ObjectListLoaded<Publication> handler) {
        request("data/person/" + id + "/publications", handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<Publication> publications = getDatabaseObjectList(list);
            handler.onObjectListLoaded(publications);
        });
    }

    public static void getReferenceSequences(DatabaseObject databaseObject, ContentClientHandler.ObjectListLoaded<ReferenceEntity> handler) {
        if (databaseObject instanceof Event || databaseObject instanceof PhysicalEntity) {
            request("data/participants/" + databaseObject.getReactomeIdentifier() + "/referenceEntities", handler, body -> {
                JSONArray list = JSONParser.parseStrict(body).isArray();
                List<ReferenceEntity> referenceEntities = getDatabaseObjectList(list);
                handler.onObjectListLoaded(referenceEntities);
            });
        } else {
            handler.onContentClientException(ContentClientHandler.Type.WRONG_REQUEST, "The object is not an Event nor PhysicalEntity");
        }
    }

    public static void getSpeciesList(ContentClientHandler.ObjectListLoaded<Species> handler) {
        request("data/species/main", handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<Species> speciesList = getDatabaseObjectList(list);
            handler.onObjectListLoaded(speciesList);
        });
    }

    public static void getTopLevelPathways(String species, ContentClientHandler.ObjectListLoaded<TopLevelPathway> handler) {
        request("data/pathways/top/" + species.replaceAll("[ _]+", "+"), handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<TopLevelPathway> tpls = getDatabaseObjectList(list);
            handler.onObjectListLoaded(tpls);
        });
    }

    public static void getPathwaysWithDiagramForEntity(PhysicalEntity pe, boolean allForms, String species, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        getPathwaysWithDiagramForEntity(pe.getReactomeIdentifier(), allForms, species, handler);
    }

    public static void getPathwaysWithDiagramForEntity(PhysicalEntity pe, boolean allForms, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        getPathwaysWithDiagramForEntity(pe.getReactomeIdentifier(), allForms, null, handler);
    }

    public static void getPathwaysWithDiagramForEntity(String pe, boolean allForms, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        getPathwaysWithDiagramForEntity(pe, allForms, null, handler);
    }

    public static void getPathwaysWithDiagramForEntity(String peId, boolean allForms, String species, ContentClientHandler.ObjectListLoaded<Pathway> handler) {
        String speciesRequest = (species == null || species.isEmpty()) ? "" : "?species=" + species;
        request("data/pathways/low/diagram/entity/" + peId + (allForms ? "/allForms" : "") + speciesRequest, handler, body -> {
            JSONArray list = JSONParser.parseStrict(body).isArray();
            List<Pathway> pathways = getDatabaseObjectList(list);
            handler.onObjectListLoaded(pathways);
        });
    }

    private static DBInfo dbInfo = null;

    public static void getDatabaseInformation(ContentClientHandler.DatabaseInfo handler) {
        if (dbInfo != null) {
            handler.onDatabaseInfoLoaded(dbInfo);
        } else {
            request("data/database/info", handler, body -> {
                JSONObject json = JSONParser.parseStrict(body).isObject();
                dbInfo = new DBInfo(json);
                handler.onDatabaseInfoLoaded(dbInfo);
            });
        }
    }
}