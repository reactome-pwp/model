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
public class Taxon extends DatabaseObject {

    private List<DatabaseIdentifier> crossReference;
    private List<String> name;
    private Taxon superTaxon;

    public Taxon() {
        this(SchemaClass.TAXON);
    }

    public Taxon(SchemaClass schemaClass){
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.crossReference = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "crossReference")) {
            this.crossReference.add((DatabaseIdentifier) DatabaseObjectFactory.create(object));
        }

        this.name = new LinkedList<>();
        for (String name : DatabaseObjectUtils.getStringList(jsonObject, "name")) {
            this.name.add(name);
        }

        if (jsonObject.containsKey("superTaxon")) {
            this.superTaxon = DatabaseObjectUtils.getDatabaseObject(jsonObject, "superTaxon");
        }
    }


    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public List<String> getName() {
        return name;
    }

    public Taxon getSuperTaxon() {
        return superTaxon;
    }
}
