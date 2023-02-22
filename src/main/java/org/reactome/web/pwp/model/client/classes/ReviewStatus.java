package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

public class ReviewStatus extends ControlledVocabulary{

    public ReviewStatus() {
        super(SchemaClass.REVIEW_STATUS);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
    }
}
