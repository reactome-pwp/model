package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class Publication extends DatabaseObject {

    private String title;
    private List<Person> authors;

    public Publication(SchemaClass schemaClass){
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.title = DatabaseObjectUtils.getStringValue(jsonObject, "title");

        this.authors = DatabaseObjectUtils.getObjectList(jsonObject, "author");
    }

    public String getTitle() {
        return title;
    }

    public List<Person> getAuthors() {
        return authors;
    }
}
