package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class Drug extends PhysicalEntity {

    private DrugType drugType;

    private ReferenceTherapeutic referenceEntity;

    public Drug() {
        super(SchemaClass.DRUG);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("referenceEntity"), () ->
                referenceEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceEntity")
        );

        setDatabaseObject(jsonObject.get("drugType"), () ->
                drugType = DatabaseObjectUtils.getDatabaseObject(jsonObject, "drugType")
        );
    }

    public DrugType getDrugType() {
        return drugType;
    }

    @Override
    public ReferenceTherapeutic getReferenceEntity() {
        return referenceEntity;
    }

    @Override
    public List<Species> getSpecies() {
        return new LinkedList<>();
    }
}
