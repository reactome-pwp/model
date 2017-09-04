package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class OpenSet extends EntitySet {

    private String referenceType;

    private ReferenceEntity referenceEntity;

    public OpenSet() {
        super(SchemaClass.OPEN_SET);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.referenceType = DatabaseObjectUtils.getStringValue(jsonObject, "referenceType");

        setDatabaseObject(jsonObject.get("referenceEntity"), () ->
                referenceEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceEntity")
        );
    }

    public String getReferenceType() {
        return referenceType;
    }

    @Override
    public ReferenceEntity getReferenceEntity() {
        return referenceEntity;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.openSet();
    }
}
