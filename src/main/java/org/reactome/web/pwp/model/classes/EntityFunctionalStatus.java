package org.reactome.web.pwp.model.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.factory.SchemaClass;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("unused")
public class EntityFunctionalStatus extends DatabaseObject {
    private List<FunctionalStatus> functionalStatus;
    private PhysicalEntity physicalEntity;

    public EntityFunctionalStatus() {
        super(SchemaClass.ENTITY_FUNCTIONAL_STATUS);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.functionalStatus = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "functionalStatus")) {
            this.functionalStatus.add((FunctionalStatus) DatabaseObjectFactory.create(object));
        }

        if (jsonObject.containsKey("physicalEntity")) {
            this.physicalEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "physicalEntity");
        }
    }

    public List<FunctionalStatus> getFunctionalStatus() {
        return functionalStatus;
    }

    public PhysicalEntity getPhysicalEntity() {
        return physicalEntity;
    }
}
