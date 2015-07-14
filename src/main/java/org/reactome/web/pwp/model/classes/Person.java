package org.reactome.web.pwp.model.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.factory.SchemaClass;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Person extends DatabaseObject {

    private String emailAddress;
    private String firstname;
    private String initial;
    private String project;
    private String surname;
    // A new attribute added in December, 2013
    private List<DatabaseIdentifier> crossReference;

    private List<LiteratureReference> literatureReferences;

    public Person() {
        super(SchemaClass.PERSON);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        if (jsonObject.containsKey("emailAddress")) {
            this.emailAddress = DatabaseObjectUtils.getStringValue(jsonObject, "emailAddress");
        }

        if (jsonObject.containsKey("firstname")) {
            this.firstname = DatabaseObjectUtils.getStringValue(jsonObject, "firstname");
        }

        if (jsonObject.containsKey("initial")) {
            this.initial = DatabaseObjectUtils.getStringValue(jsonObject, "initial");
        }

        if (jsonObject.containsKey("project")) {
            this.project = DatabaseObjectUtils.getStringValue(jsonObject, "project");
        }

        if (jsonObject.containsKey("surname")) {
            this.surname = DatabaseObjectUtils.getStringValue(jsonObject, "surname");
        }

        this.crossReference = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "crossReference")) {
            this.crossReference.add((DatabaseIdentifier) DatabaseObjectFactory.create(object));
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getInitial() {
        return initial;
    }

    public String getProject() {
        return project;
    }

    public String getSurname() {
        return surname;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public List<LiteratureReference> getLiteratureReferences() {
        return literatureReferences;
    }

    public void setLiteratureReferences(List<LiteratureReference> literatureReferences) {
        this.literatureReferences = literatureReferences;
    }
}
