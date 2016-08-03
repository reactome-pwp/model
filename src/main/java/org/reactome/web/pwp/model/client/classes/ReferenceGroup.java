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
public class ReferenceGroup extends ReferenceEntity {

    private String atomicConnectivity;
    private String formula;

    public ReferenceGroup() {
        super(SchemaClass.REFERENCE_GROUP);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.atomicConnectivity = DatabaseObjectUtils.getStringValue(jsonObject, "atomicConnectivity");

        this.formula = DatabaseObjectUtils.getStringValue(jsonObject, "formula");
    }

    public String getAtomicConnectivity() {
        return atomicConnectivity;
    }

    public String getFormula() {
        return formula;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.referenceGroup();
    }
}
