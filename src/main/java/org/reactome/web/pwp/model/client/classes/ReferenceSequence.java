package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class ReferenceSequence extends ReferenceEntity {

    private String checksum;
    private List<String> comment;
    private List<String> description;
    private List<String> geneName;
    private Boolean isSequenceChanged;
    private List<String> keyword;
    private List<String> secondaryIdentifier;
    private Integer sequenceLength;
    private Species species;

    public ReferenceSequence(SchemaClass schemaClass) {
        super(schemaClass);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.checksum = DatabaseObjectUtils.getStringValue(jsonObject, "checksum");

        this.comment = DatabaseObjectUtils.getStringList(jsonObject, "comment");

        this.description = DatabaseObjectUtils.getStringList(jsonObject, "description");

        this.geneName = DatabaseObjectUtils.getStringList(jsonObject, "geneName");

        this.isSequenceChanged = DatabaseObjectUtils.getBooleanValue(jsonObject, "isSequenceChanged");

        this.keyword = DatabaseObjectUtils.getStringList(jsonObject, "keyword");

        this.secondaryIdentifier = DatabaseObjectUtils.getStringList(jsonObject, "secondaryIdentifier");

        this.sequenceLength = DatabaseObjectUtils.getIntValue(jsonObject, "sequenceLength");

        setDatabaseObject(jsonObject.get("species"), () ->
                species = DatabaseObjectUtils.getDatabaseObject(jsonObject, "species")
        );
    }

    public String getChecksum() {
        return checksum;
    }

    public List<String> getComment() {
        return comment;
    }

    public List<String> getDescription() {
        return description;
    }

    public List<String> getGeneName() {
        return geneName;
    }

    public Boolean getSequenceChanged() {
        return isSequenceChanged;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public List<String> getSecondaryIdentifier() {
        return secondaryIdentifier;
    }

    public Integer getSequenceLength() {
        return sequenceLength;
    }

    public Species getSpecies() {
        return species;
    }
}
