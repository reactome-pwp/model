package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Species extends Taxon {

    private String abbreviation;

    public Species() {
        super(SchemaClass.SPECIES);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.abbreviation = DatabaseObjectUtils.getStringValue(jsonObject, "abbreviation");
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
