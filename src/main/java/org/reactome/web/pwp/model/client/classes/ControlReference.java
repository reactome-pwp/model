package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Guilherme Viteri <gviteri@ebi.ac.uk>
 * @author Kostas Sidiropoulos <ksidiro@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class ControlReference extends DatabaseObject {

    private List<Publication> literatureReference;

    public ControlReference(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.literatureReference = DatabaseObjectUtils.getObjectList(jsonObject, "literatureReference");
    }

    public List<Publication> getLiteratureReference() {
        return literatureReference;
    }
}
