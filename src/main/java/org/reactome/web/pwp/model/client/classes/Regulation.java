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
public class Regulation extends DatabaseObject {
    private String releaseDate;
    private List<PhysicalEntity> activeUnit;
    private GO_MolecularFunction activity;
    private InstanceEdit authored;
    private List<Pathway> containedInPathway;
    private List<InstanceEdit> edited;
    private List<Publication> literatureReference;
    private List<ReactionLikeEvent> regulatedEntity;
    private DatabaseObject regulator;
    private List<InstanceEdit> reviewed;
    private List<InstanceEdit> revised;
    private List<Summation> summation;
    private GO_BiologicalProcess goBiologicalProcess;

    public Regulation() {
        this(SchemaClass.REGULATION);
    }

    public Regulation(SchemaClass schemaClass){
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.releaseDate = DatabaseObjectUtils.getStringValue(jsonObject, "releaseDate");

        this.activeUnit = DatabaseObjectUtils.getObjectList(jsonObject, "activeUnit");

        this.activity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "activity");

        this.authored = DatabaseObjectUtils.getDatabaseObject(jsonObject, "authored");

        this.containedInPathway = DatabaseObjectUtils.getObjectList(jsonObject, "containedInPathway");

        this.edited = DatabaseObjectUtils.getObjectList(jsonObject, "edited");

        this.literatureReference = DatabaseObjectUtils.getObjectList(jsonObject, "literatureReference");

        this.regulatedEntity = DatabaseObjectUtils.getObjectList(jsonObject, "regulatedEntity");

        setDatabaseObject(jsonObject.get("regulator"), () ->
                regulator = DatabaseObjectUtils.getDatabaseObject(jsonObject, "regulator")
        );

        setDatabaseObject(jsonObject.get("reviewed"), () ->
                reviewed = DatabaseObjectUtils.getDatabaseObject(jsonObject, "reviewed")
        );

        this.revised = DatabaseObjectUtils.getObjectList(jsonObject, "revised");

        this.summation = DatabaseObjectUtils.getObjectList(jsonObject, "summation");

        setDatabaseObject(jsonObject.get("goBiologicalProcess"), () ->
                goBiologicalProcess = DatabaseObjectUtils.getDatabaseObject(jsonObject, "goBiologicalProcess")
        );
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<PhysicalEntity> getActiveUnit() {
        return activeUnit;
    }

    public GO_MolecularFunction getActivity() {
        return activity;
    }

    public InstanceEdit getAuthored() {
        return authored;
    }

    public List<Pathway> getContainedInPathway() {
        return containedInPathway;
    }

    public List<InstanceEdit> getEdited() {
        return edited;
    }

    public List<Publication> getLiteratureReference() {
        return literatureReference;
    }

    public List<ReactionLikeEvent> getRegulatedEntity() {
        return regulatedEntity;
    }

    public DatabaseObject getRegulator() {
        return regulator;
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

    public GO_BiologicalProcess getGoBiologicalProcess() {
        return goBiologicalProcess;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.regulator();
    }
}
