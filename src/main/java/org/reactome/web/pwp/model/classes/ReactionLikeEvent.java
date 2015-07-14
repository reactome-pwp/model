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
@SuppressWarnings("UnusedDeclaration")
public abstract class ReactionLikeEvent extends Event {

    private Boolean isChimeric;
    private List<PhysicalEntity> inputs;
    private List<PhysicalEntity> outputs;
    private List<PhysicalEntity> entityOnOtherCell;
    private List<DatabaseObject> requiredInputComponent;
    private ReactionLikeEvent hasMember;
    private List<CatalystActivity> catalystActivities;
    private List<ReactionLikeEvent> normalReaction;
    private List<EntityFunctionalStatus> entityFunctionalStatus;

    public ReactionLikeEvent(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        if (jsonObject.containsKey("isChimeric")) {
            this.isChimeric = DatabaseObjectUtils.getBooleanValue(jsonObject, "isChimeric");
        }

        this.inputs = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "input")) {
            this.inputs.add((PhysicalEntity) DatabaseObjectFactory.create(object));
        }

        this.outputs = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "output")) {
            this.outputs.add((PhysicalEntity) DatabaseObjectFactory.create(object));
        }

        this.entityOnOtherCell = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "entityOnOtherCell")) {
            this.entityOnOtherCell.add((PhysicalEntity) DatabaseObjectFactory.create(object));
        }

        this.requiredInputComponent = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "requiredInputComponent")) {
            this.requiredInputComponent.add(DatabaseObjectFactory.create(object));
        }

        if (jsonObject.containsKey("hasMember")) {
            this.hasMember = DatabaseObjectUtils.getDatabaseObject(jsonObject, "hasMember");
        }

        this.catalystActivities = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "catalystActivity")) {
            this.catalystActivities.add((CatalystActivity) DatabaseObjectFactory.create(object));
        }

        this.normalReaction = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "normalReaction")) {
            this.normalReaction.add((ReactionLikeEvent) DatabaseObjectFactory.create(object));
        }

        this.entityFunctionalStatus = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "entityFunctionalStatus")) {
            this.entityFunctionalStatus.add((EntityFunctionalStatus) DatabaseObjectFactory.create(object));
        }
    }

    public Boolean getChimeric() {
        return this.isChimeric;
    }

    public List<PhysicalEntity> getInputs() {
        return this.inputs;
    }

    public List<PhysicalEntity> getOutputs() {
        return this.outputs;
    }

    public List<PhysicalEntity> getEntityOnOtherCell() {
        return this.entityOnOtherCell;
    }

    public List<DatabaseObject> getRequiredInputComponent() {
        return this.requiredInputComponent;
    }

    public ReactionLikeEvent getHasMember() {
        return this.hasMember;
    }

    public List<CatalystActivity> getCatalystActivities() {
        return this.catalystActivities;
    }

    public List<ReactionLikeEvent> getNormalReaction() {
        return this.normalReaction;
    }

    public List<EntityFunctionalStatus> getEntityFunctionalStatus() {
        return entityFunctionalStatus;
    }
}
