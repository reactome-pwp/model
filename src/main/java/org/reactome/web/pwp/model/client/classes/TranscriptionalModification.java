package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Guilherme Viteri gviteri@ebi.ac.uk
 */
public abstract class TranscriptionalModification extends AbstractModifiedResidue {

    public TranscriptionalModification(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }

}
