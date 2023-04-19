package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
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

        this.rnaMarker = DatabaseObjectUtils.getObjectList(jsonObject, "RNAMarker");
        this.markerReference = DatabaseObjectUtils.getObjectList(jsonObject, "markerReference");
        this.organ = DatabaseObjectUtils.getDatabaseObject(jsonObject, "organ");
        this.proteinMarker = DatabaseObjectUtils.getDatabaseObject(jsonObject, "proteinMarker");
        this.species = DatabaseObjectUtils.getDatabaseObject(jsonObject, "species");
        this.tissue = DatabaseObjectUtils.getDatabaseObject(jsonObject, "tissue");
        this.tissueLayer = DatabaseObjectUtils.getDatabaseObject(jsonObject, "tissueLayer");
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


}
