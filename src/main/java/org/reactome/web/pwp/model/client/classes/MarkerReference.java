package org.reactome.web.pwp.model.client.classes;


import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

public class MarkerReference extends ControlReference {

    private EntityWithAccessionedSequence marker;

    private List<Cell> cell;

    public MarkerReference(SchemaClass schemaClass) {
        super(schemaClass);
    }

    public MarkerReference() {
        super(SchemaClass.MARKER_REFERENCE);
    }

    public EntityWithAccessionedSequence getMarker() {
        return marker;
    }

    public List<Cell> getCell() {
        return cell;
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);
        this.cell = DatabaseObjectUtils.getObjectList(jsonObject, "cell");
        this.marker = DatabaseObjectUtils.getDatabaseObject(jsonObject, "marker");
    }
}