package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Pathway extends Event {

    private String doi;
    private Boolean hasDiagram;
    private Boolean hasEHLD;
    private String isCanonical;
    private List<Event> hasEvent;
    private Pathway normalPathway;

    public Pathway(SchemaClass schemaClass) {
        super(schemaClass);
    }

    public Pathway() {
        super(SchemaClass.PATHWAY);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.doi = DatabaseObjectUtils.getStringValue(jsonObject, "doi");

        this.hasDiagram = DatabaseObjectUtils.getBooleanValue(jsonObject, "hasDiagram");
        if(this.hasDiagram == null) this.hasDiagram = false;

        this.hasEHLD = DatabaseObjectUtils.getBooleanValue(jsonObject, "hasEHLD");
        if(this.hasEHLD == null) this.hasEHLD = false;

        this.isCanonical = DatabaseObjectUtils.getStringValue(jsonObject, "isCanonical");

        this.hasEvent = DatabaseObjectUtils.getObjectList(jsonObject, "hasEvent");

        setDatabaseObject(jsonObject.get("normalPathway"), () ->
                normalPathway = DatabaseObjectUtils.getDatabaseObject(jsonObject, "normalPathway")
        );
    }

    public String getDoi() {
        return doi;
    }

    public Boolean getHasDiagram() {
        return hasDiagram;
    }

    public Boolean getHasEHLD() {
        return hasEHLD;
    }

    public String getIsCanonical() {
        return isCanonical;
    }

    public List<Event> getHasEvent() {
        return hasEvent;
    }

    public Pathway getNormalPathway() {
        return normalPathway;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.pathway();
    }
}
