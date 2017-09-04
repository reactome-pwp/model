package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class DatabaseIdentifier extends DatabaseObject implements Comparable<DatabaseIdentifier> {
    private String databaseName;
    private String identifier;
    private String url; // Valid URL based on referenceDatabase
    private List<DatabaseIdentifier> crossReference;
    private ReferenceDatabase referenceDatabase;

    public DatabaseIdentifier() {
        super(SchemaClass.DATABASE_IDENTIFIER);
    }

    public DatabaseIdentifier(ReferenceEntity re){
        super(SchemaClass.DATABASE_IDENTIFIER);
        this.databaseName = re.getDatabaseName();
        this.identifier = re.getIdentifier();
        this.url = re.getUrl();
        this.crossReference = re.getCrossReference();
        this.referenceDatabase = re.getReferenceDatabase();
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.databaseName = DatabaseObjectUtils.getStringValue(jsonObject, "databaseName");

        this.identifier = DatabaseObjectUtils.getStringValue(jsonObject, "identifier");

        this.url = DatabaseObjectUtils.getStringValue(jsonObject, "url");

        this.crossReference = DatabaseObjectUtils.getObjectList(jsonObject, "crossReference");

        setDatabaseObject(jsonObject.get("referenceDatabase"), () ->
                referenceDatabase = DatabaseObjectUtils.getDatabaseObject(jsonObject, "referenceDatabase")
        );
    }

    public String getDatabaseName() {
        return databaseName;
    }

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

    @Override
    public int compareTo(DatabaseIdentifier o) {
        try {
            return (databaseName + identifier).compareTo(o.databaseName + o.identifier);
        } catch (Exception e) {
            return 1;
        }
    }
}
