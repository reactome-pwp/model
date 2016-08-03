package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Taxon extends DatabaseObject {

    private List<String> name;
    private String taxId;
    private List<DatabaseIdentifier> crossReference;
    private Taxon superTaxon;

    public Taxon() {
        this(SchemaClass.TAXON);
    }

    public Taxon(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.name = DatabaseObjectUtils.getStringList(jsonObject, "name");

        this.taxId = DatabaseObjectUtils.getStringValue(jsonObject, "taxId");

        this.crossReference = DatabaseObjectUtils.getObjectList(jsonObject, "crossReference");

        this.superTaxon = DatabaseObjectUtils.getDatabaseObject(jsonObject, "superTaxon");
    }

    public List<String> getName() {
        return name;
    }

    public String getTaxId() {
        return taxId;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public Taxon getSuperTaxon() {
        return superTaxon;
    }
}
