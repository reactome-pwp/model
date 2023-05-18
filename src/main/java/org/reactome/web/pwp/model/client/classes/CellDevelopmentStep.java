package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

public class CellDevelopmentStep extends ReactionLikeEvent {

    private Anatomy tissue;

    public CellDevelopmentStep(SchemaClass schemaClass) {
        super(schemaClass);
    }

    public CellDevelopmentStep() {
        super(SchemaClass.CELL_DEVELOPMENT_STEP);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("tissue"), () ->
                tissue = DatabaseObjectUtils.getDatabaseObject(jsonObject, "tissue")
        );
    }

    public Anatomy getTissue() {
        return tissue;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.reaction();
    }
}
