package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Guilherme Viteri gviteri@ebi.ac.uk
 */
public class NonsenseMutation extends ReplacedResidue {

    public NonsenseMutation() {
        super(SchemaClass.NONSENSE_MUTATION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }

}
