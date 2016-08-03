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
public class Polymer extends PhysicalEntity {

    private Integer maxUnitCount;
    private Integer minUnitCount;
    private List<PhysicalEntity> repeatedUnit;
    private List<Species> species;


    public Polymer() {
        super(SchemaClass.POLYMER);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.maxUnitCount = DatabaseObjectUtils.getIntValue(jsonObject, "maxUnitCount");

        this.minUnitCount = DatabaseObjectUtils.getIntValue(jsonObject, "minUnitCount");

        this.repeatedUnit = DatabaseObjectUtils.getObjectList(jsonObject, "repeatedUnit");

        this.species = DatabaseObjectUtils.getObjectList(jsonObject, "species");
    }

    public Integer getMaxUnitCount() {
        return maxUnitCount;
    }

    public Integer getMinUnitCount() {
        return minUnitCount;
    }

    public List<PhysicalEntity> getRepeatedUnit() {
        return repeatedUnit;
    }

    @Override
    public List<Species> getSpecies() {
        return species;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.polymer();
    }
}
