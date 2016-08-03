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
public class ReferenceRNASequence extends ReferenceSequence {

    private List<ReferenceDNASequence> referenceGene;

    public ReferenceRNASequence() {
        super(SchemaClass.REFERENCE_RNA_SEQUENCE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.referenceGene = DatabaseObjectUtils.getObjectList(jsonObject, "referenceGene");
    }

    public List<ReferenceDNASequence> getReferenceGene() {
        return referenceGene;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.referenceRNASequence();
    }
}
