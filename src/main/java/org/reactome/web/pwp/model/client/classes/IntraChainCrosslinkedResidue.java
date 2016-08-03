package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public class IntraChainCrosslinkedResidue extends CrosslinkedResidue {

    public IntraChainCrosslinkedResidue() {
        super(SchemaClass.INTRA_CHAIN_CROSSLINKED_RESIDUE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }
}
