package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
public abstract class GO_Term extends DatabaseObject {

    private String accession;
    private String databaseName;
    private String definition;
    private String name;
    private String url;

    public GO_Term(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.accession = DatabaseObjectUtils.getStringValue(jsonObject, "accession");

        this.databaseName = DatabaseObjectUtils.getStringValue(jsonObject, "databaseName");

        this.definition = DatabaseObjectUtils.getStringValue(jsonObject, "definition");

        this.name = DatabaseObjectUtils.getStringValue(jsonObject, "name");

        this.url = DatabaseObjectUtils.getStringValue(jsonObject, "url");
    }

    public String getAccession() {
        return accession;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDefinition() {
        return definition;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
