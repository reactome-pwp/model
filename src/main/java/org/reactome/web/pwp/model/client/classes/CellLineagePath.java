package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;


@SuppressWarnings("UnusedDeclaration")
public class CellLineagePath extends Pathway {

    private Anatomy tissue;

    public CellLineagePath(SchemaClass schemaClass) {
        super(schemaClass);
    }

    public CellLineagePath() {
        super(SchemaClass.CELL_LINEAGE_PATH);
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
        return DatabaseObjectImages.INSTANCE.pathway();
    }
}
