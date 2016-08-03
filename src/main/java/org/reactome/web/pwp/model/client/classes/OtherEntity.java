package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class OtherEntity extends PhysicalEntity {

    public OtherEntity() {
        super(SchemaClass.OTHER_ENTITY);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }

    @Override
    public List<Species> getSpecies() {
        return new LinkedList<>();
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.otherEntity();
    }
}
