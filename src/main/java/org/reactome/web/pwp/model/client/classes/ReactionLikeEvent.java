package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class ReactionLikeEvent extends Event {

    private Boolean isChimeric;
    private String systematicName;
    private List<CatalystActivity> catalystActivities;
    private CatalystActivityReference catalystActivityReference;
    private List<EntityFunctionalStatus> entityFunctionalStatus;
    private List<PhysicalEntity> entityOnOtherCell;
    private List<PhysicalEntity> inputs;
    private ReactionLikeEvent normalReaction;
    private List<PhysicalEntity> outputs;
    private List<DatabaseObject> requiredInputComponent;

    private List<Regulation> regulatedBy;
    private List<RegulationReference> regulationReference;

    public ReactionLikeEvent(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.isChimeric = DatabaseObjectUtils.getBooleanValue(jsonObject, "isChimeric");

        this.systematicName = DatabaseObjectUtils.getStringValue(jsonObject, "systematicName");

        this.catalystActivities = DatabaseObjectUtils.getObjectList(jsonObject, "catalystActivity");

        this.catalystActivityReference = DatabaseObjectUtils.getDatabaseObject(jsonObject, "catalystActivityReference");

        this.entityFunctionalStatus = DatabaseObjectUtils.getObjectList(jsonObject, "entityFunctionalStatus");

        this.entityOnOtherCell = DatabaseObjectUtils.getObjectList(jsonObject, "entityOnOtherCell");

        this.inputs = DatabaseObjectUtils.getObjectList(jsonObject, "input");

        this.normalReaction = DatabaseObjectUtils.getDatabaseObject(jsonObject, "normalReaction");

        this.outputs = DatabaseObjectUtils.getObjectList(jsonObject, "output");

        this.requiredInputComponent = DatabaseObjectUtils.getObjectList(jsonObject, "requiredInputComponent");

        this.regulatedBy = DatabaseObjectUtils.getObjectList(jsonObject, "regulatedBy");

        this.regulationReference = DatabaseObjectUtils.getObjectList(jsonObject, "regulationReference");
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

    public ReactionLikeEvent getNormalReaction() {
        return normalReaction;
    }

    public List<PhysicalEntity> getOutputs() {
        return outputs;
    }

    public List<DatabaseObject> getRequiredInputComponent() {
        return requiredInputComponent;
    }

    public CatalystActivityReference getCatalystActivityReference() {
        return catalystActivityReference;
    }

    public List<RegulationReference> getRegulationReference() {
        return regulationReference;
    }

    private List<NegativeRegulation> negativeRegulations;

    public List<NegativeRegulation> getNegativeRegulations() {
        if (negativeRegulations != null) return negativeRegulations;
        negativeRegulations = new ArrayList<>();
        for (Regulation regulation : regulatedBy) {
            if (regulation instanceof NegativeRegulation) {
                negativeRegulations.add((NegativeRegulation) regulation);
            }
        }
        return negativeRegulations;
    }

    private List<PositiveRegulation> positiveRegulations;

    public List<PositiveRegulation> getPositiveRegulations() {
        if (positiveRegulations != null) return positiveRegulations;
        positiveRegulations = new ArrayList<>();
        for (Regulation regulation : regulatedBy) {
            if (regulation instanceof PositiveRegulation) {
                positiveRegulations.add((PositiveRegulation) regulation);
            }
        }
        return positiveRegulations;
    }
}
