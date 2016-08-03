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

//    private String physicalEntityClass;

    public CatalystActivity() {
        super(SchemaClass.CATALYST_ACTIVITY);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.activeUnit = DatabaseObjectUtils.getObjectList(jsonObject, "activeUnit");

        this.activity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "activity");

        this.physicalEntity = DatabaseObjectUtils.getDatabaseObject(jsonObject, "physicalEntity");

        this.regulatedBy = DatabaseObjectUtils.getObjectList(jsonObject, "regulatedBy");

//        this.physicalEntityClass = DatabaseObjectUtils.getStringValue(jsonObject, "physicalEntityClass");
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

//    public String getPhysicalEntityClass() {
//        return physicalEntityClass;
//    }
}
