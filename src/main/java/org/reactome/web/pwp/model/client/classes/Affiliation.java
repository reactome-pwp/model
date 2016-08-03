package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("unused")
public class Affiliation extends DatabaseObject {

    private String address;
    private String name;

    public Affiliation() {
        super(SchemaClass.AFFILIATION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.address = DatabaseObjectUtils.getStringValue(jsonObject, "address");

        this.name = DatabaseObjectUtils.getStringValue(jsonObject, "name");
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
