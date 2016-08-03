package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("unused")
public abstract class ExternalOntology extends DatabaseObject {
    private String databaseName;
    private String definition;
    private String identifier;
    private String url;

    private List<String> name;
    private List<String> synonym;
    private List<ExternalOntology> instanceOf;
    private ReferenceDatabase referenceDatabase;

    public ExternalOntology(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.databaseName = DatabaseObjectUtils.getStringValue(jsonObject, "databaseName");

        this.definition = DatabaseObjectUtils.getStringValue(jsonObject, "definition");

        this.identifier = DatabaseObjectUtils.getStringValue(jsonObject, "identifier");

        this.url = DatabaseObjectUtils.getStringValue(jsonObject, "url");

        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");

        this.synonym = DatabaseObjectUtils.getStringList(jsonObject, "synonym");

        this.instanceOf = DatabaseObjectUtils.getObjectList(jsonObject, "instanceOf");

        this.referenceDatabase = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceDatabase");
    }

    public String getDefinition() {
        return definition;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<ExternalOntology> getInstanceOf() {
        return instanceOf;
    }

    public List<String> getName() {
        return name;
    }

    public ReferenceDatabase getReferenceDatabase() {
        return referenceDatabase;
    }

    public List<String> getSynonym() {
        return synonym;
    }
}
