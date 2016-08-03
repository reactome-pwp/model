package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class FragmentInsertionModification extends FragmentModification {

    private Integer coordinate;

    public FragmentInsertionModification() {
        super(SchemaClass.FRAGMENT_INSERTION_MODIFICATION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.coordinate = DatabaseObjectUtils.getIntValue(jsonObject, "coordinate");
    }

    public Integer getCoordinate() {
        return coordinate;
    }
}
