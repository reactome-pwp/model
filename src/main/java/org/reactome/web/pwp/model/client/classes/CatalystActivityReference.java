package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Guilherme Viteri (gviteri@ebi.ac.uk)
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public class CatalystActivityReference extends ControlReference {

    public CatalystActivityReference() {
        super(SchemaClass.CATALYST_ACTIVITY_REFERENCE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }
}
