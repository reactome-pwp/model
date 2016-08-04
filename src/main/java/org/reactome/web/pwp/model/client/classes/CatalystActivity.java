package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class CatalystActivity extends DatabaseObject implements Regulator {
    private List<PhysicalEntity> activeUnit;
    private GO_MolecularFunction activity;
    private PhysicalEntity physicalEntity;
    private List<Regulation> regulatedBy;

    public CatalystActivity() {
        super(SchemaClass.CATALYST_ACTIVITY);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.activeUnit = DatabaseObjectUtils.getObjectList(jsonObject, "activeUnit");

        setDatabaseObject(jsonObject.get("activity"), () ->
                activity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "activity")
        );

        setDatabaseObject(jsonObject.get("physicalEntity"), () ->
                physicalEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "physicalEntity")
        );

        this.regulatedBy = DatabaseObjectUtils.getObjectList(jsonObject, "regulatedBy");

    }

    public List<PhysicalEntity> getActiveUnit() {
        return activeUnit;
    }

    public GO_MolecularFunction getActivity() {
        return activity;
    }

    public PhysicalEntity getPhysicalEntity() {
        return physicalEntity;
    }

    public List<Regulation> getRegulatedBy() {
        return regulatedBy;
    }

}
