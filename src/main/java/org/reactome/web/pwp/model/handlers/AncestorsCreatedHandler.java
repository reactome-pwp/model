package org.reactome.web.pwp.model.handlers;

import org.reactome.web.pwp.model.util.Ancestors;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public interface AncestorsCreatedHandler {
    void onAncestorsLoaded(Ancestors ancestors);
    void onAncestorsError(Throwable exception);
}
