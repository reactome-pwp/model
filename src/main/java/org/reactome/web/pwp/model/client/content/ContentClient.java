package org.reactome.web.pwp.model.client.content;

import org.reactome.web.pwp.model.client.classes.Pathway;
import org.reactome.web.pwp.model.client.classes.Person;
import org.reactome.web.pwp.model.client.classes.Publication;
import org.reactome.web.pwp.model.client.classes.Species;
import org.reactome.web.pwp.model.client.common.ContentClientAbstract;
import org.reactome.web.pwp.model.client.common.ContentClientHandler;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public abstract class ContentClient extends ContentClientAbstract {

    //TODO: getAncestors

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
