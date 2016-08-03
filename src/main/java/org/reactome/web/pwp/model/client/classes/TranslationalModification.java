package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class TranslationalModification extends AbstractModifiedResidue {

    private Integer coordinate;
    private PsiMod psiMod;

    public TranslationalModification(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.coordinate = DatabaseObjectUtils.getIntValue(jsonObject, "coordinate");

        this.psiMod = DatabaseObjectUtils.getDatabaseObject(jsonObject, "psiMod");
    }

    public Integer getCoordinate() {
        return coordinate;
    }

    public PsiMod getPsiMod() {
        return psiMod;
    }
}
