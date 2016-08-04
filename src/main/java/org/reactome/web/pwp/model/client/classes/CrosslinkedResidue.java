package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class CrosslinkedResidue extends TranslationalModification {

    private Integer secondCoordinate;

    private DatabaseObject modification;

    public CrosslinkedResidue(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("modification"), () ->
                modification = DatabaseObjectUtils.getDatabaseObject(jsonObject, "modification")
        );

        this.secondCoordinate = DatabaseObjectUtils.getIntValue(jsonObject, "secondCoordinate");
    }

    public Integer getSecondCoordinate() {
        return secondCoordinate;
    }

    public DatabaseObject getModification() {
        return modification;
    }
}
