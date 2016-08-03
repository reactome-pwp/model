package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class FragmentModification extends GeneticallyModifiedResidue {

    private Integer endPositionInReferenceSequence;
    private Integer startPositionInReferenceSequence;

    public FragmentModification(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.endPositionInReferenceSequence = DatabaseObjectUtils.getIntValue(jsonObject, "endPositionInReferenceSequence");

        this.startPositionInReferenceSequence = DatabaseObjectUtils.getIntValue(jsonObject, "startPositionInReferenceSequence");
    }

    public Integer getEndPositionInReferenceSequence() {
        return endPositionInReferenceSequence;
    }

    public Integer getStartPositionInReferenceSequence() {
        return startPositionInReferenceSequence;
    }
}
