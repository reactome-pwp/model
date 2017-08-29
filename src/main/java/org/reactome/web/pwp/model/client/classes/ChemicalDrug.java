package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class ChemicalDrug extends Drug {

    private ReferenceMolecule referenceEntity;

    public ChemicalDrug() {
        super(SchemaClass.CHEMICAL_DRUG);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("referenceEntity"), () ->
                referenceEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceEntity")
        );
    }

    public ReferenceMolecule getReferenceEntity() {
        return referenceEntity;
    }
}
