package org.reactome.web.pwp.model.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class RegulationType extends DatabaseObject {
    private String value;

    public RegulationType() {
        super(SchemaClass.REGULATION_TYPE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        if (jsonObject.containsKey("value")) {
            this.value = DatabaseObjectUtils.getStringValue(jsonObject, "value");
        }
    }

    public String getValue() {
        return value;
    }
}
