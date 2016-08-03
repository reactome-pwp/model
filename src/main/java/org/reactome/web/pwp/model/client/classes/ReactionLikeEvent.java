package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class ReactionLikeEvent extends Event {

    private Boolean isChimeric;
    private String systematicName;
    private List<CatalystActivity> catalystActivities;
    private List<EntityFunctionalStatus> entityFunctionalStatus;
    private List<PhysicalEntity> entityOnOtherCell;
    private List<PhysicalEntity> inputs;
    private List<ReactionLikeEvent> normalReaction;
    private List<PhysicalEntity> outputs;
    private List<DatabaseObject> requiredInputComponent;

    public ReactionLikeEvent(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.isChimeric = DatabaseObjectUtils.getBooleanValue(jsonObject, "isChimeric");

        this.systematicName = DatabaseObjectUtils.getStringValue(jsonObject, "systematicName");

        this.catalystActivities = DatabaseObjectUtils.getObjectList(jsonObject, "catalystActivity");

        this.entityFunctionalStatus = DatabaseObjectUtils.getObjectList(jsonObject, "entityFunctionalStatus");

        this.entityOnOtherCell = DatabaseObjectUtils.getObjectList(jsonObject, "entityOnOtherCell");

        this.inputs = DatabaseObjectUtils.getObjectList(jsonObject, "input");

        this.normalReaction = DatabaseObjectUtils.getObjectList(jsonObject, "normalReaction");

        this.outputs = DatabaseObjectUtils.getObjectList(jsonObject, "output");

        this.requiredInputComponent = DatabaseObjectUtils.getObjectList(jsonObject, "requiredInputComponent");
    }

    public Boolean getChimeric() {
        return isChimeric;
    }

    public String getSystematicName() {
        return systematicName;
    }

    public List<CatalystActivity> getCatalystActivities() {
        return catalystActivities;
    }

    public List<EntityFunctionalStatus> getEntityFunctionalStatus() {
        return entityFunctionalStatus;
    }

    public List<PhysicalEntity> getEntityOnOtherCell() {
        return entityOnOtherCell;
    }

    public List<PhysicalEntity> getInputs() {
        return inputs;
    }

    public List<ReactionLikeEvent> getNormalReaction() {
        return normalReaction;
    }

    public List<PhysicalEntity> getOutputs() {
        return outputs;
    }

    public List<DatabaseObject> getRequiredInputComponent() {
        return requiredInputComponent;
    }
}
