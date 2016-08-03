package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class DatabaseIdentifier extends DatabaseObject {
    private String databaseName;
    private String identifier;
    private String url; // Valid URL based on referenceDatabase
    private List<DatabaseIdentifier> crossReference;
    private ReferenceDatabase referenceDatabase;

    public DatabaseIdentifier() {
        super(SchemaClass.DATABASE_IDENTIFIER);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.databaseName = DatabaseObjectUtils.getStringValue(jsonObject, "databaseName");

        this.identifier = DatabaseObjectUtils.getStringValue(jsonObject, "identifier");

        this.url = DatabaseObjectUtils.getStringValue(jsonObject, "url");

        this.crossReference = DatabaseObjectUtils.getObjectList(jsonObject, "crossReference");

        this.referenceDatabase = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceDatabase");
    }

    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public String getIdentifier() {
        return identifier;
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
