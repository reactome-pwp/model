package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Species extends Taxon {

    private List<Figure> figure;

    public Species() {
        super(SchemaClass.SPECIES);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.figure = DatabaseObjectUtils.getObjectList(jsonObject, "figure");
    }

    public List<Figure> getFigure() {
        return figure;
    }
}
