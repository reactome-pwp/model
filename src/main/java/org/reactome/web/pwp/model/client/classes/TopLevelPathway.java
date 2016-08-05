package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class TopLevelPathway extends Pathway {

    public TopLevelPathway() {
        super(SchemaClass.TOP_LEVEL_PATHWAY);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }

}
