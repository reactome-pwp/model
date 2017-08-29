package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class SimpleEntity extends PhysicalEntity {

    private String referenceType;
    private ReferenceMolecule referenceEntity;
    private Species species;

    public SimpleEntity() {
        super(SchemaClass.SIMPLE_ENTITY);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.referenceType = DatabaseObjectUtils.getStringValue(jsonObject, "referenceType");

        this.referenceEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceEntity");

        setDatabaseObject(jsonObject.get("species"), () ->
                species = DatabaseObjectUtils.getDatabaseObject(jsonObject, "species")
        );
    }

    @Override
    public List<DatabaseIdentifier> getCrossReference() {
        if (!super.getCrossReference().isEmpty()) return super.getCrossReference();
        return referenceEntity != null ? referenceEntity.getCrossReference() : new LinkedList<>();
    }

    public String getReferenceType() {
        return referenceType;
    }

    public ReferenceMolecule getReferenceEntity() {
        return referenceEntity;
    }

    @Override
    public List<Species> getSpecies() {
        if (species == null) return new LinkedList<>();
        return Collections.singletonList(species);
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.simpleEntity();
    }
}
