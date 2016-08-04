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

    public Reaction() {
        super(SchemaClass.REACTION);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("reverseReaction"), () ->
                reverseReaction = DatabaseObjectUtils.getDatabaseObject(jsonObject, "reverseReaction")
        );
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.reaction();
    }

    public Reaction getReverseReaction() {
        return reverseReaction;
    }
}
