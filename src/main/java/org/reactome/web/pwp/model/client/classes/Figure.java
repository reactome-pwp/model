package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Figure extends DatabaseObject {

    private String url;

    public Figure() {
        super(SchemaClass.FIGURE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.url = DatabaseObjectUtils.getStringValue(jsonObject, "url");
    }

    public String getUrl() {
        return url;
    }
}
