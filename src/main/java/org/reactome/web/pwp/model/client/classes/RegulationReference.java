package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Guilherme Viteri (gviteri@ebi.ac.uk)
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public class RegulationReference extends ControlReference {

    private Regulation regulation;

    public RegulationReference() {
        super(SchemaClass.REGULATION_REFERENCE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        regulation = DatabaseObjectUtils.getDatabaseObject(jsonObject, "regulation");

    }

    public Regulation getRegulation() {
        return regulation;
    }
}
