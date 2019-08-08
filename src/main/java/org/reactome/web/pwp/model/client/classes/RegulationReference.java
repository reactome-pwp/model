package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Guilherme Viteri (gviteri@ebi.ac.uk)
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
public class RegulationReference extends ControlReference {

    private Regulation regulatedBy;

    public RegulationReference() {
        super(SchemaClass.REGULATION_REFERENCE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        regulatedBy = DatabaseObjectUtils.getDatabaseObject(jsonObject, "regulatedBy");

    }

    public Regulation getRegulatedBy() {
        return regulatedBy;
    }
}
