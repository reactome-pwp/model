package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class CandidateSet extends EntitySet {

    private List<PhysicalEntity> hasCandidate;

    public CandidateSet() {
        super(SchemaClass.CANDIDATE_SET);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.hasCandidate = DatabaseObjectUtils.getObjectList(jsonObject, "hasCandidate");
    }

    public List<PhysicalEntity> getHasCandidate() {
        return hasCandidate;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.candidateSet();
    }
}
