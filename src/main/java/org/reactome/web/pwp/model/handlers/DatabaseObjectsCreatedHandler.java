package org.reactome.web.pwp.model.handlers;

import org.reactome.web.pwp.model.classes.DatabaseObject;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface DatabaseObjectsCreatedHandler {
    void onDatabaseObjectsLoaded(List<DatabaseObject> databaseObjects);
    void onDatabaseObjectError(Throwable exception);
}
