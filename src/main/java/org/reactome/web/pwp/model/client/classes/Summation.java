package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Summation extends DatabaseObject {

    private String text;
    private List<Publication> literatureReference;

    public Summation() {
        super(SchemaClass.SUMMATION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.text = DatabaseObjectUtils.getStringValue(jsonObject, "text");

        this.literatureReference = DatabaseObjectUtils.getObjectList(jsonObject, "literatureReference");
    }

    public String getText() {
        return text;
    }

    public List<Publication> getLiteratureReference() {
        return literatureReference;
    }
}
