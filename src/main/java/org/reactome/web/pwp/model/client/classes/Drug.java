package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public abstract class Drug extends PhysicalEntity {

    private ReferenceTherapeutic referenceTherapeutic;

    Drug(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("referenceTherapeutic"), () ->
                referenceTherapeutic = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceTherapeutic")
        );
    }

    public ReferenceTherapeutic getReferenceTherapeutic() {
        return referenceTherapeutic;
    }

    @Override
    public List<Species> getSpecies() {
        return new LinkedList<>();
    }
}
