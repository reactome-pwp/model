package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class InterChainCrosslinkedResidue extends CrosslinkedResidue {

    private List<InterChainCrosslinkedResidue> equivalentTo;
    private List<ReferenceSequence> secondReferenceSequence;

    public InterChainCrosslinkedResidue() {
        super(SchemaClass.INTER_CHAIN_CROSSLINKED_RESIDUE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.equivalentTo = DatabaseObjectUtils.getObjectList(jsonObject, "equivalentTo");

        this.secondReferenceSequence = DatabaseObjectUtils.getObjectList(jsonObject, "secondReferenceSequence");
    }

    public List<InterChainCrosslinkedResidue> getEquivalentTo() {
        return equivalentTo;
    }

    public List<ReferenceSequence> getSecondReferenceSequence() {
        return secondReferenceSequence;
    }
}
