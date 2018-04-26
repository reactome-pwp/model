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
public class Complex extends PhysicalEntity {

    private Boolean isChimeric;
    private List<PhysicalEntity> hasComponent;
    private List<PhysicalEntity> entityOnOtherCell;
    private List<Compartment> includedLocation;
    private List<Species> species;
    private Boolean stoichiometryKnown;

    public Complex() {
        super(SchemaClass.COMPLEX);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.isChimeric = DatabaseObjectUtils.getBooleanValue(jsonObject, "isChimeric");

        this.hasComponent = DatabaseObjectUtils.getObjectList(jsonObject, "hasComponent");

        this.entityOnOtherCell = DatabaseObjectUtils.getObjectList(jsonObject, "entityOnOtherCell");

        this.includedLocation = DatabaseObjectUtils.getObjectList(jsonObject, "includedLocation");

        this.species = DatabaseObjectUtils.getObjectList(jsonObject, "species");

        this.stoichiometryKnown = DatabaseObjectUtils.getBooleanValue(jsonObject, "stoichiometryKnown");
    }

    public Boolean getChimeric() {
        return isChimeric;
    }

    public List<PhysicalEntity> getHasComponent() {
        return hasComponent;
    }

    public List<PhysicalEntity> getEntityOnOtherCell() {
        return entityOnOtherCell;
    }

    public List<Compartment> getIncludedLocation() {
        return includedLocation;
    }

    @Override
    public List<Species> getSpecies() {
        return species;
    }

    public Boolean getStoichiometryKnown() {
        return stoichiometryKnown;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.complex();
    }
}
