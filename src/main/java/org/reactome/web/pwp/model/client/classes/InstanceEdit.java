package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class InstanceEdit extends DatabaseObject {

    private String dateTime;
    private String note;
    private List<Person> author;

    public InstanceEdit() {
        super(SchemaClass.INSTANCE_EDIT);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.dateTime = DatabaseObjectUtils.getStringValue(jsonObject, "dateTime");

        this.note = DatabaseObjectUtils.getStringValue(jsonObject, "note");

        this.author = DatabaseObjectUtils.getObjectList(jsonObject, "author");
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getNote() {
        return note;
    }

    public List<Person> getAuthor() {
        return author;
    }
}
