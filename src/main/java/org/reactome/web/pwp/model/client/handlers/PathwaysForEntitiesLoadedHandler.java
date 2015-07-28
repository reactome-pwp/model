package org.reactome.web.pwp.model.client.handlers;

import org.reactome.web.pwp.model.classes.Pathway;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface PathwaysForEntitiesLoadedHandler {
    void onPathwaysForEntitiesLoaded(List<Pathway> pathways);
    void onPathwaysForEntitiesError(Throwable throwable);
}
