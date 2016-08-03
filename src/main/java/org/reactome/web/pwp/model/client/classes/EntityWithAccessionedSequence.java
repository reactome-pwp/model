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
public class EntityWithAccessionedSequence extends GenomeEncodedEntity {

    private Integer endCoordinate;
    private String referenceType;
    private Integer startCoordinate;

    private List<AbstractModifiedResidue> hasModifiedResidue;
    private ReferenceSequence referenceEntity;

    public EntityWithAccessionedSequence() {
        super(SchemaClass.ENTITY_WITH_ACCESSIONED_SEQUENCE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.endCoordinate = DatabaseObjectUtils.getIntValue(jsonObject, "endCoordinate");

        this.referenceType = DatabaseObjectUtils.getStringValue(jsonObject, "referenceType");

        this.startCoordinate = DatabaseObjectUtils.getIntValue(jsonObject, "startCoordinate");

        this.hasModifiedResidue = DatabaseObjectUtils.getObjectList(jsonObject, "hasModifiedResidue");

        this.referenceEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceEntity");
    }

    public Integer getEndCoordinate() {
        return endCoordinate;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public Integer getStartCoordinate() {
        return startCoordinate;
    }

    public List<AbstractModifiedResidue> getHasModifiedResidue() {
        return hasModifiedResidue;
    }

    public ReferenceSequence getReferenceEntity() {
        return referenceEntity;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.entityWithAccessionedSequence();
    }
}
