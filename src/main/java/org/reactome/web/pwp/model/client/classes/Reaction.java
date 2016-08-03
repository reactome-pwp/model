package org.reactome.web.pwp.model.client.classes;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.resources.client.ImageResource;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectImages;
import org.reactome.web.pwp.model.client.factory.DatabaseObjectUtils;
import org.reactome.web.pwp.model.client.factory.SchemaClass;

/**
 * @author Antonio Fabregat <fabregat@ebi.ac.uk>
 */
@SuppressWarnings("UnusedDeclaration")
public class Reaction extends ReactionLikeEvent {

    private Reaction reverseReaction;
//    private List<Regulation> regulation;

    public Reaction() {
        super(SchemaClass.REACTION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        this.reverseReaction = DatabaseObjectUtils.getDatabaseObject(jsonObject, "reverseReaction");

//        this.regulation = new LinkedList<>();
//        for (JSONObject object : DatabaseObjectUtils.getObjectList(jsonObject, "regulation")) {
//            this.regulation.add((Regulation) DatabaseObjectFactory.create(object));
//        }
    }

    /******************************
     * NEXT TWO METHODS NOT AUTO-GENERATED
     ******************************/
//    public List<PositiveRegulation> getPositiveRegulation() {
//        List<PositiveRegulation> pr = new LinkedList<>();
//        for (Regulation r : regulation) {
//            if (r instanceof PositiveRegulation) {
//                pr.add((PositiveRegulation) r);
//            }
//        }
//        return pr;
//    }
//
//    public List<NegativeRegulation> getNegativeRegulation() {
//        List<NegativeRegulation> nr = new LinkedList<>();
//        for (Regulation r : regulation) {
//            if (r instanceof NegativeRegulation) {
//                nr.add((NegativeRegulation) r);
//            }
//        }
//        return nr;
//    }

    /*************************************************************************************************/

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.reaction();
    }

    public Reaction getReverseReaction() {
        return reverseReaction;
    }

//    public List<Regulation> getRegulation() {
//        return regulation;
//    }
}
