package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class PhysicalEntity extends DatabaseObject {
    private String definition;
    private Boolean isInDisease;
    private List<String> name;
    private String speciesName;
    private String systematicName;
    private InstanceEdit authored;
    private List<EntityCompartment> compartment;
//    private List<Input> consumedByEvent;
    private List<DatabaseIdentifier> crossReference;
    private List<Disease> disease;
    private List<InstanceEdit> edited;
    private List<Figure> figure;
    private GO_CellularComponent goCellularComponent;
    private List<PhysicalEntity> inferredTo;
    private List<PhysicalEntity> inferredFrom;
    private List<Publication> literatureReference;
//    private List<Output> producedByEvent;
    private List<InstanceEdit> reviewed;
    private List<InstanceEdit> revised;
    private List<Summation> summation;

    public PhysicalEntity(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.definition = DatabaseObjectUtils.getStringValue(jsonObject, "definition");
        this.isInDisease = DatabaseObjectUtils.getBooleanValue(jsonObject, "isInDisease");
        if (this.isInDisease == null) this.isInDisease = true;
        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");
        this.speciesName = DatabaseObjectUtils.getStringValue(jsonObject, "speciesName");
        this.systematicName = DatabaseObjectUtils.getStringValue(jsonObject, "systematicName");
        this.authored = DatabaseObjectUtils.getDatabaseObject(jsonObject, "authored");

        this.compartment = DatabaseObjectUtils.getObjectList(jsonObject, "compartment");

        this.crossReference = DatabaseObjectUtils.getObjectList(jsonObject, "crossReference");

        this.disease = DatabaseObjectUtils.getObjectList(jsonObject, "disease");

        this.edited = DatabaseObjectUtils.getObjectList(jsonObject, "edited");

        this.figure = DatabaseObjectUtils.getObjectList(jsonObject, "figure");

        this.goCellularComponent = DatabaseObjectUtils.getDatabaseObject(jsonObject, "goCellularComponent");

        this.inferredTo = DatabaseObjectUtils.getObjectList(jsonObject, "inferredTo");

        this.inferredFrom = DatabaseObjectUtils.getObjectList(jsonObject, "inferredFrom");

        this.literatureReference = DatabaseObjectUtils.getObjectList(jsonObject, "literatureReference");

        this.reviewed = DatabaseObjectUtils.getObjectList(jsonObject, "reviewed");

        this.revised = DatabaseObjectUtils.getObjectList(jsonObject, "revised");

        this.summation = DatabaseObjectUtils.getObjectList(jsonObject, "summation");
    }

    public String getDefinition() {
        return definition;
    }

    public Boolean getInDisease() {
        return isInDisease;
    }

    public List<String> getName() {
        return name;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String getSystematicName() {
        return systematicName;
    }

    public InstanceEdit getAuthored() {
        return authored;
    }

    public List<EntityCompartment> getCompartment() {
        return compartment;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public List<Disease> getDisease() {
        return disease;
    }

    public List<InstanceEdit> getEdited() {
        return edited;
    }

    public List<Figure> getFigure() {
        return figure;
    }

    public GO_CellularComponent getGoCellularComponent() {
        return goCellularComponent;
    }

    public List<PhysicalEntity> getInferredTo() {
        return inferredTo;
    }

    public List<PhysicalEntity> getInferredFrom() {
        return inferredFrom;
    }

    public List<Publication> getLiteratureReference() {
        return literatureReference;
    }

    public List<InstanceEdit> getReviewed() {
        return reviewed;
    }

    public List<InstanceEdit> getRevised() {
        return revised;
    }

    public List<Summation> getSummation() {
        return summation;
    }

    public abstract List<Species> getSpecies();
}