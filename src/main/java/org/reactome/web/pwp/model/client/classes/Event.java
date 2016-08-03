package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class Event extends DatabaseObject {

    private String definition;
    private Boolean isInDisease;    //A simple flag to indicate if this Event object is a disease
    private Boolean isInferred;     //A simple flag to indicate if this Event is inferred from another
    private List<String> name;
    private String releaseDate;
    private EventStatus releaseStatus;
    private String speciesName;

    private List<InstanceEdit> authored;
    private List<DatabaseIdentifier> crossReference;
    private List<Compartment> compartment;
    private List<Disease> disease;
    private List<InstanceEdit> edited;
    private EvidenceType evidenceType;
    private List<Figure> figure;
    private GO_BiologicalProcess goBiologicalProcess;
    private List<Event> inferredFrom;
    private List<Publication> literatureReference;
    private List<Event> orthologousEvent;
    private List<Event> precedingEvent;
    private List<Species> relatedSpecies;
    private List<InstanceEdit> reviewed;
    private List<InstanceEdit> revised;
    private List<Species> species;
    private List<Summation> summation;

    private List<NegativeRegulation> negativeRegulators;
    private List<PositiveRegulation> positiveRegulators;
    private List<Requirement> requirements;

    public Event(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.definition = DatabaseObjectUtils.getStringValue(jsonObject, "definition");
        this.isInDisease = DatabaseObjectUtils.getBooleanValue(jsonObject, "isInDisease");
        this.isInferred = DatabaseObjectUtils.getBooleanValue(jsonObject, "isInferred");
        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");
        this.releaseDate = DatabaseObjectUtils.getStringValue(jsonObject, "releaseDate");

        String status = DatabaseObjectUtils.getStringValue(jsonObject, "releaseStatus");
        this.releaseStatus = status != null ? EventStatus.getEventStatus(status) : EventStatus.REGULAR;

        this.speciesName = DatabaseObjectUtils.getStringValue(jsonObject, "speciesName");

        this.authored = DatabaseObjectUtils.getObjectList(jsonObject, "authored");

        this.crossReference = DatabaseObjectUtils.getObjectList(jsonObject, "crossReference");

        this.compartment = DatabaseObjectUtils.getObjectList(jsonObject, "compartment");

        this.disease = DatabaseObjectUtils.getObjectList(jsonObject, "disease");

        this.edited  = DatabaseObjectUtils.getObjectList(jsonObject, "edited");

        this.evidenceType = DatabaseObjectUtils.getDatabaseObject(jsonObject, "evidenceType");

        this.figure = DatabaseObjectUtils.getObjectList(jsonObject, "figure");

        this.goBiologicalProcess = DatabaseObjectUtils.getDatabaseObject(jsonObject, "goBiologicalProcess");

        this.inferredFrom = DatabaseObjectUtils.getObjectList(jsonObject, "inferredFrom");

        this.literatureReference = DatabaseObjectUtils.getObjectList(jsonObject, "literatureReference");

        this.orthologousEvent = DatabaseObjectUtils.getObjectList(jsonObject, "orthologousEvent");

        this.precedingEvent = DatabaseObjectUtils.getObjectList(jsonObject, "precedingEvent");

        this.relatedSpecies = DatabaseObjectUtils.getObjectList(jsonObject, "relatedSpecies");

        this.reviewed = DatabaseObjectUtils.getObjectList(jsonObject, "reviewed");

        this.revised = DatabaseObjectUtils.getObjectList(jsonObject, "revised");

        this.species = DatabaseObjectUtils.getObjectList(jsonObject, "species");

        this.summation = DatabaseObjectUtils.getObjectList(jsonObject, "summation");

        this.negativeRegulators = DatabaseObjectUtils.getObjectList(jsonObject, "negativelyRegulatedBy");

        this.positiveRegulators = DatabaseObjectUtils.getObjectList(jsonObject, "positivelyRegulatedBy");

        this.requirements = DatabaseObjectUtils.getObjectList(jsonObject, "requirements");
    }

    public ImageResource getStatusIcon() {
        switch (getReleaseStatus()) {
            case NEW:       return DatabaseObjectImages.INSTANCE.isNew();
            case UPDATED:   return DatabaseObjectImages.INSTANCE.isUpdated();
            case REGULAR:
            default:        return null;
        }
    }

    public ImageResource getDiseaseIcon(){
        if(isInDisease){
            return DatabaseObjectImages.INSTANCE.isDisease();
        }
        return null;
    }

    public ImageResource getInferredIcon(){
        if(isInferred){
            return DatabaseObjectImages.INSTANCE.isInferred();
        }
        return null;
    }

    public String getDefinition() {
        return definition;
    }

    public Boolean getInDisease() {
        return isInDisease;
    }

    public Boolean getInferred() {
        return isInferred;
    }

    public List<String> getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public EventStatus getReleaseStatus() {
        return releaseStatus;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public List<InstanceEdit> getAuthored() {
        return authored;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public List<Compartment> getCompartment() {
        return compartment;
    }

    public List<Disease> getDisease() {
        return disease;
    }

    public List<InstanceEdit> getEdited() {
        return edited;
    }

    public EvidenceType getEvidenceType() {
        return evidenceType;
    }

    public List<Figure> getFigure() {
        return figure;
    }

    public GO_BiologicalProcess getGoBiologicalProcess() {
        return goBiologicalProcess;
    }

    public List<Event> getInferredFrom() {
        return inferredFrom;
    }

    public List<Publication> getLiteratureReference() {
        return literatureReference;
    }

    public List<Event> getOrthologousEvent() {
        return orthologousEvent;
    }

    public List<Event> getPrecedingEvent() {
        return precedingEvent;
    }

    public List<Species> getRelatedSpecies() {
        return relatedSpecies;
    }

    public List<InstanceEdit> getReviewed() {
        return reviewed;
    }

    public List<InstanceEdit> getRevised() {
        return revised;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public List<Summation> getSummation() {
        return summation;
    }

    public List<NegativeRegulation> getNegativeRegulators() {
        return negativeRegulators;
    }

    public List<PositiveRegulation> getPositiveRegulators() {
        return positiveRegulators;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }
}
