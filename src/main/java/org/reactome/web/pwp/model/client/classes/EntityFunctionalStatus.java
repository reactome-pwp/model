package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("unused")
public class EntityFunctionalStatus extends DatabaseObject {

    private List<FunctionalStatus> functionalStatus;
    private PhysicalEntity diseaseEntity;
    private PhysicalEntity normalEntity;

    public EntityFunctionalStatus() {
        super(SchemaClass.ENTITY_FUNCTIONAL_STATUS);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.functionalStatus = DatabaseObjectUtils.getObjectList(jsonObject, "functionalStatus");

        setDatabaseObject(jsonObject.get("diseaseEntity"), () ->
                diseaseEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "diseaseEntity"));
        //For backwards compatibility
        setDatabaseObject(jsonObject.get("physicalEntity"), () ->
                diseaseEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "physicalEntity"));

        setDatabaseObject(jsonObject.get("normalEntity"), () ->
                normalEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "normalEntity")
        );
    }

    public List<FunctionalStatus> getFunctionalStatus() {
        return functionalStatus;
    }

    public PhysicalEntity getDiseaseEntity() {
        return diseaseEntity;
    }

    public PhysicalEntity getNormalEntity() {
        return normalEntity;
    }
}
