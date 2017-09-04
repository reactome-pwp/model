package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;


/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class ProteinDrug extends Drug {

    private ReferenceGeneProduct referenceEntity;

    public ProteinDrug() {
        super(SchemaClass.PROTEIN_DRUG);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("referenceEntity"), () ->
                referenceEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceEntity")
        );
    }

    @Override
    public ReferenceGeneProduct getReferenceEntity() {
        return referenceEntity;
    }
}
