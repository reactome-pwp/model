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
public class EntitySet extends PhysicalEntity {

    private Boolean isOrdered;

    private List<PhysicalEntity> hasMember;

    private List<Species> species;

    public EntitySet() {
        this(SchemaClass.ENTITY_SET);
    }

    public EntitySet(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.isOrdered = DatabaseObjectUtils.getBooleanValue(jsonObject, "isOrdered");

        this.hasMember = DatabaseObjectUtils.getObjectList(jsonObject, "hasMember");

        this.species = DatabaseObjectUtils.getObjectList(jsonObject, "species");
    }

    public Boolean getOrdered() {
        return isOrdered;
    }

    public List<PhysicalEntity> getHasMember() {
        return hasMember;
    }

    @Override
    public List<Species> getSpecies() {
        return species;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.entitySet();
    }
}
