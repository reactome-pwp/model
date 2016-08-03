package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class ReferenceGeneProduct extends ReferenceSequence {

    private List<String> chain;
    private List<ReferenceDNASequence> referenceGene;
    private List<ReferenceRNASequence> referenceTranscript;

    public ReferenceGeneProduct() {
        this(SchemaClass.REFERENCE_GENE_PRODUCT);
    }

    public ReferenceGeneProduct(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.chain = DatabaseObjectUtils.getStringList(jsonObject, "chain");

        this.referenceGene = DatabaseObjectUtils.getObjectList(jsonObject, "referenceGene");

        this.referenceTranscript = DatabaseObjectUtils.getObjectList(jsonObject, "referenceTranscript");
    }

    public List<String> getChain() {
        return chain;
    }

    public List<ReferenceDNASequence> getReferenceGene() {
        return referenceGene;
    }

    public List<ReferenceRNASequence> getReferenceTranscript() {
        return referenceTranscript;
    }
}
