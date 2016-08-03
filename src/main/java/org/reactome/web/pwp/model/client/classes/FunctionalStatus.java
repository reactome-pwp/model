package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("unused")
public class FunctionalStatus extends DatabaseObject {
    private FunctionalStatusType functionalStatusType;
    private SequenceOntology structuralVariant;

    public FunctionalStatus() {
        super(SchemaClass.FUNCTIONAL_STATUS);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.functionalStatusType = DatabaseObjectUtils.getDatabaseObject(jsonObject, "functionalStatusType");

        this.structuralVariant = DatabaseObjectUtils.getDatabaseObject(jsonObject, "structuralVariant");
    }

    public FunctionalStatusType getFunctionalStatusType() {
        return functionalStatusType;
    }

    public SequenceOntology getStructuralVariant() {
        return structuralVariant;
    }
}
