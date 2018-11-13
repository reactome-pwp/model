package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class DrugType extends ExternalOntology {

    private String definition;

    private List<String> name;

    public DrugType() {
        super(SchemaClass.DRUG_TYPE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.definition = DatabaseObjectUtils.getStringValue(jsonObject, "definition");

        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");
    }

    @Override
    public String getDefinition() {
        return definition;
    }

    @Override
    public List<String> getName() {
        return name;
    }
}
