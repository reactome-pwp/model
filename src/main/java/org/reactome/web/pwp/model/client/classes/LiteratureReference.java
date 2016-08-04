package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("unused")
public class LiteratureReference extends Publication {

    private String journal;
    private String pages;
    private Integer pubMedIdentifier;
    private Integer volume;
    private Integer year;

    public LiteratureReference() {
        super(SchemaClass.LITERATURE_REFERENCE);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.journal = DatabaseObjectUtils.getStringValue(jsonObject, "journal");

        this.pages = DatabaseObjectUtils.getStringValue(jsonObject, "pages");

        this.pubMedIdentifier = DatabaseObjectUtils.getIntValue(jsonObject, "pubMedIdentifier");

        this.volume = DatabaseObjectUtils.getIntValue(jsonObject, "volume");

        this.year = DatabaseObjectUtils.getIntValue(jsonObject, "year");
    }

    public String getJournal() {
        return journal;
    }

    public String getPages() {
        return pages;
    }

    public Integer getPubMedIdentifier() {
        return pubMedIdentifier;
    }

    public Integer getVolume() {
        return volume;
    }

    public Integer getYear() {
        return year;
    }
}
