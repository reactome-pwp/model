package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

public class Cell extends PhysicalEntity {

    private List<EntityWithAccessionedSequence> rnaMarker;
    private List<MarkerReference> markerReference;
    private Anatomy organ;

    private List<EntityWithAccessionedSequence> proteinMarker;

    private List<Species> species;

    private Anatomy tissue;

    private Anatomy tissueLayer;

    public Cell() {
        super(SchemaClass.CELL);
    }

    public Cell(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.rnaMarker = DatabaseObjectUtils.getObjectList(jsonObject, "rnaMarker");
        this.markerReference = DatabaseObjectUtils.getObjectList(jsonObject, "markerReference");

        setDatabaseObject(jsonObject.get("organ"), () ->
                organ = DatabaseObjectUtils.getDatabaseObject(jsonObject, "organ")
        );

        this.proteinMarker = DatabaseObjectUtils.getObjectList(jsonObject, "proteinMarker");
        this.species = DatabaseObjectUtils.getObjectList(jsonObject, "species");

        setDatabaseObject(jsonObject.get("tissue"), () ->
                tissue = DatabaseObjectUtils.getDatabaseObject(jsonObject, "tissue")
        );

        setDatabaseObject(jsonObject.get("tissue"), () ->
                tissueLayer = DatabaseObjectUtils.getDatabaseObject(jsonObject, "tissueLayer")
        );

    }

    public List<EntityWithAccessionedSequence> getRnaMarker() {
        return rnaMarker;
    }

    public List<MarkerReference> getMarkerReference() {
        return markerReference;
    }

    public Anatomy getOrgan() {
        return organ;
    }

    public List<EntityWithAccessionedSequence> getProteinMarker() {
        return proteinMarker;
    }

    public List<Species> getSpecies() {
        return species;
    }


    public Anatomy getTissue() {
        return tissue;
    }


    public Anatomy getTissueLayer() {
        return tissueLayer;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.cell();
    }

}
