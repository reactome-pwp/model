package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class GenomeEncodedEntity extends PhysicalEntity {

    private Species species;

    public GenomeEncodedEntity() {
        this(SchemaClass.GENOME_ENCODED_ENTITY);
    }

    public GenomeEncodedEntity(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("species"), () -> {
            DatabaseObject object = DatabaseObjectUtils.getDatabaseObject(jsonObject, "species");
            if(object instanceof Species) species = (Species) object; //It could also be Taxon
        });
    }

    @Override
    public List<Species> getSpecies() {
        if(species==null) return new LinkedList<>();
        return Collections.singletonList(species);
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.genomeEncodeEntity();
    }
}
