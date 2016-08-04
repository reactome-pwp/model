package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class ReferenceEntity extends DatabaseObject {

    private String databaseName;
    private String identifier;
    private List<String> name;
    private List<String> otherIdentifier;
    private String url;
    private List<DatabaseIdentifier> crossReference;
    private ReferenceDatabase referenceDatabase;

    public ReferenceEntity(SchemaClass schemaClass){
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.databaseName = DatabaseObjectUtils.getStringValue(jsonObject, "databaseName");

        this.identifier = DatabaseObjectUtils.getStringValue(jsonObject, "identifier");

        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");

        this.otherIdentifier = DatabaseObjectUtils.getStringList(jsonObject, "otherIdentifier");

        this.url = DatabaseObjectUtils.getStringValue(jsonObject, "url");

        this.crossReference = DatabaseObjectUtils.getObjectList(jsonObject, "crossReference");

        setDatabaseObject(jsonObject.get("referenceDatabase"), () ->
                referenceDatabase = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceDatabase")
        );
    }

    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getOtherIdentifier() {
        return otherIdentifier;
    }

    public String getUrl() {
        return url;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public ReferenceDatabase getReferenceDatabase() {
        return referenceDatabase;
    }
}
