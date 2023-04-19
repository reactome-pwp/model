package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

public class Anatomy extends ExternalOntology {
    public Anatomy(SchemaClass schemaClass) {
        super(schemaClass);
    }

    public Anatomy() {
        super(SchemaClass.ANATOMY);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }

}
