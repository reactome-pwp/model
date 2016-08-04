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
public class BlackBoxEvent extends ReactionLikeEvent {

    private Event templateEvent;
    private List<Event> hasEvent;

    public BlackBoxEvent() {
        super(SchemaClass.BLACK_BOX_EVENT);
    }

    @Override
    public void load(JSONObject jsonObject) {
        super.load(jsonObject);

        setDatabaseObject(jsonObject.get("templateEvent"), () ->
                templateEvent = DatabaseObjectUtils.getDatabaseObject(jsonObject, "templateEvent")
        );

        this.hasEvent = DatabaseObjectUtils.getObjectList(jsonObject, "hasEvent");
    }

    public Event getTemplateEvent() {
        return templateEvent;
    }

    public List<Event> getHasEvent() {
        return hasEvent;
    }

    @Override
    public ImageResource getImageResource() {
        return DatabaseObjectImages.INSTANCE.blackBoxEvent();
    }
}
