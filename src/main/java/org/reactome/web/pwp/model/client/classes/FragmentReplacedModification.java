package org.reactome.web.pwp.model.client.classes;


import com.google.gwt.json.client.JSONObject;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class FragmentReplacedModification extends FragmentModification {

    private String alteredAminoAcidFragment;

    public FragmentReplacedModification() {
        super(SchemaClass.FRAGMENT_REPLACED_MODIFICATION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.alteredAminoAcidFragment = DatabaseObjectUtils.getStringValue(jsonObject, "alteredAminoAcidFragment");
    }

    public String getAlteredAminoAcidFragment() {
        return alteredAminoAcidFragment;
    }
}
