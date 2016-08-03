package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class ReferenceIsoform extends ReferenceGeneProduct {

    private String variantIdentifier;
    private List<ReferenceGeneProduct> isoformParent;

    public ReferenceIsoform() {
        super(SchemaClass.REFERENCE_ISOFORM);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.variantIdentifier = DatabaseObjectUtils.getStringValue(jsonObject, "variantIdentifier");

        this.isoformParent = DatabaseObjectUtils.getObjectList(jsonObject, "isoformParent");
    }

    public String getVariantIdentifier() {
        return variantIdentifier;
    }

    public List<ReferenceGeneProduct> getIsoformParent() {
        return isoformParent;
    }
}
