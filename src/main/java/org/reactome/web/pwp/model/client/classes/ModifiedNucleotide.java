package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Guilherme Viteri gviteri@ebi.ac.uk
 */
public class ModifiedNucleotide extends TranscriptionalModification {

    private Integer coordinate;
    private DatabaseObject modification;

    public ModifiedNucleotide() {
        super(SchemaClass.MODIFIED_NUCLEOTIDE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("modification"), () ->
                modification = DatabaseObjectUtils.getDatabaseObject(jsonObject, "modification")
        );

        this.coordinate = DatabaseObjectUtils.getIntValue(jsonObject, "coordinate");
    }

    public DatabaseObject getModification() {
        return modification;
    }
    public Integer getCoordinate() {
        return coordinate;
    }

}
