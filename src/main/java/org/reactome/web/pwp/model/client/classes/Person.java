package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Person extends DatabaseObject {

    private String firstname;
    private String initial;
    private String orcidId;
    private String project;
    private String surname;

    private List<Affiliation> affiliation;
    private List<DatabaseIdentifier> crossReference;

    private List<Publication> publications;

    public Person() {
        super(SchemaClass.PERSON);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.firstname = DatabaseObjectUtils.getStringValue(jsonObject, "firstname");

        this.initial = DatabaseObjectUtils.getStringValue(jsonObject, "initial");

        this.orcidId = DatabaseObjectUtils.getStringValue(jsonObject, "orcidId");

        this.project = DatabaseObjectUtils.getStringValue(jsonObject, "project");

        this.surname = DatabaseObjectUtils.getStringValue(jsonObject, "surname");

        this.affiliation = DatabaseObjectUtils.getObjectList(jsonObject, "affiliation");

        this.crossReference = DatabaseObjectUtils.getObjectList(jsonObject, "crossReference");
    }

    public String getFirstname() {
        return firstname;
    }

    public String getInitial() {
        return initial;
    }

    public String getOrcidId() {
        return orcidId;
    }

    public String getProject() {
        return project;
    }

    public String getSurname() {
        return surname;
    }

    public List<Affiliation> getAffiliation() {
        return affiliation;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
