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
public class Species extends Taxon {

    private List<Figure> figure;
    /*private int speciesRank;
    private int species;
    private String speciesClass;*/

    public Species() {
        super(SchemaClass.SPECIES);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.figure = new LinkedList<>();
        for (JSONObject value : DatabaseObjectUtils.getObjectList(jsonObject, "figure")) {
            this.figure.add((Figure) DatabaseObjectFactory.create(value));
        }
        /*this.speciesRank = getIntValue(jsonObject, "speciesRank");
        this.species = getIntValue(jsonObject, "species");
        this.speciesClass = getStringValue(jsonObject, "speciesClass");*/
    }

    public List<Figure> getFigure() {
        return figure;
    }

    /*
    public int getSpeciesRank() {
        return speciesRank;
    }

    public int getSpecies() {
        return species;
    }

    public String getSpeciesClass() {
        return speciesClass;
    }*/
}
