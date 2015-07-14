package org.reactome.web.pwp.model.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.factory.SchemaClass;

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

        if (jsonObject.containsKey("coordinate")) {
            this.coordinate = DatabaseObjectUtils.getIntValue(jsonObject, "coordinate");
        }
    }

    public Integer getCoordinate() {
        return coordinate;
    }
}
