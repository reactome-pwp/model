package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class ReplacedResidue extends GeneticallyModifiedResidue {

    private Integer coordinate;
    private List<PsiMod> psiMod;

    public ReplacedResidue() {
        super(SchemaClass.REPLACED_RESIDUE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.coordinate = DatabaseObjectUtils.getIntValue(jsonObject, "coordinate");

        this.psiMod = DatabaseObjectUtils.getObjectList(jsonObject, "psiMod");
    }

    public Integer getCoordinate() {
        return coordinate;
    }

    public List<PsiMod> getPsiMod() {
        return psiMod;
    }
}
