package org.reactome.web.pwp.model.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.factory.DatabaseObjectFactory;
import org.reactome.web.pwp.model.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.factory.SchemaClass;
import org.reactome.web.pwp.model.images.DatabaseObjectImages;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Reaction extends ReactionLikeEvent {

    private Reaction reverseReaction;
    private String totalProt;
    private String maxHomologues;
    private String inferredProt;
    private List<Regulation> regulation;

    public Reaction() {
        super(SchemaClass.REACTION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        if (jsonObject.containsKey("reverseReaction")) {
            this.reverseReaction = DatabaseObjectUtils.getDatabaseObject(jsonObject, "reverseReaction");
        }

        if (jsonObject.containsKey("totalProt")) {
            this.totalProt = DatabaseObjectUtils.getStringValue(jsonObject, "totalProt");
        }

        if (jsonObject.containsKey("maxHomologues")) {
            this.maxHomologues = DatabaseObjectUtils.getStringValue(jsonObject, "maxHomologues");
        }

        if (jsonObject.containsKey("inferredProt")) {
            this.inferredProt = DatabaseObjectUtils.getStringValue(jsonObject, "inferredProt");
        }

        this.regulation = new LinkedList<>();
        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "regulation")) {
            this.regulation.add((Regulation) DatabaseObjectFactory.create(object));
        }
    }

    /******************************
     * NEXT TWO METHODS NOT AUTO-GENERATED
     ******************************/
    public List<PositiveRegulation> getPositiveRegulation() {
        List<PositiveRegulation> pr = new LinkedList<>();
        for (Regulation r : regulation) {
            if (r instanceof PositiveRegulation) {
                pr.add((PositiveRegulation) r);
            }
        }
        return pr;
    }

    public List<NegativeRegulation> getNegativeRegulation() {
        List<NegativeRegulation> nr = new LinkedList<>();
        for (Regulation r : regulation) {
            if (r instanceof NegativeRegulation) {
                nr.add((NegativeRegulation) r);
            }
        }
        return nr;
    }

    /*************************************************************************************************/

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.reaction();
    }

    public Reaction getReverseReaction() {
        return reverseReaction;
    }

    public String getTotalProt() {
        return totalProt;
    }

    public String getMaxHomologues() {
        return maxHomologues;
    }

    public String getInferredProt() {
        return inferredProt;
    }

    public List<Regulation> getRegulation() {
        return regulation;
    }
}
